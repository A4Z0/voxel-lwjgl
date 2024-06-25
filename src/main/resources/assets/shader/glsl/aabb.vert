#version 330 core

layout (std140) uniform Camera {
    vec3 position;
    mat4 projection;
    mat4 view;
};

layout (std140) uniform AABB {
    vec3 minimum;
    vec3 maximum;
};

layout (location = 0) in vec3 vertex_position;

void main() {
    gl_Position = Camera.projection * Camera.view * vec4(mix(AABB.minimum, AABB.maximum, vertex_position), 1.f);
}