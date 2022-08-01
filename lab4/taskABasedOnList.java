import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskABasedOnList {

    private static class List {

        private static class Node {

            Node prev;
            Node next;

            int value;

            public Node(Node prev, int value, Node next) {
                this.prev = prev;
                this.next = next;
                this.value = value;
            }
        }

        int size = 0;

        Node first;
        Node last;

        Node node(int index) {
            Node buf = first;
            for (int i = 0; i < index; i++) {
                buf = buf.next;
            }
            return buf;
        }

        void add(int value) {

            if (size == 0) {
                Node buf = new Node(null, value, null);
                first = buf;
                last = buf;
            }
            else {
                Node buf = new Node(last, value, null);
                last.next = buf;
                last = buf;
            }

            size++;
        }

        void add(int index, int value) {
            if (index > size) {
                System.err.print("IndexOutOfBoundsException");
                return;
            }
            if (index == size) add(value);
            if (index < size) {
                Node withNeededIndex = node(index);
                Node buf = new Node(withNeededIndex.prev, value, withNeededIndex);
                buf.prev.next = buf;
                withNeededIndex.prev = buf;
            }
            size++;
        }

        int remove(int index) {
            if (size < 1 || index > size - 1){
                System.err.print("IndexOutOfBoundsException");
                return -1;
            }
            if (index == 0) {
                int deletedValue = first.value;
                first = first.next;
                size--;
                return deletedValue;
            }
            if (index == size - 1) {
                int deletedValue = last.value;
                last = last.prev;
                last.next = null;
                size--;
                return deletedValue;
            }
            else {
                Node deleted = node(index);
                deleted.prev.next = deleted.next;
                deleted.next.prev = deleted.prev;
                size--;
                return deleted.value;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("stack.in"));
        List list = new List();
        FileWriter writer = new FileWriter("stack.out");
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] cmd = reader.readLine().split(" ");
            if (cmd[0].equals("+")) {
                list.add(Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("-")) {
                writer.write(String.format("%d\n", list.remove(list.size - 1)));
            }
        }
        reader.close();
        writer.close();
    }
}
