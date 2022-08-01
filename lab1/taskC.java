import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskC {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("turtle.in"));
        int h = in.nextInt(), w = in.nextInt();
        int[][] data = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                data[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i < w; i++) {
            data[h - 1][i] += data[h - 1][i - 1];
        }
        for (int i = h - 2; i >= 0; i--) {
            data[i][0] += data[i + 1][0];
        }
        for (int i = h - 2; i >= 0; i--) {
            for (int j = 1; j < w; j++) {
                data[i][j] += Math.max(data[i + 1][j], data[i][j - 1]);
            }
        }
        FileWriter writer = new FileWriter("turtle.out");
        writer.write(Integer.toString(data[0][w - 1]));
        in.close();
        writer.close();
    }
}
