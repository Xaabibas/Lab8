package ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn;

import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import javafx.scene.control.Button;

public class ColumnSortFlag {
    
    public static void setColumnSortFlag(
            String columnFlag,
            Button headerKeyButton,
            Button headerIdButton,
            Button headerNameButton,
            Button headerXButton,
            Button headerYButton,
            Button headerCreationDateButton,
            Button headerPriceButton,
            Button headerTypeButton,
            Button headerBirthdayButton,
            Button headerEyeButton,
            Button headerHairButton,
            Button headerCountryButton
    ) {
        if (columnFlag.equals("keyFlagSort")) {
            if (DataSheet.keyFlagSort == null) {
                DataSheet.keyFlagSort = true;
                headerKeyButton.setText("↓");
            } else if (DataSheet.keyFlagSort) {
                headerKeyButton.setText("↑");
                DataSheet.keyFlagSort = false;
            } else if (!DataSheet.keyFlagSort) {
                headerKeyButton.setText("⇅");
                DataSheet.keyFlagSort = null;
            }
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("idFlagSort")) {
            if (DataSheet.idFlagSort == null) {
                DataSheet.idFlagSort = true;
                headerIdButton.setText("↓");
            } else if (DataSheet.idFlagSort) {
                headerIdButton.setText("↑");
                DataSheet.idFlagSort = false;
            } else if (!DataSheet.idFlagSort) {
                headerIdButton.setText("⇅");
                DataSheet.idFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("nameFlagSort")) {
            if (DataSheet.nameFlagSort == null) {
                DataSheet.nameFlagSort = true;
                headerNameButton.setText("↓");
            } else if (DataSheet.nameFlagSort) {
                headerNameButton.setText("↑");
                DataSheet.nameFlagSort = false;
            } else if (!DataSheet.nameFlagSort) {
                headerNameButton.setText("⇅");
                DataSheet.nameFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("xFlagSort")) {
            if (DataSheet.xFlagSort == null) {
                DataSheet.xFlagSort = true;
                headerXButton.setText("↓");
            } else if (DataSheet.xFlagSort) {
                headerXButton.setText("↑");
                DataSheet.xFlagSort = false;
            } else if (!DataSheet.xFlagSort) {
                headerXButton.setText("⇅");
                DataSheet.xFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("yFlagSort")) {
            if (DataSheet.yFlagSort == null) {
                DataSheet.yFlagSort = true;
                headerYButton.setText("↓");
            } else if (DataSheet.yFlagSort) {
                headerYButton.setText("↑");
                DataSheet.yFlagSort = false;
            } else if (!DataSheet.yFlagSort) {
                headerYButton.setText("⇅");
                DataSheet.yFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("creationDateFlagSort")) {
            if (DataSheet.creationDateFlagSort == null) {
                DataSheet.creationDateFlagSort = true;
                headerCreationDateButton.setText("↓");
            } else if (DataSheet.creationDateFlagSort) {
                headerCreationDateButton.setText("↑");
                DataSheet.creationDateFlagSort = false;
            } else if (!DataSheet.creationDateFlagSort) {
                headerCreationDateButton.setText("⇅");
                DataSheet.creationDateFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("priceFlagSort")) {
            if (DataSheet.priceFlagSort == null) {
                DataSheet.priceFlagSort = true;
                headerPriceButton.setText("↓");
            } else if (DataSheet.priceFlagSort) {
                headerPriceButton.setText("↑");
                DataSheet.priceFlagSort = false;
            } else if (!DataSheet.priceFlagSort) {
                headerPriceButton.setText("⇅");
                DataSheet.priceFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("typeFlagSort")) {
            if (DataSheet.typeFlagSort == null) {
                DataSheet.typeFlagSort = true;
                headerTypeButton.setText("↓");
            } else if (DataSheet.typeFlagSort) {
                headerTypeButton.setText("↑");
                DataSheet.typeFlagSort = false;
            } else if (!DataSheet.typeFlagSort) {
                headerTypeButton.setText("⇅");
                DataSheet.typeFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("birthdayFlagSort")) {
            if (DataSheet.birthdayFlagSort == null) {
                DataSheet.birthdayFlagSort = true;
                headerBirthdayButton.setText("↓");
            } else if (DataSheet.birthdayFlagSort) {
                headerBirthdayButton.setText("↑");
                DataSheet.birthdayFlagSort = false;
            } else if (!DataSheet.birthdayFlagSort) {
                headerBirthdayButton.setText("⇅");
                DataSheet.birthdayFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("eyeFlagSort")) {
            if (DataSheet.eyeFlagSort == null) {
                DataSheet.eyeFlagSort = true;
                headerEyeButton.setText("↓");
            } else if (DataSheet.eyeFlagSort) {
                headerEyeButton.setText("↑");
                DataSheet.eyeFlagSort = false;
            } else if (!DataSheet.eyeFlagSort) {
                headerEyeButton.setText("⇅");
                DataSheet.eyeFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerHairButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.hairFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("hairFlagSort")) {
            if (DataSheet.hairFlagSort == null) {
                DataSheet.hairFlagSort = true;
                headerHairButton.setText("↓");
            } else if (DataSheet.hairFlagSort) {
                headerHairButton.setText("↑");
                DataSheet.hairFlagSort = false;
            } else if (!DataSheet.hairFlagSort) {
                headerHairButton.setText("⇅");
                DataSheet.hairFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerCountryButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.countryFlagSort = null;
        } else if (columnFlag.equals("countryFlagSort")) {
            if (DataSheet.countryFlagSort == null) {
                DataSheet.countryFlagSort = true;
                headerCountryButton.setText("↓");
            } else if (DataSheet.countryFlagSort) {
                headerCountryButton.setText("↑");
                DataSheet.countryFlagSort = false;
            } else if (!DataSheet.countryFlagSort) {
                headerCountryButton.setText("⇅");
                DataSheet.countryFlagSort = null;
            }
            headerKeyButton.setText("⇅");
            headerIdButton.setText("⇅");
            headerNameButton.setText("⇅");
            headerXButton.setText("⇅");
            headerYButton.setText("⇅");
            headerCreationDateButton.setText("⇅");
            headerPriceButton.setText("⇅");
            headerTypeButton.setText("⇅");
            headerBirthdayButton.setText("⇅");
            headerEyeButton.setText("⇅");
            headerHairButton.setText("⇅");
            DataSheet.keyFlagSort = null;
            DataSheet.idFlagSort = null;
            DataSheet.nameFlagSort = null;
            DataSheet.xFlagSort = null;
            DataSheet.yFlagSort = null;
            DataSheet.creationDateFlagSort = null;
            DataSheet.priceFlagSort = null;
            DataSheet.typeFlagSort = null;
            DataSheet.birthdayFlagSort = null;
            DataSheet.eyeFlagSort = null;
            DataSheet.hairFlagSort = null;
        }
    }

}
