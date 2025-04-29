package managers;

import moduls.Ticket;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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
