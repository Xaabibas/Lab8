package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Vector;
import java.util.Collections;

public class SortByX {

    public static Vector<String[]> sortByXAscendingOrder(Vector<String[]> arr) {
        int n = arr.size();
        boolean swapped;
        for (int i = 0; i < n - 1; ++i) {
            swapped = false;
            for (int ii = 0; ii < n - i - 1; ++ii) {
                if (Float.parseFloat(arr.get(ii)[3]) > Float.parseFloat(arr.get(ii + 1)[3])) {
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

    public static Vector<String[]> sortByXDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByXAscendingOrder(arr));
        return arr;
    }
    
}
