package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByType {

    public static Vector<String[]> sortByTypeAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[7].compareTo(b[7]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByTypeDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByTypeAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
