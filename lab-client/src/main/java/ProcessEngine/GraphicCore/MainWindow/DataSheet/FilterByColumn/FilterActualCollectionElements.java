package ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn;

import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;

import java.util.Vector;
import java.util.stream.Collectors;

public class FilterActualCollectionElements {

    public static Vector<String[]> filterActualCollectionElements(Vector<String[]> collectionData) {

        Vector<String[]> filteredVector = collectionData.stream()
            .filter(el -> DataSheet.filteredKeyElementsSet.isEmpty() || DataSheet.filteredKeyElementsSet.contains(el[0]))
            .filter(el -> DataSheet.filteredIdElementsSet.isEmpty() || DataSheet.filteredIdElementsSet.contains(el[1]))
            .filter(el -> DataSheet.filteredNameElementsSet.isEmpty() || DataSheet.filteredNameElementsSet.contains(el[2]))
            .filter(el -> DataSheet.filteredXElementsSet.isEmpty() || DataSheet.filteredXElementsSet.contains(el[3]))
            .filter(el -> DataSheet.filteredYElementsSet.isEmpty() || DataSheet.filteredYElementsSet.contains(el[4]))
            .filter(el -> DataSheet.filteredCreationDateElementsSet.isEmpty() || DataSheet.filteredCreationDateElementsSet.contains(el[5]))
            .filter(rl -> DataSheet.filteredPriceElementsSet.isEmpty() || DataSheet.filteredPriceElementsSet.contains(rl[6]))
            .filter(el -> DataSheet.filteredTypeElementsSet.isEmpty() || DataSheet.filteredTypeElementsSet.contains(el[7]))
            .filter(el -> DataSheet.filteredBirthdayElementsSet.isEmpty() || DataSheet.filteredBirthdayElementsSet.contains(el[8]))
            .filter(el -> DataSheet.filteredEyeElementsSet.isEmpty() || DataSheet.filteredEyeElementsSet.contains(el[9]))
            .filter(el -> DataSheet.filteredHairElementsSet.isEmpty() || DataSheet.filteredHairElementsSet.contains(el[10]))
            .filter(el -> DataSheet.filteredCountryElementsSet.isEmpty() || DataSheet.filteredCountryElementsSet.contains(el[11]))
            .collect(Collectors.toCollection(Vector::new));
        return filteredVector;

    }
    
}
