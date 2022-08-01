import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskB {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("aplusbb.in"));
        long a = in.nextLong(), b = in.nextLong();
        FileWriter writer = new FileWriter("aplusbb.out");
        writer.write(String.valueOf(a + b*b));
        in.close();
        writer.close();
    }
}
