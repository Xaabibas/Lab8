package moduls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Comparable<Ticket>, Serializable {
    final private static long serialVersionUID = 15L;
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private float price;
    private TicketType type;
    private Person person;
    private final String creator;
    
    public Ticket(String creator) {
        this.creationDate = LocalDateTime.now();
        this.creator = creator;
    }

    
    public TicketType getType() {
        return type;
    }

    
    public void setType(TicketType type) {
        this.type = type;
    }

    
    public float getPrice() {
        return price;
    }

    public String getCreator() {
        return creator;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    
    public Coordinates getCoordinates() {
        return coordinates;
    }

    
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    
    public Person getPerson() {
        return person;
    }

    
    public void setPerson(Person person) {
        this.person = person;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if (o==null || getClass()!=o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Float.compare(price, ticket.price)==0 && name.equals(ticket.name) && coordinates.equals(ticket.coordinates) && type==ticket.type && person.equals(ticket.person);
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, type, person);
    }

    
    @Override
    public String toString() {
        return id + ", " + name + ", " + coordinates.toString() + ", " + creationDate.toString() + ", " + price + ", " + type + ", " + person.toString() + ", " + creator;
    }

    
    @Override
    public int compareTo(Ticket o) {
        return (int) (this.price - o.price);
    }
}