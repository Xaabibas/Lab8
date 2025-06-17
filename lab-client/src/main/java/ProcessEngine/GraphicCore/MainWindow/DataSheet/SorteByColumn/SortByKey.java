package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByKey {

    public static Vector<String[]> sortByKeyAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByKeyDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByKeyAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
