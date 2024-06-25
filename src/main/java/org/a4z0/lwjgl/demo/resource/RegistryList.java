package org.a4z0.lwjgl.demo.resource;

public class RegistryList {

    public static final Key ROOT = new Key("root");

    public static final ResourceKey BLOCK = new ResourceKey(ROOT, new Key("block"));
    public static final ResourceKey ENTITY = new ResourceKey(ROOT, new Key("entity"));
}