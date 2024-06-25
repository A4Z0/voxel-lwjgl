package org.a4z0.lwjgl.demo.nbt;

import java.util.*;

public class NBTTagCompound implements NBTBase<Map<String, NBTBase<?>>> {

    protected final Map<String, NBTBase<?>> m;

    /**
    * Construct a {@link NBTTagCompound}.
    */

    public NBTTagCompound() {
        this.m = new HashMap<>();
    }

    /**
    * Performs a Search for the presence of a Key.
    *
    * @param k Key to be searched for.
    *
    * @return true if it contains the Key, false otherwise.
    */

    public boolean contains(String k) {
        return this.get(k) != null;
    }

    /**
    * Retrieves all the Keys.
    *
    * @return a {@link Set} of Keys.
    */

    public Set<String> getKeys() {
        return this.getKeys(false);
    }

    /**
    * Retrieves all the Keys.
    *
    * @param d Deep keys?
    *
    * @return a {@link Set} of Keys.
    */

    public Set<String> getKeys(boolean d) {
        Set<String> keySet = new HashSet<>();

        if(d) for(Map.Entry<String, NBTBase<?>> e : this.m.entrySet())
            if(e.getValue() instanceof NBTTagCompound c)
                for(String k : c.getKeys(true))
                    keySet.add(e.getKey() + "." + k);

            else keySet.add(e.getKey());
        else keySet.addAll(this.m.keySet());

        return keySet;
    }

    /**
    * Retrieves a {@link NBTBase} attached to a Key.
    *
    * @param k Key where the {@link NBTBase} is.
    *
    * @return a {@link NBTBase} if it exists, null otherwise.
    */

    public NBTBase<?> get(String k) {
        String[] pA = k.split("\\.");

        if(pA.length > 1 && this.m.get(pA[0]) instanceof NBTTagCompound c)
            return c.get(String.join(".", Arrays.copyOfRange(pA, 1, pA.length)));
        else
            return this.m.get(pA[0]);
    }

    /**
    * Retrieves a {@link NBTBase} attached to a Key.
    *
    * @param key Key where the {@link NBTBase} is.
    * @param clazz {@link Class} to cast the returned {@link NBTBase}.
    *
    * @return a {@link NBTBase} if it exists, null otherwise.
    */

    public <T extends NBTBase<?>> T get(String key, Class<T> clazz) {
        return clazz.cast(this.get(key));
    }

    /**
    * Sets a {@link NBTBase} to a Key.
    *
    * @param k Key where the {@link NBTBase} will be attached.
    * @param v {@link NBTBase} that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound set(String k, NBTBase<?> v) {
        String[] pA = k.split("\\.");

        if(pA.length > 1 && this.m.get(pA[0]) instanceof NBTTagCompound c)
            c.set(String.join(".", Arrays.copyOfRange(pA, 1, pA.length)), v);
        else
            this.m.put(pA[0], v);

        return this;
    }

    /**
    * Removes a Key and its attached {@link NBTBase}.
    *
    * @param k Key to be removed.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound remove(String k) {
        String[] pA = k.split("\\.");

        if(pA.length > 1 && this.m.get(pA[0]) instanceof NBTTagCompound c)
            c.remove(String.join(".", Arrays.copyOfRange(pA, 1, pA.length)));
        else
            this.m.remove(pA[0]);

        return this;
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, zero otherwise.
    */

    public byte getByte(String k) {
        return this.contains(k) ? this.get(k, NBTTagByte.class).b : 0;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param b Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setByte(String k, byte b) {
        return this.set(k, new NBTTagByte(b));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, zero otherwise.
    */

    public int getInt(String k) {
        return this.contains(k) ? this.get(k, NBTTagInt.class).i : 0;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param i Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setInt(String k, int i) {
        return this.set(k, new NBTTagInt(i));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, NaN otherwise.
    */

    public float getFloat(String k) {
        return this.contains(k) ? this.get(k, NBTTagFloat.class).f : Float.NaN;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param f Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setFloat(String k, float f) {
        return this.set(k, new NBTTagFloat(f));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, NaN otherwise.
    */

    public double getDouble(String k) {
        return this.contains(k) ? this.get(k, NBTTagDouble.class).d : Double.NaN;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param d Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setDouble(String k, double d) {
        return this.set(k, new NBTTagDouble(d));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, zero otherwise.
    */

    public long getLong(String k) {
        return this.contains(k) ? this.get(k, NBTTagLong.class).l : 0L;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param l Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setLong(String k, long l) {
        return this.set(k, new NBTTagLong(l));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, false otherwise.
    */

    public boolean getBoolean(String k) {
        return this.contains(k) && this.getByte(k) == 1;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param b Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setBoolean(String k, boolean b) {
        return this.setByte(k, (byte) (b ? 1 : 0));
    }

    /**
    * Retrieves a Value attached to a Key.
    *
    * @param k Key where the Value is.
    *
    * @return a Value if it exists, null otherwise.
    */

    public String getString(String k) {
        return this.get(k, NBTTagString.class).s;
    }

    /**
    * Sets a Value to a Key.
    *
    * @param k Key where the Value will be attached.
    * @param s Value that will be attached.
    *
    * @return this {@link NBTTagCompound}.
    */

    public NBTTagCompound setString(String k, String s) {
        return this.set(k, new NBTTagString(s));
    }
}