package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByX {

    public static Vector<String[]> sortByXAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Double.compare(Double.parseDouble(a[3]), Double.parseDouble(b[3])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByXDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByXAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
