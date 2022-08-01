import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskAQuickSort {

    public static void quickSort(int[] array, int low, int high) {

        if (array.length == 0) return;

        if (low >= high) return;

        int pivotId = low + (high - low) / 2;
        int pivotValue = array[pivotId];

        int i = low, j = high;

        while (i <= j) {

            while (array[i] < pivotValue) i++;

            while (array[j] > pivotValue) j--;

            if (i <= j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }

        }

        if (low < j) quickSort(array, low, j);

        if (high > i) quickSort(array, i, high);
    }

    public static void main(String[] args) throws IOException {

        FileReader reader = new FileReader("sort.in");
        Scanner in = new Scanner(reader);

        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }

        int low = 0, high = n - 1;
        quickSort(data, low, high);

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != n - 1) buf.append(data[i]).append(" ");
            else buf.append(data[i]);
        }
        String sortedDataStr = buf.toString();
        FileWriter writer = new FileWriter("sort.out");
        writer.write(sortedDataStr);

        in.close();
        writer.close();
    }
}
