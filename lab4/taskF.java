import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class taskF {

    static double findB(double[] h, int n) {
        double left = 0;
        double right = h[0];

        while (right - left > 0.0000000001) {
            h[1] = (left + right) / 2;
            boolean flag = true;

            for (int i = 2; i < n; i++) {
                h[i] = 2 * h[i - 1] - h[i - 2] + 2;

                if (h[i] < 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) right = h[1];
            else left = h[1];
        }
        return h[n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("garland.in"));
        String[] data = reader.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        double[] h = new double[n];
        h[0] = Double.parseDouble(data[1]);

        double b = findB(h, n);

        FileWriter writer = new FileWriter("garland.out");
        writer.write(String.format("%.2f", b));
        writer.close();
        reader.close();
        }
}
