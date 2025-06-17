package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

public class SortByCountry {

    public static Vector<String[]> sortByCountryAscendingOrder(Vector<String[]> arr) {
        arr = arr.stream()
            .sorted((a, b) -> a[11].compareTo(b[11]))
            .collect(Collectors.toCollection(Vector::new));
        return arr;
    }

    public static Vector<String[]> sortByCountryDescendingOrder(Vector<String[]> arr) {
        Collections.reverse(sortByCountryAscendingOrder(arr));
        return arr;
    }

}
