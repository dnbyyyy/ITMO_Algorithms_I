import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskE {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("sortland.in"));
        int residentsCnt = in.nextInt();
        double[] data = new double[residentsCnt];
        double[] dataCopy = new double[residentsCnt];
        for (int i = 0; i < residentsCnt; i++) {
            data[i] = in.nextDouble();
            dataCopy[i] = data[i];
        }

        for (int i = 0; i < residentsCnt - 1; i++) {                //bubble sort
            for (int j = 0; j < residentsCnt - i - 1; j++) {
                if (data[j] > data[j + 1]){
                    double tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        int minId = 0, avgId = 0, maxId = 0;
        for (int i = 0; i < residentsCnt; i++) {
            if (dataCopy[i] == data[0]) minId = i + 1;
            if (dataCopy[i] == data[residentsCnt / 2]) avgId = i + 1;
            if (dataCopy[i] == data[residentsCnt - 1]) maxId = i + 1;
        }
        String answer = minId + " " + avgId + " " + maxId;
        FileWriter writer = new FileWriter("sortland.out");
        writer.write(answer);
        in.close();
        writer.close();
    }
}
