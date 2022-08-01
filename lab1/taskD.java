import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskD {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("sort.in"));
        int elementCnt = in.nextInt();
        int[] data = new int[elementCnt];
        for (int i = 0; i < elementCnt; i++) {
            data[i] = in.nextInt();
        }
        for (int i = 0; i < elementCnt - 1; i++) {          // bubble sort
            for (int j = 0; j < elementCnt - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    data[j + 1] += data[j];
                    data[j] = data[j + 1] - data[j];
                    data[j + 1] -= data[j];
                }
            }
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < elementCnt; i++) {
            if (i != elementCnt - 1) buf.append(data[i]).append(" ");
            else buf.append(data[i]);
        }
        String sortedDataStr = buf.toString();
        FileWriter writer = new FileWriter("sort.out");
        writer.write(sortedDataStr);
        in.close();
        writer.close();
    }
}
