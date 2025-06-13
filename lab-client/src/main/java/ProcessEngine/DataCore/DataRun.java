package ProcessEngine.DataCore;

import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import network.Request;

import java.util.Vector;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataRun {

    protected NetworkManager networkManager;

    public DataRun(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }
    
    public Vector<String[]> collectionDataRun(String login, String password) {
        Vector<String[]> vectorString = new Vector<String[]>();

        Request collectionRequest = new Request();
        collectionRequest.setUser(login);
        collectionRequest.setPassword(Arrays.toString(password
            .chars()
            .mapToObj(c -> String.valueOf((char) c))
            .toArray(String[]::new))
        );
        collectionRequest.setCommandName("show");
        collectionRequest.setTokens("show");

        String networkresponse = networkManager.sendAndReceive(collectionRequest);
        System.out.println(networkresponse);
        Pattern wordPattern = Pattern.compile("(\\d+\\s*-\\s*Ticket\\{([^\\}]*\\}[^\\}]*\\}))");
        Matcher wordMatcher = wordPattern.matcher(networkresponse);

        while (wordMatcher.find()) {
            String word = wordMatcher.group(1);
            vectorString.add(replacedFunc(word));
        }
        
        return vectorString;
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

}
