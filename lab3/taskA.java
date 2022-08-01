import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskA {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("isheap.in"));
        int n = in.nextInt();
        int[] data = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            data[i] = in.nextInt();
        }
        FileWriter writer = new FileWriter("isheap.out");
        for (int i = 1; i <= n; i++){
            if((i * 2 <= n && data[i] > data[i * 2]) || (i * 2 + 1 <= n) && (data[i] > data[i * 2 + 1])){
                writer.write("NO");
                writer.close();
                in.close();
                return;
            }
        }
        writer.write("YES");
        writer.close();
        in.close();
    }
}