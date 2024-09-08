package lab9;

import org.junit.FixMethodOrder;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 返回以 P 为根的子树中由 KEY 映射到的值。
     * 如果此 map 为不包含KEY的 Map，则为 null。
     */
    private V getHelper(K key, Node p) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left); // key小于根节点 往左边节点递归
        } else if (cmp > 0) {
            return getHelper(key, p.right); // key大于根节点 往右边节点递归
        } else {
            return p.value;
        }
    }

    /**
     * 返回指定键映射到的值，如果此
     * map 不包含键的映射。
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * 返回以 p 为根的 BSTMap，并添加 （KEY， VALUE） 作为键值映射。
     * 或者，如果 p 为 null，则返回包含 （KEY， VALUE） 的单节点 BSTMap。
     */
    private Node putHelper(K key, V value, Node p) {
        // 如果是空节点，new一个新的子节点
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        // 如果不是空节点，查找key覆盖原来的值
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /**
     * 插入键 KEY
     * 如果已存在，则将 value 更新为 VALUE。
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* 返回此映射中键值映射的数量。 */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
