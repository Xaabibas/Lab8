package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByHair {

    public static Vector<String[]> sortByHairAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[10].compareTo(b[10]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByHairDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByHairAscendingOrder(arr));
        return arr;
    }

}
