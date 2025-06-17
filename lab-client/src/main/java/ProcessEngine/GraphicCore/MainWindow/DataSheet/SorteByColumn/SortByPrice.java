package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;

public class SortByPrice {

    public static Vector<String[]> sortByPriceAscendingOrder(Vector<String[]> arr) {
        int n = arr.size();
        boolean swapped;
        for (int i = 0; i < n - 1; ++i) {
            swapped = false;
            for (int ii = 0; ii < n - i - 1; ++ii) {
                if (Float.parseFloat(arr.get(ii)[6]) > Float.parseFloat(arr.get(ii + 1)[6])) {
                    String[] temporary = arr.get(ii);
                    arr.set(ii, arr.get(ii + 1));
                    arr.set(ii + 1, temporary);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return arr;
    }

    public static Vector<String[]> sortByPriceDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByPriceAscendingOrder(arr));
        return arr;
    }
    
}
