package resources;

import java.util.ListResourceBundle;

public class Resource_is extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                // Start
                {"sign up", "Nýskrá"},
                {"sign in", "Skrá inn"},
                {"already have account?", "Ertu með aðgang?"},
                {"yet have not account?", "Ertu ekki með aðgang?"},
                {"login", "Notandanafn"},
                {"password", "Lykilorð"},

                // Buttons
                {"language", "Tungumál"},
                {"log out", "Skrá út"},
                {"table view", "Töflusýn"},
                {"visualization", "Sýnigögn"},
                {"count by type", "Fjöldi eftir tegund"},
                {"update", "Uppfæra"},
                {"sum", "Heildarverð miða"},
                {"insert", "Setja inn"},
                {"remove", "Fjarlægja"},
                {"lower", "Minni"},
                {"greater", "Stærri"},
                {"by key", "Eftir lykli"},
                {"count by type", "Fjöldi eftir tegund"},
                {"print ascending", "Prenta í hækkandi röð"},
                {"clear", "Hreinsa"},

                // Count by type
                {"count!", "Telja!"},
                {"elements of type", "Þættir af gerð "},
                {"enter type", "Sláðu inn tegund sem þú vilt telja fjölda af"},

                // Insert and table
                {"continue", "Halda áfram"},
                {"insert data", "Sláðu inn gögn"},
                {"key", "Lykill"},
                {"creation date", "Búið til dagsetningu"},
                {"name", "Nafn"},
                {"price", "Verð"},
                {"type", "Tegund"},
                {"birthday", "Fæðingardagur"},
                {"person data", "Upplýsingar"},
                {"country", "Land"},
                {"eye color", "Augnlitur"},
                {"hair color", "Hárlitur"},
                {"success insert", "Hlutur hefur verið settur inn með góðum árangri"},
                // Update
                {"success update", "Hlutur hefur verið uppfærður með góðum árangri"},

                // Removes
                {"insert price", "Sláðu inn verð"},
                {"insert key", "Sláðu inn lykil"},
                {"remove lower", "Minni hlutir voru fjarlægðir"},
                {"remove greater", "Stærri hlutir voru fjarlægðir"},
                {"remove this?", "Fjarlægja þennan hlut?"},
                {"success remove", "Atriðið var eytt"},

                // Another
                {"yes", "Já"},

                // Errors
                {"insert correct values", "Sláðu inn réttar gildi"},
                {"insert all values", "Sláðu inn öll gildi"},
                {"wrong login or password", "Rangt notandanafn eða lykilorð"},
                {"one more user", "Notandi með þessu nafni er þegar skráður"},
                {"not enough rights", "Ekki með nægar heimildir"},
                {"insert correct type", "Sláðu inn rétta tegund"},
                {"no key", "Enginn hlutur með þessum lykli"},

                // Languages
                {"russian", "Rússneska"},
                {"island", "Íslenska"},
                {"latish", "Lettneska"},
                {"english", "Enska (Írland)"}
        };
    }
}
