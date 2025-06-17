package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByX {

    public static Vector<String[]> sortByXAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Float.compare(Float.parseFloat(a[3]), Float.parseFloat(b[3])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByXDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByXAscendingOrder(arr));
        return arr;
    }

}
