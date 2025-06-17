package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByPrice {

    public static Vector<String[]> sortByPriceAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> Float.compare(Float.parseFloat(a[6]), Float.parseFloat(b[6])))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByPriceDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByPriceAscendingOrder(arr));
        return arr;
    }

}
