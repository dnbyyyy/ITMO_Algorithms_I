import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskA {

    private static class Stack {

        private int tail = 0;

        private int[] data = new int[256];

        private void reallocate() {
            int[] buf = data;
            data = new int[data.length + data.length / 2];
            System.arraycopy(buf, 0, data, 0, buf.length);
        }

        private void push(int n){
            if (tail + 1 >= data.length) {
                reallocate();
            }
            data[tail] = n;
            tail++;
        }

        private int pop() {
            int deleted = data[tail - 1];
            tail--;
            return deleted;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("stack.in"));
        int n = Integer.parseInt(reader.readLine());
        Stack stack = new Stack();
        FileWriter writer = new FileWriter("stack.out");
        for (int i = 0; i < n; i++) {
            String[] cmd = reader.readLine().split(" ");
            if (cmd[0].equals("+")) {
                stack.push(Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("-")) {
                writer.write(String.format("%d\n", stack.pop()));
            }
        }
        reader.close();
        writer.close();
    }

}
