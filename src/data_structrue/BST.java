package data_structrue;

/**
 * 基于二叉查找树的符号表
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;           // 键
        private Value val;         // 值
        private Node left, right;  // 指向子树的链接
        private int N;             // 以该节点为根节点的节点个数

        public Node(Key key, Value val, int N) {
            this.key = key; this.val = val; this.N = N;
        }
    }

    public int size() { return size(root); }

    private int size(Node x) {
        if (x == null) { return 0; }
        else           { return x.N; }
    }

    public Value get(Key key) { return get(root, key); }

    public Value get(Node x, Key key) {
        // 在以x为根节点的子树上查找并返回key所对应的值
        if (x == null) { return null; }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) { return get(x.left, key); }
        else if (cmp > 0) { return get(x.right, key); }
        else { return x.val; }
    }

    public void put(Key key, Value val) {
        // 查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为根节点的子树上, 更新key的值并更新子树
        // 否则将以key和val为键值对作为根节点插入到该子树中
        if (x == null) { return new Node(key, val, 1); }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) { x.left = put(x.left, key, val); }
        else if (cmp > 0) {x.right = put(x.right, key, val); }
        else { x.val = val; }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(100, "ROOT");
        bst.put(70, "70");
        bst.put(110, "110");
        bst.put(105, "105");
        System.out.println(111);
    }
}
