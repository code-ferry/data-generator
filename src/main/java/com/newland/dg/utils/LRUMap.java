package com.newland.dg.utils;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUMap<K, V> extends LinkedHashMap<K, V> {
    static final long serialVersionUID = -1L;
    private int maxCapacity;

    public LRUMap(int maxCapacity) {
        super(16, 0.75F, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public V remove(Object key) {
        return super.remove(key);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public V put(K k, V v) {
        return super.put(k, v);
    }

    @Override
    public V get(Object k) {
        return super.get(k);
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return this.size() > this.maxCapacity;
    }
}