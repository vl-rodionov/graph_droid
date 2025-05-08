attribute vec4 a_Position;
attribute vec3 a_Normal;
attribute vec2 a_TexCoordinate;
uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;
varying vec3 v_Normal;
varying vec2 v_TexCoordinate;
void main() {
    v_Normal = vec3(u_MVMatrix * vec4(a_Normal, 0.0));
    v_TexCoordinate = a_TexCoordinate;
    gl_Position = u_MVPMatrix * a_Position;
}