package managers;

import moduls.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class DataBaseManager {
    public final Logger logger = Logger.getLogger("DBLogger");
    private Connection connection;


    public void connect() {
        try {
            logger.info("Try to connect to DB");
            String url = "jdbc:postgresql://localhost:5432/studs";
            Properties info = new Properties();
            info.load(new FileReader("db.cfg"));

            connection = DriverManager.getConnection(url, info);
            logger.info("Successful connection to DB");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<Long, Ticket> readCollection() throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets;");
        ResultSet result = statement.executeQuery();

        LinkedHashMap<Long, Ticket> collection = new LinkedHashMap<>();

        while (result.next()) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").
            appendPattern("[.SSSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSS][.SS][.S]").toFormatter();
            Ticket ticket = new Ticket();
            ticket.setName(result.getString("name"));
            ticket.setId(result.getLong("id"));
            ticket.setCoordinates(new Coordinates(
                    result.getFloat("x"),
                    result.getLong("y")
            ));
            ticket.setCreationDate(
                LocalDateTime.parse(result.getTimestamp("creation").toString(), formatter)
            );
            ticket.setPrice(result.getFloat("price"));
            ticket.setType(result.getString("type") == null ? null : TicketType.valueOf(result.getString("type")));
            ticket.setPerson(new Person(
                    result.getTimestamp("birthday") == null ? null : LocalDateTime.parse(result.getTimestamp("birthday").toString(), formatter),
                    result.getString("eye") == null ? null : EyeColor.valueOf(result.getString("eye")),
                    result.getString("hair") == null ? null : HairColor.valueOf(result.getString("hair")),
                    result.getString("country") == null ? null : Country.valueOf(result.getString("country"))
            ));
            Long key = result.getLong("key");
            collection.put(key, ticket);
        }
        return collection;
    }

    public boolean clear() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE tickets;");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void insert(Long key, Ticket ticket) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO tickets " +
                "(key, name, x, y, price, type, birthday, eye, hair, country, creation) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        statement.setLong(1, key);
        statement.setString(2, ticket.getName());
        statement.setFloat(3, ticket.getCoordinates().getX());
        statement.setLong(4, ticket.getCoordinates().getY());
        statement.setFloat(5, ticket.getPrice());
        String type = ticket.getType() == null ? null : ticket.getType().toString();
        statement.setString(6, type);
        Timestamp birthday = ticket.getPerson().getBirthday() == null ? null : Timestamp.valueOf(ticket.getPerson().getBirthday());
        statement.setTimestamp(7, birthday);
        String eye = ticket.getPerson().getEyeColor() == null ? null : ticket.getPerson().getEyeColor().toString();
        statement.setString(8, eye);
        String hair = ticket.getPerson().getHairColor() == null ? null : ticket.getPerson().getHairColor().toString();
        statement.setString(9, hair);
        String country = ticket.getPerson().getNationality() == null ? null : ticket.getPerson().getNationality().toString();
        statement.setString(10, country);
        statement.setTimestamp(11, Timestamp.valueOf(ticket.getCreationDate()));
        statement.execute();
    }

    public void update(Long key, Ticket ticket) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE tickets " +
                        "SET name = ?, " +
                        "x = ?, " +
                        "y = ?, " +
                        "price = ?, " +
                        "type = ?, " +
                        "birthday = ?, " +
                        "eye = ?, " +
                        "hair = ?, " +
                        "country = ?, " +
                        "creation = ? " +
                            "WHERE key = ?;"
        );

        statement.setLong(11, key);
        statement.setString(1, ticket.getName());
        statement.setFloat(2, ticket.getCoordinates().getX());
        statement.setLong(3, ticket.getCoordinates().getY());
        statement.setFloat(4, ticket.getPrice());
        String type = ticket.getType() == null ? null : ticket.getType().toString();
        statement.setString(5, type);
        Timestamp birthday = ticket.getPerson().getBirthday() == null ? null : Timestamp.valueOf(ticket.getPerson().getBirthday());
        statement.setTimestamp(6, birthday);
        String eye = ticket.getPerson().getEyeColor() == null ? null : ticket.getPerson().getEyeColor().toString();
        statement.setString(7, eye);
        String hair = ticket.getPerson().getHairColor() == null ? null : ticket.getPerson().getHairColor().toString();
        statement.setString(8, hair);
        String country = ticket.getPerson().getNationality() == null ? null : ticket.getPerson().getNationality().toString();
        statement.setString(9, country);
        statement.setTimestamp(10, Timestamp.valueOf(ticket.getCreationDate()));
        statement.execute();
    }

    public void removeByKey(Long key) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM tickets *" +
                        "WHERE key = ?;"
        );

        statement.setLong(1, key);
        statement.execute();
    }

    public void removeByKeySet(Set<Long> removeSet) throws SQLException{
        for (Long key : removeSet) {
            removeByKey(key);
        }
    }
}
