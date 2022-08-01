import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class taskB {

    static class Pair {
        String key;
        String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("map.in"));
        FileWriter writer = new FileWriter("map.out");
        ArrayList<Pair>[] hash = new ArrayList[1009];
        String line, cmd, key, value;
        int a, num, index = 0;
        boolean t;
        while ((line = reader.readLine()) != null) {
            String[] buf = line.split(" ");
            cmd = buf[0];
            key = buf[1];
            num = 0;
            for (int i = key.length() - 1; i >= 0; i--) {
                num += ((int)(key.charAt(i))) * i;
            }
            a = num % 1009;
            t = false;
            if (hash[a] == null) hash[a] = new ArrayList<>();
            for (int i = 0; i < hash[a].size(); i++) {
                if (hash[a].get(i).key.equals(key)) {
                    t = true;
                    index = i;
                    break;
                }
            }
            if (cmd.equals("put")) {
                value = buf[2];
                if (t) hash[a].get(index).setValue(value);
                else hash[a].add(new Pair(key, value));
            }
            if (cmd.equals("get")) {
                if (t) writer.write(hash[a].get(index).value + "\n");
                else writer.write("none\n");
            }
            if (cmd.equals("delete") && t) {
                hash[a].remove(index);
            }
        }
        reader.close();
        writer.close();
    }
}