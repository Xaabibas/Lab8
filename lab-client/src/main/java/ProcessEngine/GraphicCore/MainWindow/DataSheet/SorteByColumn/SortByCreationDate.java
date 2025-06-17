package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByCreationDate {

    public static Vector<String[]> sortByCreationDateAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[5].compareTo(b[5]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByCreationDateDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByCreationDateAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
