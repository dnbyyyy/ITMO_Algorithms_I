import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class taskA {

    static class Set {

        ArrayList<Integer>[] hashTable = new ArrayList[100001];

        int hash(int key) {
            return Math.abs(key % 100001);
        }

        void insert(int key) {
            int index = hash(key);
            if (hashTable[index] == null) hashTable[index] = new ArrayList<>();
            hashTable[index].add(key);
        }

        void delete(int key, int index) {
            int tableIndex = hash(key);
            hashTable[tableIndex].remove(index);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader =  new BufferedReader(new FileReader("set.in"));
        FileWriter writer = new FileWriter("set.out");
        Set set = new Set();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] buf = line.split(" ");
            String cmd = buf[0];
            int key = Integer.parseInt(buf[1]);
            boolean exists = false;
            int index = -1;
            if (set.hashTable[set.hash(key)] != null) {
                for (int i = 0; i < set.hashTable[set.hash(key)].size(); i++) {
                    if (set.hashTable[set.hash(key)].get(i) == key) {
                        exists = true;
                        index = i;
                        break;
                    }
                }
            }
            if (cmd.equals("exists")) writer.write(exists ? "true\n" : "false\n");
            if (cmd.equals("insert") && !exists) set.insert(key);
            if (cmd.equals("delete") && index >= 0) set.delete(key, index);
        }
        reader.close();
        writer.close();
    }
}
