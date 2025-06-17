package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByBirthday {

    public static Vector<String[]> sortByBirthdayAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[8].compareTo(b[8]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByBirthdayDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByBirthdayAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
