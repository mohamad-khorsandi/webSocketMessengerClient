package root.utils;

import lombok.Data;

@Data
public class Pair<K, V> {
    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    K key;
    V val;
}
