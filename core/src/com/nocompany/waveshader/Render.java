package com.nocompany.waveshader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Render implements Disposable {

    private ShaderProgram   waveShader;
    private ShaderProgram   distorsionShader;
    private SpriteBatch     spriteBatch;
    private FrameBuffer     frameBuffer;
    private ShapeRenderer   shapeRenderer;

    private Texture         drawTexture;
    private Texture         imageTexture;

    public Render(int width, int height){

        ShaderProgram.pedantic = false;

        this.loadShaders         ();
        this.makeBatch           ();
        this.makeShapeRenderer   ();
        this.makeFrameBuffer     (width, height);
        this.loadTextures        (width, height);
    }

    private void makeFrameBuffer(int width, int height) {
        frameBuffer = new FrameBuffer(Pixmap.Format.RGB888, width, height, false);
    }

    private void loadShaders(){
        waveShader = new ShaderProgram(
            Gdx.files.internal("shaders/vertex_wave.glsl").readString(),
            Gdx.files.internal("shaders/fragment_wave.glsl").readString()
        );
        Gdx.app.log("D", String.format("wave shader compilation [%b]", waveShader.isCompiled()));

        distorsionShader = new ShaderProgram(
            Gdx.files.internal("shaders/vertex_draw.glsl").readString(),
            Gdx.files.internal("shaders/fragment_draw.glsl").readString()
        );
        Gdx.app.log("D", String.format("distorsion shader compilation [%b]", waveShader.isCompiled()));
    }

    private void makeBatch(){
        spriteBatch = new SpriteBatch();
    }

    private void makeShapeRenderer(){
        shapeRenderer = new ShapeRenderer();
    }

    private void loadTextures(int width, int height){
        Pixmap pixmap = new Pixmap( width, height, Pixmap.Format.RGB565 );
        drawTexture = new Texture(pixmap);
        pixmap.dispose();

        imageTexture = new Texture(Gdx.files.internal("blue_flower.jpg"));
    }

    public ShaderProgram getWaveShader() {
        return waveShader;
    }

    public ShaderProgram getDistorsionShader() {
        return distorsionShader;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Texture getDrawTexture() {
        return drawTexture;
    }

    public Texture getImageTexture() {
        return imageTexture;
    }

    public void setDrawTexture(Texture texture) {
        drawTexture = texture;
    }

    @Override
    public void dispose() {
        waveShader.dispose();
        distorsionShader.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
        frameBuffer.dispose();
        imageTexture.dispose();
        drawTexture.dispose();
    }
}