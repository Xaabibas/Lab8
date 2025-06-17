package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Vector;
import java.util.Collections;

public class SortByType {

    public static Vector<String[]> sortByTypeAscendingOrder(Vector<String[]> arr) {
        int n = arr.size();
        boolean swapped;
        for (int i = 0; i < n - 1; ++i) {
            swapped = false;
            for (int ii = 0; ii < n - i - 1; ++ii) {
                if (arr.get(ii)[7].compareTo(arr.get(ii + 1)[7]) > 0) {
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

    public static Vector<String[]> sortByTypeDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByTypeAscendingOrder(arr));
        return arr;
    }
    
}
