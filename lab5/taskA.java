import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskA {

    static class Node {
        Node right;
        Node left;
        int value;

        public Node(Node left, Node right, int value) {
            this.right = right;
            this.left = left;
            this.value = value;
        }
    }

    static class NodeInfo {
        int leftId;
        int rightId;

        public NodeInfo(int leftId, int rightId) {
            this.leftId = leftId;
            this.rightId = rightId;
        }
    }

    static int height(Node v) {
        if (v == null) return 0;
        return 1 + Math.max(height(v.left), height(v.right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("height.in"));
        FileWriter writer = new FileWriter("height.out");
        int n = Integer.parseInt(reader.readLine());

        if (n == 0) {
            writer.write("0");
            writer.close();
            return;
        }

        Node[] data = new Node[n];
        NodeInfo[] buf = new NodeInfo[n];

        for (int i = 0; i < n; i++) {
            String[] cmd = reader.readLine().split(" ");
            data[i] = new Node(null, null, Integer.parseInt(cmd[0]));
            buf[i] = new NodeInfo(Integer.parseInt(cmd[1]) - 1, Integer.parseInt(cmd[2]) - 1);
        }

        for (int i = 0; i < n; i++) {
            if (buf[i].leftId >= 0) data[i].left = data[buf[i].leftId];
            if (buf[i].rightId >= 0) data[i].right = data[buf[i].rightId];
        }

        writer.write(Integer.toString(height(data[0])));

        reader.close();
        writer.close();
    }
}