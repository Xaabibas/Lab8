package ProcessEngine.DataCore;

import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn.*;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.concurrent.Task;
import network.Request;

import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (DataSheet.keyFlagSort != null) {
                    if (DataSheet.keyFlagSort) {
                        collectionVectorData = SortByKey.sortByKeyAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.keyFlagSort) {
                        collectionVectorData = SortByKey.sortByKeyDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.idFlagSort != null) {
                    if (DataSheet.idFlagSort) {
                        collectionVectorData = SortById.sortByIdAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.idFlagSort) {
                        collectionVectorData = SortById.sortByIdDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.nameFlagSort != null) {
                    if (DataSheet.nameFlagSort) {
                        collectionVectorData = SortByName.sortByNameAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.nameFlagSort) {
                        collectionVectorData = SortByName.sortByNameDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.xFlagSort != null) {
                    if (DataSheet.xFlagSort) {
                        collectionVectorData = SortByX.sortByXAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.xFlagSort) {
                        collectionVectorData = SortByX.sortByXDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.yFlagSort != null) {
                    if (DataSheet.yFlagSort) {
                        collectionVectorData = SortByY.sortByYAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.yFlagSort) {
                        collectionVectorData = SortByY.sortByYDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.creationDateFlagSort != null) {
                    if (DataSheet.creationDateFlagSort) {
                        collectionVectorData = SortByCreationDate.sortByCreationDateAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.creationDateFlagSort) {
                        collectionVectorData = SortByCreationDate.sortByCreationDateDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.priceFlagSort != null) {
                    if (DataSheet.priceFlagSort) {
                        collectionVectorData = SortByPrice.sortByPriceAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.priceFlagSort) {
                        collectionVectorData = SortByPrice.sortByPriceDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.typeFlagSort != null) {
                    if (DataSheet.typeFlagSort) {
                        collectionVectorData = SortByType.sortByTypeAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.typeFlagSort) {
                        collectionVectorData = SortByType.sortByTypeDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.birthdayFlagSort != null) {
                    if (DataSheet.birthdayFlagSort) {
                        collectionVectorData = SortByBirthday.sortByBirthdayAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.birthdayFlagSort) {
                        collectionVectorData = SortByBirthday.sortByBirthdayDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.eyeFlagSort != null) {
                    if (DataSheet.eyeFlagSort) {
                        collectionVectorData = SortByEye.sortByEyeAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.eyeFlagSort) {
                        collectionVectorData = SortByEye.sortByEyeDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.hairFlagSort != null) {
                    if (DataSheet.hairFlagSort) {
                        collectionVectorData = SortByHair.sortByHairAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.hairFlagSort) {
                        collectionVectorData = SortByHair.sortByHairDescendingOrder(collectionDataRun(login, password));
                    }
                } else if (DataSheet.countryFlagSort != null) {
                    if (DataSheet.countryFlagSort) {
                        collectionVectorData = SortByCountry.sortByCountryAscendingOrder(collectionDataRun(login, password));
                    } else if (!DataSheet.countryFlagSort) {
                        collectionVectorData = SortByCountry.sortByCountryDescendingOrder(collectionDataRun(login, password));
                    }
                } else {
                    collectionVectorData = collectionDataRun(login, password);
                }

                Thread.sleep(100);
                return null;
            }
        };

        asyncUpdateCollectionDataTask.setOnSucceeded(event -> {
            // System.out.println("# AsyncCollectionUpdate завершился успешно");
            asyncAutoUpdateCollectionData(login, password);
        });

        asyncUpdateCollectionDataTask.setOnFailed(event -> {
            Throwable ex = asyncUpdateCollectionDataTask.getException();
            ex.printStackTrace();
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
