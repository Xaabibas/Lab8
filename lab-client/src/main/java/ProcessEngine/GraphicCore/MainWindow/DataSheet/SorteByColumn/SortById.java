package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;

public class SortById {

    public static Vector<String[]> sortByIdAscendingOrder(Vector<String[]> arr) {
        int n = arr.size();
        boolean swapped;
        for (int i = 0; i < n - 1; ++i) {
            swapped = false;
            for (int ii = 0; ii < n - i - 1; ++ii) {
                if (Integer.parseInt(arr.get(ii)[1]) < Integer.parseInt(arr.get(ii + 1)[1])) {
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

    public static Vector<String[]> sortByIdDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByIdAscendingOrder(arr));
        return arr;
    }

}
