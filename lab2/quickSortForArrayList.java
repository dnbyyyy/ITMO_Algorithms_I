import java.util.ArrayList;
import java.util.Collections;

public class quickSortForArrayList {
    public static void quickSort(ArrayList<String> data, int low, int high) {
        if (data.size() == 0) return;
        if (low >= high) return;
        int pivotId = low + (high - low) / 2;
        String pivotValue = data.get(pivotId);
        int i = low, j = high;
        while (i <= j) {
            while (data.get(i).compareTo(pivotValue) < 0) i++;

            while (data.get(i).compareTo(pivotValue) > 0) j--;

            if (i <= j) {
                Collections.swap(data, i, j);
                i++;
                j--;
            }
        }
        if (low < j) quickSort(data, low, j);
        if (high > i) quickSort(data, i, high);
    }
}
