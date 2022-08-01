import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskD {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("antiqs.in"));
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            int tmp = data[i];
            data[i] = data[i / 2];
            data[i / 2] = tmp;
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != n - 1) buf.append(data[i]).append(" ");
            else buf.append(data[i]);
        }
        String antiQS = buf.toString();
        FileWriter writer = new FileWriter("antiqs.out");
        writer.write(antiQS);

        in.close();
        writer.close();
    }
}
