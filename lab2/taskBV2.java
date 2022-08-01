import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskBV2 {

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
        String racer;

        public Country(String countryName, String racer) {
            this.countryName = countryName;
            this.racer = racer;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("race.in"));
        int racerCnt = in.nextInt();
        Country[] data = new Country[racerCnt];
        for (int i = 0; i < racerCnt; i++) {
            String country = in.next(), name = in.next();
            data[i] = new Country(country, name);
        }
        Country[] a = new Country[data.length];
        mergeSort(data, data.length);
        FileWriter writer = new FileWriter("race.out");
        writer.write("=== " + data[0].countryName + " ===\n" + data[0].racer + '\n');
        for (int i = 1; i < data.length; i++) {
            if (!data[i].countryName.equals(data[i - 1].countryName)) {
                writer.write("=== " + data[i].countryName + " ===\n");
            }
            writer.write(data[i].racer + '\n');
        }
        in.close();
        writer.close();
    }
}

