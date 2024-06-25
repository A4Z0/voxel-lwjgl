#version 400 core

in vec2 out_vertex_texture_coordinates;

out vec4 out_color;

uniform vec4 text_color;
uniform sampler2D texture_sampler;

void main() {
    vec4 vertex_texture_color = texture(texture_sampler, out_vertex_texture_coordinates);
    out_color = vertex_texture_color * text_color;
}