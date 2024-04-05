#version 400 core

in vec3 vertex_position;

uniform mat4 camera_projection;
uniform mat4 camera_view;
uniform mat4 transformation;

uniform vec4 outline_color;

out vec4 out_color;

const float offset = 1.00002f;

void main() {
    gl_Position = camera_projection * camera_view * transformation * vec4(vertex_position * offset, 1.0);
    out_color = outline_color;
}