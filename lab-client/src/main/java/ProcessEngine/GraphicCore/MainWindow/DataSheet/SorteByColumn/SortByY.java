package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByY {

    public static Vector<String[]> sortByYAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Double.compare(Double.parseDouble(a[4]), Double.parseDouble(b[4])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByYDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByYAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
