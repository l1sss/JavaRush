package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private transient HashMap<E, Object> map;

    private static final Object PRESENT = new Object();

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int) (collection.size() / .75f) + 1, 16));
        addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        //noinspection SuspiciousMethodCalls
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.keySet().remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            AmigoSet<E> clone = (AmigoSet<E>) super.clone();
            clone.map = (HashMap<E, Object>) map.clone();
            return clone;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));

        out.writeInt(map.size());

        for (E e : map.keySet())
            out.writeObject(e);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int capacity = in.readInt();
        if (capacity < 0)
            throw new InvalidObjectException("Illegal capacity: " + capacity);

        float loadFactor = in.readFloat();
        if (loadFactor <= 0)
            throw new InvalidObjectException("Illegal load factor: " + loadFactor);

        int size = in.readInt();
        if (size < 0)
            throw new InvalidObjectException("Illegal size: " + size);

        map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            E e = (E) in.readObject();
            map.put(e, PRESENT);
        }
    }
}