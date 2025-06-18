package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortById {

    public static Vector<String[]> sortByIdAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Long.compare(Long.parseLong(a[1]), Long.parseLong(b[1])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByIdDescendingOrder(Vector<String[]> arr) {
        Vector<String[]> sorted = sortByIdAscendingOrder(arr);
        Collections.reverse(sorted);
        return sorted;
    }

}
