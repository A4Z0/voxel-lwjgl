#version 400 core

in vec3 vertex_position;
in vec4 vertex_color;

uniform mat4 camera_projection;
uniform mat4 camera_view;
uniform mat4 transformation;

out vec4 out_color;

void main() {
    gl_Position = camera_projection * camera_view * transformation * vec4(vertex_position, 1.0);
    out_color = vertex_color / 255.f;
}