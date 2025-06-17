package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByEye {

    public static Vector<String[]> sortByEyeAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[9].compareTo(b[9]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByEyeDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByEyeAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
