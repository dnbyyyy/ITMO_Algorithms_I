import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskE {

    static int binarySearchLeft(String[] data, int key) {
        int left = -1, right = data.length;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (Integer.parseInt(data[mid]) < key) {
                left = mid;
            }
            else right = mid;
        }
        if (right < data.length && Integer.parseInt(data[right]) == key) return right + 1;
        return -1;
    }

    static int binarySearchRight(String[] data, int key) {
        int left = 0, right = data.length;
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (Integer.parseInt(data[mid]) > key) right = mid;
            else left = mid;
        }
        if (Integer.parseInt(data[left]) == key) return left + 1;
        else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("binsearch.in"));
        int n = Integer.parseInt(reader.readLine());
        String[] data = reader.readLine().split(" ");
        int m = Integer.parseInt(reader.readLine());
        String[] requests = reader.readLine().split(" ");
        FileWriter writer = new FileWriter("binsearch.out");
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(requests[i]);
            writer.write(String.format("%d %d\n", binarySearchLeft(data, key), binarySearchRight(data, key)));
        }
        writer.close();
        reader.close();
    }
}
