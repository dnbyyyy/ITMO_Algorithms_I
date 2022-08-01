import java.io.*;
import java.util.Scanner;

public class taskC {

    private static class List {

        private static class Node {

            Node prev;
            Node next;

            char value;

            public Node(Node prev, char value, Node next) {
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

        void add(char value) {

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

        void add(int index, char value) {
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

        char remove(int index) {
            if (size < 1 || index > size - 1){
                System.err.print("IndexOutOfBoundsException");
                return '0';
            }
            if (index == 0) {
                char deletedValue = first.value;
                first = first.next;
                size--;
                return deletedValue;
            }
            if (index == size - 1) {
                char deletedValue = last.value;
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

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileReader("brackets.in"));
        FileWriter writer = new FileWriter("brackets.out");
        while (in.hasNext()) {
            String brackets = in.nextLine();
            List opBrackets = new List();
            int flag = 0;
            for (int i = 0; i < brackets.length(); i++) {
                if (brackets.charAt(0) == ']' || brackets.charAt(0) == ')') {
                    writer.write("NO\n");
                    flag++;
                    break;
                }
                if (brackets.charAt(i) == '[' || brackets.charAt(i) == '(') {
                    opBrackets.add(brackets.charAt(i));
                }
                if (brackets.charAt(i) == ')' || brackets.charAt(i) == ']'){
                    if (brackets.charAt(i) == ')'){
                        if (opBrackets.size == 0){
                            writer.write("NO\n");
                            flag++;
                            break;
                        }
                        if (opBrackets.node(opBrackets.size - 1).value != '('){
                            writer.write("NO\n");
                            flag++;
                            break;
                        }
                        else opBrackets.remove(opBrackets.size - 1);
                    }

                    if (brackets.charAt(i) == ']'){
                        if (opBrackets.size == 0){
                            writer.write("NO\n");
                            flag++;
                            break;
                        }
                        if (opBrackets.node(opBrackets.size - 1).value != '['){
                            writer.write("NO\n");
                            flag++;
                            break;
                        }
                        else {
                            opBrackets.remove(opBrackets.size - 1);
                        }
                    }
                }
            }
            if (flag == 0 && opBrackets.size == 0) writer.write("YES\n");
            if (opBrackets.size > 0 && flag == 0) writer.write("NO\n");
            }
        writer.close();
        in.close();
    }
}
