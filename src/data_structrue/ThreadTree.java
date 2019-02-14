package data_structrue;

/**
 * 线索二叉树
 */

class ThreadNode {
    private String data;
    private int lTag;  // 1表示指向前驱， 0表示指向左孩子
    private int rTag;  // 1表示指向后继， 0表示指向右孩子
    private ThreadNode lChild;
    private ThreadNode rChild;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getlTag() {
        return lTag;
    }

    public void setlTag(int lTag) {
        this.lTag = lTag;
    }

    public int getrTag() {
        return rTag;
    }

    public void setrTag(int rTag) {
        this.rTag = rTag;
    }

    public ThreadNode getlChild() {
        return lChild;
    }

    public void setlChild(ThreadNode lChild) {
        this.lChild = lChild;
    }

    public ThreadNode getrChild() {
        return rChild;
    }

    public void setrChild(ThreadNode rChild) {
        this.rChild = rChild;
    }
}

public class ThreadTree {
    private ThreadNode root;
    private String[] treeNodes;
    private int index = 0;
    private ThreadNode pre;

    public ThreadTree(String data) {
        this.treeNodes = data.split(",");
        root = new ThreadNode();
        createTree(root);
    }

    public ThreadNode getRoot() {
        return root;
    }

    private ThreadNode createTree(ThreadNode node) {
        String data = treeNodes[index];
        index++;
        if ("#".equals(data)) {
            return null;
        }
        node.setData(data);
        node.setlTag(0);
        node.setrTag(0);
        node.setlChild(createTree(new ThreadNode()));
        node.setrChild(createTree(new ThreadNode()));
        return node;
    }

    public void inThreading(ThreadNode root) {
        if (root == null) {
            return;
        }
        inThreading(root.getlChild());
        if (root.getlChild() == null) {
            root.setlTag(1);
            root.setlChild(pre);
        }
        // 如果 判断 pre == null 将导致最后一个节点的右孩子无法设为线索 不过问题应该不大
        if (pre != null && null == pre.getrChild()) {
            pre.setrTag(1);
            pre.setrChild(root);
        }
        pre = root;
        inThreading(root.getrChild());
    }

    /**
     * 思路：根据中序线索的顺序进行遍历  例如 "HDIBJEAFCG"  当G的右线索为空时 遍历结束
     * @param root 根节点
     */
    public void inOrderThreadTree(ThreadNode root) {

        if (root == null) {
            return;
        }

        // 当出现右子树为空时表示遍历结束
        while (root != null) {
            while (root.getlTag() != 1 && root.getlChild() != null) {
                root = root.getlChild();
            }
            System.out.println(root.getData());
            while (root.getrTag() == 1) {
                // 由于建立索引时最后一个无法设为线索 因此此判断失效 当存在右线索并且右线索为空时， 表示遍历结束
//                if (root.getrChild() == null) {
//                    return;
//                }
                root = root.getrChild();
                System.out.println(root.getData());
            }
            root = root.getrChild();
        }
    }

    public static void main(String[] args) {
        ThreadTree threadTree = new ThreadTree("A,B,D,H,#,#,I,#,#,E,J,#,#,#,C,F,#,#,G,#,#");
        ThreadNode root = threadTree.getRoot();
        threadTree.inThreading(root);
        threadTree.inOrderThreadTree(root);
    }
}
