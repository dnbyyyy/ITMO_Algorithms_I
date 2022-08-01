import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskE {

    static void orderStats(int[] data, int left, int right, int k){
        int i = left;
        int j = right;
        int key = data[(right + left ) / 2];
        while(i <= j){
            while(data[i] < key)
                i++;
            while(data[j] > key)
                j--;
            if(i <= j) {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
                j--;
            }
        }
        if (j > left && k <= j)
            orderStats(data, left, j, k);
        if (i < right && k >= j)
            orderStats(data, j + 1, right, k);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("kth.in"));
        int n = in.nextInt(), k = in.nextInt();
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        int[] data = new int[n];
        data[0] = in.nextInt();
        if (n != 1) data[1] = in.nextInt();
        for (int i = 2; i < n; i++) {
            data[i] = a * data[i - 2] + b * data[i - 1] + c;
        }
        orderStats(data, 0, n - 1, k - 1);
        FileWriter writer = new FileWriter("kth.out");
        writer.write(String.valueOf(data[k - 1]));
        writer.close();
        in.close();
    }
}
