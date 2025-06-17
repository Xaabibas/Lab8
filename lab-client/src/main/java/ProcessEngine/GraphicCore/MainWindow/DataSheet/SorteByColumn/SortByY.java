package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByY {

    public static Vector<String[]> sortByYAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Float.compare(Float.parseFloat(a[4]), Float.parseFloat(b[4])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByYDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByYAscendingOrder(arr));
        return arr;
    }

}
