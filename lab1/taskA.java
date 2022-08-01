import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskA {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("aplusb.in"));
        int a = in.nextInt(), b = in.nextInt();
        FileWriter writer = new FileWriter("aplusb.out");
        writer.write(String.valueOf(a + b));
        in.close();
        writer.close();
    }
}
