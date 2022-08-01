import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskB {

    public static void merge(
            Country[] a, Country[] l, Country[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].countryName.compareTo(r[j].countryName) <= 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void mergeSort(Country[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Country[] l = new Country[mid];
        Country[] r = new Country[n - mid];

        System.arraycopy(a, 0, l, 0, mid);
        if (n - mid >= 0) System.arraycopy(a, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private static class Country {
        String countryName;
        ArrayList<String> racers;

        public Country(String countryName, ArrayList<String> racers) {
            this.countryName = countryName;
            this.racers = racers;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("race.in"));
        int racerCnt = in.nextInt();
        ArrayList<Country> data = new ArrayList<>();
        for (int i = 0; i < racerCnt; i++) {
            String country = in.next(), name = in.next();
            boolean notInData = true;
            for (Country datum : data) {
                if (datum.countryName.equals(country)) {
                    datum.racers.add(name);
                    notInData = false;
                    break;
                }
            }
            if (notInData) {
                ArrayList<String> buf = new ArrayList<>();
                buf.add(name);
                data.add(new Country(country, buf));
            }
        }
        Country[] a = new Country[data.size()];
        for (int i = 0; i < data.size(); i++) {
            a[i] = data.get(i);
        }
        mergeSort(a, a.length);
        FileWriter writer = new FileWriter("race.out");
        for (Country country : a) {
            writer.write("=== " + country.countryName + " ===\n");
            for (int j = 0; j < country.racers.size(); j++) {
                writer.write(country.racers.get(j) + '\n');
            }
        }
        in.close();
        writer.close();
    }
}

