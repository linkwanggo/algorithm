package data_structrue;

class Node {
    private String data;
    private Node lchild;
    private Node rchild;

    public Node() {};

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLchild() {
        return lchild;
    }

    public void setLchild(Node lchild) {
        this.lchild = lchild;
    }

    public Node getRchild() {
        return rchild;
    }

    public void setRchild(Node rchild) {
        this.rchild = rchild;
    }
}

public class BinaryTree {
    private Node root;
    private String[] treeNodes = null;
    private int index = 0;

    public BinaryTree(String treeNodes) {
        root = new Node();
        this.treeNodes = treeNodes.split(",");
        createTree(root);
    }

    private Node createTree(Node node) {
        String data = treeNodes[index];
        index++;
        if ("#".equals(data)) {
            return null;
        }
        node.setData(data);
        node.setLchild(createTree(new Node()));
        node.setRchild(createTree(new Node()));
        return node;
    }

    public Node getRoot() {
        return root;
    }

    public void preOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        preOrderTraverse(root.getLchild());
        preOrderTraverse(root.getRchild());
    }

    public void inOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLchild());
        System.out.println(root.getData());
        inOrderTraverse(root.getRchild());
    }

    public void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.getLchild());
        postOrderTraverse(root.getRchild());
        System.out.print(root.getData());
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("A,B,#,D,#,#,C,#,#");
        Node root = tree.getRoot();
        tree.preOrderTraverse(root);
        tree.inOrderTraverse(root);
        tree.postOrderTraverse(root);
    }
}


