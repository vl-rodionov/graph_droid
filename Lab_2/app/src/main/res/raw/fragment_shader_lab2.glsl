precision mediump float;
uniform vec3 u_LightDirection;
uniform vec4 u_LightColor;
uniform vec4 u_AmbientColor;
uniform sampler2D u_Texture;
uniform int u_Effect;
varying vec3 v_Normal;
varying vec2 v_TexCoordinate;
void main() {
    vec3 normal = normalize(v_Normal);
    float diff = max(dot(normal, -u_LightDirection), 0.0);
    vec4 texColor = texture2D(u_Texture, v_TexCoordinate);
    vec4 color = texColor * (u_AmbientColor + u_LightColor * diff);
    if(u_Effect==1) { float gray = dot(color.rgb, vec3(0.299,0.587,0.114)); color = vec4(vec3(gray), color.a); }
    else if(u_Effect==2) { vec3 c = color.rgb; color.rgb = vec3(c.r*0.393 + c.g*0.769 + c.b*0.189, c.r*0.349 + c.g*0.686 + c.b*0.168, c.r*0.272 + c.g*0.534 + c.b*0.131); }
    else if(u_Effect==3) { color = vec4(vec3(1.0)-color.rgb, color.a); }
    else if(u_Effect==4) { color.rgb = min(color.rgb*1.5, vec3(1.0)); }
    gl_FragColor = color;
}