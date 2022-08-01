import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskB {

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

    static boolean check(Node v, int min, int max) {

        if (v == null) return true;

        if (v.value <= min || max <= v.value) return false;

        return check(v.left, min, v.value) && check(v.right, v.value, max);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("check.in"));
        FileWriter writer = new FileWriter("check.out");
        int n = Integer.parseInt(reader.readLine());

        if (n == 0) {
            writer.write("YES");
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

        if (check(data[0], Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            writer.write("YES");
        } else {
            writer.write("NO");
        }

        reader.close();
        writer.close();
    }
}
