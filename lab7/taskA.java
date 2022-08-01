import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskA {

    static int[][] inputArray;


    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("balance.in"));
        FileWriter writer = new FileWriter("balance.out");
        int n = Integer.parseInt(reader.readLine());
        inputArray = new int[n][3];

        for (int i = 0; i < n; i++) {
            String[] data = reader.readLine().split(" ");
            inputArray[i] = new int[]{Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])};
        }
        Tree tree = new Tree(init(null, 0), n);
        tree.updateTreeNodeHeights();
        for (int i = 0; i < n; i++) {
            writer.write(tree.balanceArray[i] + "\n");
        }
        reader.close();
        writer.close();
    }

    private static TreeNode init(TreeNode parent, int index) {
        TreeNode node = new TreeNode(inputArray[index][0], index);
        node.parent = parent;
        if (inputArray[index][1] != 0) {
            node.leftChild = init(node, inputArray[index][1] - 1);
        }
        if (inputArray[index][2] != 0) {
            node.rightChild = init(node, inputArray[index][2] - 1);
        }
        return node;
    }

    private static class Tree {
        int[] balanceArray;
        TreeNode head;

        public Tree(TreeNode head, int n) {
            this.head = head;
            balanceArray = new int[n];
        }

        private void updateTreeNodeHeights() {
            head.updateHeight(balanceArray);
        }
    }

    private static class TreeNode {
        int index;
        int value;
        int height;
        int leftChildHeight;
        int rightChildHeight;

        TreeNode parent;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value, int index) {
            this.index = index;
            this.value = value;
            height = 0;
            leftChildHeight = 0;
            rightChildHeight = 0;
            parent = null;
            leftChild = null;
            rightChild = null;
        }

        public int balance() {
            return rightChildHeight - leftChildHeight;
        }

        public int updateHeight(int[] balanceArray) {
            if (leftChild != null)
                leftChildHeight = leftChild.updateHeight(balanceArray);
            if (rightChild != null)
                rightChildHeight = rightChild.updateHeight(balanceArray);
            height = Math.max(leftChildHeight, rightChildHeight) + 1;
            balanceArray[index] = balance();
            return height;
        }
    }
}