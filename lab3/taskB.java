import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class taskB {

    static void heapify(int[] data, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && data[left] > data[largest]) largest = left;

        if (right < n && data[right] > data[largest]) largest = right;

        if (largest != i) {
            int tmp = data[i];
            data[i] = data[largest];
            data[largest] = tmp;

            heapify(data, n, largest);
        }
    }

    static void heapSort(int[] data) {
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int tmp = data[0];
            data[0] = data[i];
            data[i] = tmp;

            heapify(data, i, 0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("sort.in"));
        int n = Integer.parseInt(reader.readLine());
        int[] data = new int[n];
        String[] buf = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(buf[i]);
        }
        heapSort(data);
        FileWriter writer = new FileWriter("sort.out");
        for (int datum : data) {
            writer.write(String.valueOf(datum) + " ");
        }
        reader.close();
        writer.close();
    }
}
