package resources;

import java.util.ListResourceBundle;

public class Resource_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                // Start
                {"sign up", "Register"},
                {"sign in", "Log in"},
                {"already have account?", "Already have an account?"},
                {"yet have not account?", "Don't have an account yet?"},
                {"login", "Username"},
                {"password", "Password"},

                // Buttons
                {"continue", "Continue"},
                {"language", "Language"},
                {"log out", "Log out"},
                {"table view", "Table View"},
                {"visualization", "Visualisation"},
                {"count by type", "Count by Type"},
                {"update", "Update"},
                {"sum", "Total Ticket Price"},
                {"insert", "Insert"},
                {"remove", "Remove"},
                {"lower", "Lower"},
                {"greater", "Greater"},
                {"by key", "By Key"},
                {"count by type", "Count by Type"},
                {"print ascending", "Print in Ascending Order"},
                {"clear", "Clear"},

                // Count by type
                {"count!", "Count!"},
                {"elements of type", "Count of this type "},
                {"enter type", "Enter the type you want to count"},

                // Insert and table
                {"insert data", "Enter the values"},
                {"key", "Key"},
                {"creation date", "Creation Date"},
                {"name", "Name"},
                {"price", "Price"},
                {"type", "Type"},
                {"birthday", "Date of Birth"},
                {"person data", "Information"},
                {"country", "Country"},
                {"eye color", "Eye Colour"},
                {"hair color", "Hair Colour"},
                {"success insert", "Item was successfully added"},
                // Update
                {"success update", "Item was successfully updated"},
                // Info
                {"info", "Information"},
                {"owner", "Owner"},

                // Removes
                {"insert price", "Enter the price"},
                {"insert key", "Enter the key"},
                {"remove lower", "Lower items have been removed"},
                {"remove greater", "Greater items have been removed"},
                {"remove this?", "Remove this item?"},
                {"success remove", "Item was successful removed"},

                // Another
                {"yes", "Yes"},

                // Errors
                {"insert correct values", "Please enter valid values"},
                {"insert all values", "Please fill in all fields"},
                {"wrong login or password", "Incorrect username or password"},
                {"one more user", "A user with this name is already registered"},
                {"not enough rights", "Insufficient access rights"},
                {"insert correct type", "Enter a valid type value"},
                {"no key", "No item found with this key"},

                // Languages
                {"russian", "Russian"},
                {"island", "Icelandic"},
                {"latish", "Latvian"},
                {"english", "English (Ireland)"}
        };
    }
}
