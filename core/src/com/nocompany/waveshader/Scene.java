package com.nocompany.waveshader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Scene implements Disposable {

    private Render render;

    private int width;
    private int height;

    public Scene(int width, int height){
        this.render = new Render(width, height);
        this.width = width;
        this.height = height;
    }

    public void update(float dt){

        render.getFrameBuffer().begin();

        render.getSpriteBatch().begin();
        render.getSpriteBatch().setShader(render.getWaveShader());
        render.getSpriteBatch().draw(render.getDrawTexture(), 0, 0, width, height, 0, 0, width, height, false, true);
        render.getSpriteBatch().end();

        if(Gdx.input.isTouched()) {
            render.getShapeRenderer().setColor( 0.4f, 0.0f, 0f, 0f );
            render.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
            render.getShapeRenderer().circle(Gdx.input.getX(), Gdx.input.getY(), width * 0.02f);
            render.getShapeRenderer().end();
        }

        render.getFrameBuffer().end();
        render.setDrawTexture(render.getFrameBuffer().getColorBufferTexture());

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        render.getDistorsionShader().begin();
        render.getDrawTexture().bind(1);
        render.getDistorsionShader().setUniformi("u_texture_displacement", 1);
        render.getImageTexture().bind(0);
        render.getDistorsionShader().setUniformi("u_texture", 0);
        render.getDistorsionShader().end();

        render.getSpriteBatch().begin();
        render.getSpriteBatch().setShader(render.getDistorsionShader());
        render.getSpriteBatch().draw(render.getImageTexture(), 0, 0, width, height);
        render.getSpriteBatch().end();
    }

    @Override
    public void dispose() {
        render.dispose();
    }
}