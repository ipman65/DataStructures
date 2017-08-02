package dataStructures;

import java.util.*;

public class MyHashTable<K, V> implements Iterable<MyHashTable.Entry<K,V>> {

    public static class Entry<K,V> {
        private K key;
        private V value;
        public Entry(K key, V value){
            this.key = key;
            this.value = value; }
        public K getKey(){ return key; }
        public V getValue(){ return value; }
    }

    private static class MyIterator<K, V> implements Iterator<Entry<K, V>> {
        private int index;
        private Entry<K,V>[] entries;

        public MyIterator(Entry<K, V>[] entries){
            index = 0;
            this.entries = entries;
        }

        public Entry<K, V> next(){
            int iterator = 0;
            while(hasNext()){
                if (entries[iterator] != null){
                    Entry<K, V> e = entries[index];
                    index++;
                    return e;
                }
                iterator++;
            }
            throw new IllegalArgumentException("there are no more elements");
        }

        public boolean hasNext(){
            return index < entries.length && entries[index] != null;
        }
    }

    private Entry<K, V>[] table;
    private int size;

    public MyHashTable(){
        size = 100;
        table = new Entry[size];
        for(int i = 0; i < size; i++){
            table[i] = null; }
    }

    public void put(K key, V value){
        if(hash(key) > size) resize();
        table[hash(key)] = new Entry(hash(key), value);
    }

    public V get(K key){
        if(!contains(key)) throw new NullPointerException("key is null");
        else return table[hash(key)].getValue();
    }

    public boolean contains(K key) {
        return table[hash(key)] != null;
    }

    public void delete(K key){
        table[hash(key)] = new Entry(hash(key), null);
    }

    public Iterator iterator(){
        return new MyIterator(table);
    }

    private int hash(K key){
        return key.hashCode() % size;
    }

    private void resize() {
        size *= 2;
        table = Arrays.copyOf(table, size);
    }

    public int size(){
        return size;
    }
}
