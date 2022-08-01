import java.io.FileWriter;
import java.io.IOException;

public class testWriterForBTask {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("race.in");
        writer.write("90000" + '\n');
        String[] countries = {"Russia", "USA", "Finland", "France"};
        for (int i = 0; i < 90000; i++) {
            writer.write(String.valueOf(i*10000) + " lol\n");
        }
    }
}
