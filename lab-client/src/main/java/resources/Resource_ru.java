package resources;

import java.util.ListResourceBundle;

public class Resource_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                // Start
                {"sign up", "Регистрация"},
                {"sign in", "Войти"},
                {"already have account?", "Уже есть аккаунт?"},
                {"yet have not account?", "Еще нет аккаунта?"},
                {"login", "Имя пользователя"},
                {"password", "Пароль"},

                // Buttons
                {"continue", "Продолжить"},
                {"language", "Язык"},
                {"log out", "Выйти"},
                {"table view", "Таблица"},
                {"visualization", "Визуализация"},
                {"count by type", "Подсчет по типу"},
                {"update", "обновить"},
                {"sum", "Сумма ц. б."},
                {"insert", "Вставить"},
                {"remove", "Удалить "},
                {"lower", "мен. эл."},
                {"greater", "бол. эл."},
                {"by key", "по кл."},
                {"count by type", "Подсчет по типу"},
                {"print ascending", "Выв. по пор."},
                {"clear", "Очистить"},

                // Count by type
                {"count!", "Посчитать!"},
                {"elements of type", "Элементов типа "},
                {"enter type", "Введите значение типа, количество которого хотите подсчитать"},

                // Insert and table
                {"insert data", "Введите значения"},
                {"key", "Ключ"},
                {"creation date", "Дт. созд."},
                {"name", "Имя"},
                {"price", "Цена"},
                {"type", "Тип"},
                {"birthday", "Дт. рож."},
                {"person data", "Информация"},
                {"country", "Страна"},
                {"eye color", "Ц. глз"},
                {"hair color", "Ц. вол."},
                {"success insert", "Элемент был успешно добавлен"},
                // Update
                {"success update", "Элемент был успешно обновлен"},
                // Info
                {"info", "Информация"},
                {"owner", "Владелец"},

                // Removes
                {"insert price", "Введите цену"},
                {"insert key", "Введите ключ"},
                {"remove lower", "Меньшие элементы были удалены"},
                {"remove greater", "Большие элементы были удалены"},
                {"remove this?", "Удалить данный элемент?"},
                {"success remove", "Элемент успешно удален"},

                // Another
                {"yes", "Да"},

                // Errors
                {"insert correct values", "Введите корректные значения"},
                {"insert all values", "Введите значения всех полей"},
                {"wrong login or password", "Неверное имя пользователя или пароль"},
                {"one more user", "Пользователь с таким именем уже зарегистрирован"},
                {"not enough rights", "Нет прав доступа"},
                {"insert correct type", "Введите корректное значение типа"},
                {"no key", "Нет элемента с таким ключом"},

                // Languages
                {"russian", "Русский"},
                {"island", "Исландский"},
                {"latish", "Латышский"},
                {"english", "Английский (Ирландия)"}
        };
    }
}
