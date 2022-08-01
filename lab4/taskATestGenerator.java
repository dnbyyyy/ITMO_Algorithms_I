import java.io.FileWriter;
import java.io.IOException;

public class taskATestGenerator {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("stack.in");
        writer.write("1000000\n");
        for (int i = 0; i < 1000000; i++) {
            if ((i + 1) % 7 != 0) writer.write(String.format("+ %d\n", (int) (Math.random() * 1000000000)));
            if ((i + 1) % 7 == 0) writer.write("-\n");
        }
        writer.close();
    }
}
