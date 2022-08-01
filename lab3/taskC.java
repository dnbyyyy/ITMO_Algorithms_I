import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskC {

    static void radixSort(String[] data, int m, int k) {
        for (int i = m - 1; i >= m - k; i--) {
            for (int j = 1; j < data.length; j++) {
                int l = j - 1;
                while (l >= 0 && data[l].charAt(i) > data[l + 1].charAt(i)) {
                    String tmp = data[l];
                    data[l] = data[l + 1];
                    data[l + 1] = tmp;
                    l--;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("radixsort.in"));
        String[] cmd = reader.readLine().split(" ");
        String[] data = new String[Integer.parseInt(cmd[0])];
        for (int i = 0; i < data.length; i++) {
            data[i] = reader.readLine();
        }
        radixSort(data, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
        FileWriter writer = new FileWriter("radixsort.out");
        for (String datum : data) {
            writer.write(String.format("%s\n", datum));
        }
        writer.close();
        reader.close();
    }
}
