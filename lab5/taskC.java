import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskC {

    static class Node {
        int key;
        Node l, r;

        public Node(int key, Node l, Node r) {
            this.key = key;
            this.l = l;
            this.r = r;
        }
    }

    static boolean search(Node x, int k) {
        if (x == null) return false;
        if (x.key == k) return true;
        if (k < x.key) return search(x.l, k);
        else return search(x.r, k);
    }

    static Node insert(Node x, int a) {
            if (x == null) return new Node(a, null, null);
            else if (x.key > a) x.l = insert(x.l, a);
            else if (x.key < a) x.r = insert(x.r, a);
        return x;
    }

    static Node next(Node root, int x) {
        Node current = root;
        Node successor = null;
        while (current != null) {
            if (current.key > x) {
                successor = current;
                current = current.l;
            }
            else current = current.r;
        }
        return successor;
    }

    static Node prev(Node root, int x) {
        Node current = root;
        Node successor = null;
        while (current != null) {
            if (current.key < x) {
                successor = current;
                current = current.r;
            }
            else  current = current.l;
        }
        return successor;
    }

    static Node min(Node x) {
        if (x.l == null) return x;
        return min(x.l);
    }

    static Node delete(Node root, int a) {
        if (root == null) return root;
        if (a < root.key) root.l = delete(root.l, a);
        else if (a > root.key) root.r = delete(root.r, a);
        else if (root.l != null && root.r != null) {
            root.key = min(root.r).key;
            root.r = delete(root.r,root.key);
        }
        else {
            if (root.l != null) root = root.l;
            else root = root.r;
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("bstsimple.in"));
        FileWriter out = new FileWriter("bstsimple.out");
        Node tree = null;
        while (in.hasNext()) {
            String[] buf = in.nextLine().split(" ");
            String cmd = buf[0];
            int value = Integer.parseInt(buf[1]);
            if (cmd.equals("insert")) {
                if (!search(tree, value)) {
                    tree = insert(tree, value);
                }
            }
            if (cmd.equals("exists")) {
                if (search(tree, value)) out.write("true\n");
                else out.write("false\n");
            }
            if (cmd.equals("next")) {
                Node tmp = next(tree, value);
                if (tmp == null) out.write("none\n");
                else out.write(String.format("%d\n", tmp.key));
            }
            if (cmd.equals("prev")) {
                Node tmp = prev(tree, value);
                if (tmp == null) out.write("none\n");
                else out.write(String.format("%d\n", tmp.key));
            }
            if (cmd.equals("delete")) tree = delete(tree, value);
        }
        in.close();
        out.close();
    }
}
