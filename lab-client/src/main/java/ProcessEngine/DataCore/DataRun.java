package ProcessEngine.DataCore;

import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import network.Request;

import java.util.Vector;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.concurrent.Task;

public class DataRun {

    protected NetworkManager networkManager;
    protected Vector<String[]> collectionVectorData = new Vector<>();
 
    public DataRun(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }
    
    public Vector<String[]> collectionDataRun(String login, String password) {
        Vector<String[]> vectorString = new Vector<>();

        Request collectionRequest = new Request();
        collectionRequest.setUser(login);
        collectionRequest.setPassword(Arrays.toString(password
            .chars()
            .mapToObj(c -> String.valueOf((char) c))
            .toArray(String[]::new))
        );
        collectionRequest.setCommandName("show");
        collectionRequest.setTokens("show");

        String networkResponse = networkManager.sendAndReceive(collectionRequest);

        Pattern wordPattern = Pattern.compile("(\\d+\\s*-\\s*Ticket\\{([^\\}]*\\}[^\\}]*\\}))");
        Matcher wordMatcher = wordPattern.matcher(networkResponse);

        while (wordMatcher.find()) {
            String word = wordMatcher.group(1);
            vectorString.add(replacedFunc(word));
        }
        
        return vectorString; // [ [key, id, name, Coordinates.x, Coordinates.y, creationDate, price, type, Person.birthday, Person.eyeColor, Person.hairColor, Person.nationality] ]
    }

    protected String[] replacedFunc(String word) {
        return word
            .substring(0, word.length() - 1)
            .replace("id=", "")
            .replace("name=", "")
            .replace("coordinates=", "")
            .replace("creationDate=", "")
            .replace("price=", "")
            .replace("type=", "")
            .replace("person=", "")
            .replace("eyeColor=", "")
            .replace("hairColor=", "")
            .replace("nationality=", "")
            .replace("Coordinates", "")
            .replace("birthday=", "")
            .replace("x=", "")
            .replace("y=", "")
            .replace("Person", "")
            .replace("Person", "")
            .replace("'", "")
            .replace("{", "")
            .replace("}", "")
            .replace(" -", ",")
            .replace("Ticket", "")
            .split(", ");
    }

    public synchronized void asyncAutoUpdateCollectionData(String login, String password) {
        Task<Void> asyncUpdateCollectionDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                collectionVectorData = collectionDataRun(login, password);
                Thread.sleep(200);
                return null;
            }
        };

        asyncUpdateCollectionDataTask.setOnSucceeded(event -> {
            // System.out.println("# AsyncCollectionUpdate завершился успешно");
            asyncAutoUpdateCollectionData(login, password);
        });

        asyncUpdateCollectionDataTask.setOnFailed(event -> {
            System.out.println("# AsyncCollectionUpdate завершился с ошибкой. Повторный запуск");
            asyncAutoUpdateCollectionData(login, password);
        });

        Thread newThread = new Thread(asyncUpdateCollectionDataTask);
        newThread.setDaemon(true);
        newThread.start();
    }

    public synchronized Vector<String[]> getCollectionData() {
        return collectionVectorData;
    }

}
