package resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Formatter;
import java.util.ResourceBundle;

public class Localizator {
    private ResourceBundle bundle;
    private DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendPattern("[.SSSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSS][.SS][.S]").toFormatter();

    public Localizator(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String text) {
        return bundle.getString(text);
    }

    public String getDate(String value) {
        if (value == null) {
            return null;
        }
        LocalDateTime date = LocalDateTime.parse(value.replace("T", " "), formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(bundle.getLocale());
        return date.format(formatter1);
    }
}
