varying vec4 vColor;
varying vec2 vTexCoord0;
uniform sampler2D u_texture;
uniform sampler2D u_texture_displacement;

float sqr(float a){
	return a*a;
}

void main()
{

	float delta = 0.000001;
	float delta1 = delta * 1.414;

	vec2 disp = vec2(0.0,0.0);

	vec2 c1 = vec2(vTexCoord0.x + delta , vTexCoord0.y);
	vec2 c2 = vec2(vTexCoord0.x - delta , vTexCoord0.y);
	vec2 c3 = vec2(vTexCoord0.x , vTexCoord0.y + delta);
	vec2 c4 = vec2(vTexCoord0.x , vTexCoord0.y - delta);

	vec4 p0 = texture2D(u_texture_displacement , vTexCoord0);
	vec4 p1 = texture2D(u_texture_displacement , c1);
	vec4 p2 = texture2D(u_texture_displacement , c2);
	vec4 p3 = texture2D(u_texture_displacement , c3);
	vec4 p4 = texture2D(u_texture_displacement , c4);

	float left = p0.r - p2.r;
	float right = p0.r - p1.r;
	float up = p0.r - p3.r;
	float down = p0.r - p4.r;

	disp.x = left >= right ? -0.01 : 0.01;
	disp.y = up >= down ? 0.01 : -0.01;

	disp.x *= sqrt( (sqr(p0.r) + sqr(p1.r) + sqr(p2.r) + sqr(p3.r) + sqr(p4.r) ) ) /5.0;
	disp.y *= sqrt( (sqr(p0.r) + sqr(p1.r) + sqr(p2.r) + sqr(p3.r) + sqr(p4.r) ) ) /5.0;

	vec2 uvPosition = vec2(vTexCoord0.x + disp.x/0.1 , vTexCoord0.y + disp.y/0.1 );

	gl_FragColor = vColor * texture2D(u_texture , uvPosition);
}

