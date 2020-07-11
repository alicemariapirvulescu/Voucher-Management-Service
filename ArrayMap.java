package temapoo;

import java.util.AbstractMap;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


class ArrayMap <K, V> extends AbstractMap<K, V> {

    @SuppressWarnings("hiding")
    class ArrayMapEntry<K, V> implements Map.Entry<K, V>, Comparable<K> {
        public K key;
        public V value;

        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }


        public String toString() {
            String result = value.toString();
            return result;
        }

        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
                    (this.getValue() == null ? 0 : this.getValue().hashCode());
        }


        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (!(other instanceof ArrayMapEntry))
                return false;
            ArrayMapEntry<K, V> o = (ArrayMapEntry<K, V>) other;
            return (this.getKey() == null ?
                    o.getKey() == null : this.getKey().equals(o.getKey()) &&
                    (this.getValue() == null ?
                            o.getValue() == null : this.getValue().equals(o.getValue())));
        }

        @Override
        public int compareTo(Object arg0) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    public Set<Map.Entry<K, V>> entries = null;
    public ArrayList<ArrayMapEntry<K, V>> list = null;

    public ArrayMap() {
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet() {
        if (entries == null) {
            entries = new AbstractSet<>() {
                public void clear() {
                    list.clear();
                }

                @SuppressWarnings("rawtypes")
                public Iterator iterator() {
                    return list.iterator();
                }

                public int size() {
                    return list.size();
                }
            };
        }
        return entries;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public V put(K key, V value) {
        int size = list.size();
        Map.Entry<K, V> entry = null;
        int i;
        if (key == null) {
            for (i = 0; i < size; i++) {
                entry = list.get(i);
                if (entry.getKey() == null) {
                    break;
                }
            }
        } else {
            for (i = 0; i < size; i++) {
                entry = list.get(i);
                if (key.equals(entry.getKey())) {
                    break;
                }
            }
        }
        V oldValue = null;
        if (i < size) {
            oldValue = entry.getValue();
            entry.setValue((V) value);
        } else {
            list.add(new ArrayMapEntry(key, value));
        }
        return oldValue;
    }


    public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); ++i) {
            result += list.get(i);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        K secondKey= (K) key;
        for (int i = 0; i < list.size(); ++i) {

            ArrayMapEntry entry = list.get(i);
            if (secondKey.equals(entry.getKey())) {
                return true;
            }
        } return false;
    }

    public V get(Object key) {
        V value = null;
        K secondKey= (K) key;
        for (int i = 0; i < list.size(); ++i) {

            ArrayMapEntry entry = list.get(i);
            if (secondKey.equals(entry.getKey())) {
                value = (V) entry.value;
            }
        }

        return value;
    }


}
