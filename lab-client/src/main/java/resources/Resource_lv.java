package resources;

import java.util.ListResourceBundle;

public class Resource_lv extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                // Start
                {"sign up", "Reģistrēties"},
                {"sign in", "Pieteikties"},
                {"already have account?", "Jau ir konts?"},
                {"yet have not account?", "Vēl nav konta?"},
                {"login", "Lietotājvārds"},
                {"password", "Parole"},

                // Buttons
                {"continue", "Turpināt"},
                {"language", "Valoda"},
                {"log out", "Izrakstīties"},
                {"table view", "Tabula"},
                {"visualization", "Vizualizācija"},
                {"count by type", "Skaits pēc veida"},
                {"update", "Atjaunināt"},
                {"sum", "Biļešu cenu summa"},
                {"insert", "Ievietot"},
                {"remove", "Dzēst"},
                {"lower", "Mazāki"},
                {"greater", "Lielāki"},
                {"by key", "Pēc atslēgas"},
                {"count by type", "Skaits pēc veida"},
                {"print ascending", "Drukāt augošā secībā"},
                {"clear", "Notīrīt"},

                // Count by type
                {"count!", "Skaitīt!"},
                {"elements of type", "Šāda elementi"},
                {"enter type", "Ievadiet tipu, kura daudzumu vēlaties saskaitīt"},

                // Insert and table
                {"insert data", "Ievadiet datus"},
                {"key", "Atslēga"},
                {"creation date", "Izveides datums"},
                {"name", "Vārds"},
                {"price", "Cena"},
                {"type", "Tips"},
                {"birthday", "Dzimšanas datums"},
                {"person data", "Informācija"},
                {"country", "Valsts"},
                {"eye color", "Acu krāsa"},
                {"hair color", "Matu krāsa"},
                {"success insert", "Elements veiksmīgi pievienots"},
                // Update
                {"success update", "Elements veiksmīgi atjaunināts"},

                // Removes
                {"insert price", "Ievadiet cenu"},
                {"insert key", "Ievadiet atslēgu"},
                {"remove lower", "Mazākie elementi tika dzēsti"},
                {"remove greater", "Lielākie elementi tika dzēsti"},
                {"remove this?", "Dzēst šo elementu?"},
                {"success remove", "Vienums ir veiksmīgi izdzēsts"},

                // Another
                {"yes", "Jā"},

                // Errors
                {"insert correct values", "Ievadiet pareizas vērtības"},
                {"insert all values", "Ievadiet visas vērtības"},
                {"wrong login or password", "Nepareizs lietotājvārds vai parole"},
                {"one more user", "Lietotājs ar šādu vārdu jau ir reģistrēts"},
                {"not enough rights", "Nav piekļuves tiesību"},
                {"insert correct type", "Ievadiet pareizu tipa vērtību"},
                {"no key", "Nav elementa ar šādu atslēgu"},

                // Languages
                {"russian", "Krievu"},
                {"island", "Islandešu"},
                {"latish", "Latviešu"},
                {"english", "Angļu (Īrija)"}
        };
    }
}
