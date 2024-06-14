#version 400 core

in vec3 vertex_position;
in int vertex_color;

uniform mat4 camera_projection;
uniform mat4 camera_view;
uniform mat4 transformation;

out vec4 out_color;

void main() {
    gl_Position = camera_projection * camera_view * transformation * vec4(vertex_position, 1.0);

    out_color = vec4(
        (vertex_color) & 0xFF,
        (vertex_color >> 8) & 0xFF,
        (vertex_color >> 16) & 0xFF,
        (vertex_color >> 24) & 0xFF
    ) / 255.f;
}