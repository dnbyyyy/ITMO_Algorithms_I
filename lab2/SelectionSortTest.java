import java.util.Arrays;
import java.util.Scanner;

public class SelectionSortTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        for (int i = 0; i < n - 2; i++) {
            int minId = i;
            for (int j = i + 1; j < n - 1; j++) {
                if (data[j] < data[minId]) minId = j;
            }
            if (minId != i){
                int tmp = data[i];
                data[i] = data[minId];
                data[minId] = tmp;
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
