varying vec4 vColor;
varying vec2 vTexCoord0;
uniform sampler2D u_texture;

void main()
{

    float delta = 0.0009;
    float pi = 3.1415926535897;
    float alpha = 0.0;
    float sum = 0.0;

    for( int j = 0 ; j < 5 ; j++){
        for( int idx = 0 ; idx < 16 ; idx++){

            float rot_x = delta * cos(alpha) - delta * sin(alpha);
            float rot_y = delta * sin(alpha) + delta * cos(alpha);

            vec2 left = vec2(vTexCoord0.x + rot_x, vTexCoord0.y + rot_y);

            alpha += pi/2.0;
            rot_x = delta * cos(alpha) - delta * sin(alpha);
            rot_y = delta * sin(alpha) + delta * cos(alpha);

            vec2 right = vec2(vTexCoord0.x + rot_x, vTexCoord0.y + rot_y);

            alpha += pi/2.0;
            rot_x = delta * cos(alpha) - delta * sin(alpha);
            rot_y = delta * sin(alpha) + delta * cos(alpha);

            vec2 up = vec2(vTexCoord0.x + rot_x, vTexCoord0.y + rot_y);

            alpha += pi/2.0;
            rot_x = delta * cos(alpha) - delta * sin(alpha);
            rot_y = delta * sin(alpha) + delta * cos(alpha);

            vec2 down = vec2(vTexCoord0.x + rot_x, vTexCoord0.y + rot_y);

            float leftVal =  (texture2D(u_texture , left)).r;
            float rightVal =  (texture2D(u_texture , right)).r;
            float upVal =  (texture2D(u_texture , up)).r;
            float downVal = (texture2D(u_texture , down)).r;

            sum += (leftVal + rightVal + upVal + downVal) / 2.0;

            alpha += pi/32.0;
        }
        delta += 0.0016;
    }

    float pointVal = (texture2D(u_texture , vTexCoord0)).r;
    float newStateVal = (texture2D(u_texture , vTexCoord0)).g;

    sum /= 81.0;
    sum -= newStateVal;
    sum *= 0.989;

	gl_FragColor = vec4(sum, pointVal, 0.0, 1.0);

}