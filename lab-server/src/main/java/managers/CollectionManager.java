package managers;

import moduls.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectionManager {
    public static final Logger logger = Logger.getLogger("CollectionLogger");
    private LinkedHashMap<Long, Ticket> collection;
    private final LocalDateTime time;
    private final FileManager fm;
    private final DataBaseManager dbManager;

    public CollectionManager(DataBaseManager dbManager) {
        this.fm = new FileManager();
        this.time = LocalDateTime.now();
        this.dbManager = dbManager;
    }

    public LinkedHashMap<Long, Ticket> getCollection() {
        return collection;
    }

    public void loadCollection() throws SQLException{
        collection = dbManager.readCollection();
    }

    public void setCollection(LinkedHashMap<Long, Ticket> collection) {
        this.collection = collection;
    }

    public void setCollectionFromFile() {
        logger.info("Attempt to load a collection from a file");
        this.collection = fm.readCollection();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void sortByPrice() {
        this.collection = collection.entrySet().stream().sorted(Map.Entry.comparingByValue(Ticket::compareTo))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void sortByName() {
        this.collection = collection.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Ticket::getName)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public FileManager getFm() {
        return fm;
    }

    public boolean clear() {
        if (dbManager.clear()) {
            collection.clear();
            return true;
        }
        return false;
    }

    public void insert(Long key, Ticket ticket) throws SQLException{
        dbManager.insert(key, ticket);
        collection.put(key, ticket);
    }

    public void update(Long key, Ticket ticket) throws SQLException {
        dbManager.update(key, ticket);
        collection.replace(key, ticket);
    }

    public void removeByKey(Long key) throws SQLException{
        dbManager.removeByKey(key);
        collection.remove(key);
    }

    public void removeByKeySet(Set<Long> removeSet) throws SQLException {
        dbManager.removeByKeySet(removeSet);
        for (Long key : removeSet) {
            collection.remove(key);
        }
    }
}
