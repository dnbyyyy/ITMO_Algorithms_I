import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskAMergeSort {

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("sort.in"));

        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }

        mergeSort(data, n);

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
