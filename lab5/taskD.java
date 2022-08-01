import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class taskD {

    static final int modulo = 65535;

    static int[] registers = new int[26];
    static ArrayList<String> commands = new ArrayList<>();

    static class Label {
        String cmd;
        int id;

        public Label(String cmd, int id) {
            this.cmd = cmd;
            this.id = id;
        }
    }

    static int n = 0, l = 0;

    static ArrayList<Label> labels = new ArrayList<>();

    static Queue<Integer> queue = new LinkedList<>();

    static int jump(String label) {
        for (int i = 0; i < l; i++) {
            if (labels.get(i).cmd.equals(label)) return labels.get(i).id;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("quack.in"));
        FileWriter out = new FileWriter("quack.out");

        while (in.hasNext()) {
            String cmd = in.nextLine();
            commands.add(cmd);
            n++;
        }

        for (int i = 0; i < n; i++) {
            if (commands.get(i).charAt(0) == ':') {
                labels.add(new Label(commands.get(i).substring(1), i));
                l++;
            }
        }

        int i = 0, a, b, x;
        char reg;
        boolean run = true;
        String label;

        while (i < n) {
            String cmd = commands.get(i);

            switch (cmd.charAt(0)) {
                case '+':
                    a = queue.remove();
                    b = queue.remove();
                    x = (a + b) & (modulo);
                    queue.add(x);
                    break;
                case '-':
                    a = queue.remove();
                    b = queue.remove();
                    x = (a - b) & (modulo);
                    queue.add(x);
                    break;
                case '*':
                    a = queue.remove();
                    b = queue.remove();
                    x = (a * b) & (modulo);
                    queue.add(x);
                    break;
                case '/':
                    a = queue.remove();
                    b = queue.remove();
                    if (b == 0) x = 0;
                    else x = (a / b) & (modulo);
                    queue.add(x);
                    break;
                case '%':
                    a = queue.remove();
                    b = queue.remove();
                    if (b == 0) x = 0;
                    else x = (a % b) & (modulo);
                    queue.add(x);
                    break;
                case '>':
                    reg = cmd.charAt(1);
                    x = queue.remove();
                    registers[reg - 'a'] = x;
                    break;
                case '<':
                    reg = cmd.charAt(1);
                    x = registers[reg - 'a'];
                    queue.add(x);
                    break;
                case 'P':
                    if (cmd.length() == 1) {
                        x = queue.remove();
                    } else {
                        reg = cmd.charAt(1);
                        x = registers[reg - 'a'];
                    }
                    out.write(String.valueOf(x) + '\n');
                    break;
                case 'C':
                    if (cmd.length() == 1) {
                        x = queue.remove();
                    } else {
                        reg = cmd.charAt(1);
                        x = registers[reg - 'a'];
                    }
                    out.write((char) (x % 256));
                    break;
                case ':':
                    break;
                case 'J':
                    label = commands.get(i).substring(1);
                    i = jump(label);
                    break;
                case 'Z':
                    reg = cmd.charAt(1);
                    label = commands.get(i).substring(2);
                    if (registers[reg - 'a'] == 0) i = jump(label);
                    break;
                case 'E':
                    label = commands.get(i).substring(3);
                    if (registers[cmd.charAt(1) - 'a'] == registers[cmd.charAt(2) - 'a'])
                        i = jump(label);
                    break;
                case 'G':
                    label = commands.get(i).substring(3);
                    if (registers[cmd.charAt(1) - 'a'] > registers[cmd.charAt(2) - 'a'])
                        i = jump(label);
                    break;
                case 'Q':
                    run = false;
                    break;
                default:
                    x = Integer.parseInt(cmd);
                    queue.add(x);
                    break;
            }

            i++;

            if (!run)
                break;
            }
        in.close();
        out.close();
    }
}
