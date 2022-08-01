import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskB {

    private static class Queue {

        private int tail = 0;

        private int head = 0;

        private int[] data = new int[256];

        private void reallocate() {
            int[] buf = data;
            data = new int[2 * data.length];
            System.arraycopy(buf, 0, data, 0, buf.length);
        }

        private void push(int n){
            if (tail + 1 >= data.length) {
                reallocate();
            }
            data[tail] = n;
            tail++;
        }

        private int pop(){
            head++;
            return data[head - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("queue.in"));
        int n = Integer.parseInt(reader.readLine());
        FileWriter writer = new FileWriter("queue.out");
        Queue queue = new Queue();
        for (int i = 0; i < n; i++) {
            String[] cmd = reader.readLine().split(" ");
            if (cmd[0].equals("+")) {
                queue.push(Integer.parseInt(cmd[1]));
            }
            if (cmd[0].equals("-")) {
                writer.write(String.format("%d\n", queue.pop()));
            }
        }
        reader.close();
        writer.close();
    }
}
