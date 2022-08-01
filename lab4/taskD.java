import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskD {
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
        BufferedReader reader = new BufferedReader(new FileReader("postfix.in"));
        String[] cmd = reader.readLine().split(" ");
        reader.close();
        List stack = new List();
        for (String s : cmd) {
            if (Character.isDigit(s.charAt(0))) stack.add(Integer.parseInt(String.valueOf(s.charAt(0))));
            if (s.equals("+")) {
                int op1 = stack.remove(stack.size - 1);
                int op2 = stack.remove(stack.size - 1);
                int bufResult = op1 + op2;
                stack.add(bufResult);
            }
            if (s.equals("-")) {
                int op1 = stack.remove(stack.size - 1);
                int op2 = stack.remove(stack.size - 1);
                int bufResult = op2 - op1;
                stack.add(bufResult);
            }
            if (s.equals("*")) {
                int op1 = stack.remove(stack.size - 1);
                int op2 = stack.remove(stack.size - 1);
                int bufResult = op1 * op2;
                stack.add(bufResult);
            }
        }
        FileWriter writer = new FileWriter("postfix.out");
        writer.write(String.valueOf(stack.remove(stack.size - 1)));
        writer.close();
    }
}
