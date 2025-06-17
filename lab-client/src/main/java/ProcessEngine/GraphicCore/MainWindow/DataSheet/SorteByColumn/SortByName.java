package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByName {

    public static Vector<String[]> sortByNameAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[2].compareTo(b[2]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByNameDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByNameDescendingOrder(arr));
        return arr;
    }

}
