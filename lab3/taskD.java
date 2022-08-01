import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class taskD {

    static class indexedValue {
        int value;
        int index;

        public indexedValue(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static ArrayList<indexedValue> heap = new ArrayList<>();

    static void siftUp(int index) {
        while (heap.get(index).value < heap.get((index - 1) / 2).value && index != 0) {
            Collections.swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    static void siftDown(int index) {
        int left, right, j;
        while (2 * index + 1 < heap.size()) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            j = left;
            if (right < heap.size() && heap.get(right).value < heap.get(left).value) {
                j = right;
            }
            if (heap.get(index).value <= heap.get(j).value) break;
            Collections.swap(heap, index, j);
            index = j;
        }
    }

    static int extractMin() {
        int min = heap.get(0).value;
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        siftDown(0);
        return min;
    }

    static void decreaseKey(int index, int value) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).index == index) {
                heap.set(i, new indexedValue(value, heap.get(i).index));
                siftUp(i);
                break;
            }
        }
    }

    static void insert(int value, int cmdCnt) {
        heap.add(new indexedValue(value, cmdCnt));
        siftUp(heap.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("priorityqueue.in"));
        FileWriter writer = new FileWriter("priorityqueue.out");
        int cmdCnt = 1;
        while (in.hasNext()) {
            String cmd = in.next();
            if (cmd.equals("push")) {
                int value = in.nextInt();
                insert(value, cmdCnt);
            }
            if (cmd.equals("extract-min")) {
                if (heap.isEmpty()) writer.write("*\n");
                else writer.write(extractMin() + "\n");
            }
            if (cmd.equals("decrease-key")) {
                int index = in.nextInt();
                int replacement = in.nextInt();
                decreaseKey(index, replacement);
            }
            cmdCnt++;
        }
        in.close();
        writer.close();
    }
}