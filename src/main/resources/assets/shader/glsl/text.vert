#version 400 core

in vec2 vertex_position;
in vec2 vertex_texture_coordinates;

out vec2 out_vertex_texture_coordinates;

void main() {
    gl_Position = vec4(vertex_position, 1, 1);
    out_vertex_texture_coordinates = vertex_texture_coordinates;
}