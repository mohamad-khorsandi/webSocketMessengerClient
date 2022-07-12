package root.utils;

public class ThreePair<K, V, O> {
    public ThreePair(K key, V val, O opt) {
        this.key = key;
        this.val = val;
        this.opt = opt;
    }

    K key;
    V val;
    O opt;
}
