package ProcessEngine.DataCore;

import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn.*;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import javafx.concurrent.Task;
import network.Request;

import java.util.Arrays;
import java.util.Vector;

public class DataRun {

    protected NetworkManager networkManager;
    private Vector<String[]> collectionVectorData = new Vector<>();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

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

        for (String line : networkResponse.split("\n")) {
            vectorString.add(line.split(", "));
        }

        return vectorString; // [ [key, id, name, Coordinates.x, Coordinates.y, creationDate, price, type, Person.birthday, Person.eyeColor, Person.hairColor, Person.nationality] ]
    }

    public void asyncAutoUpdateCollectionData(String login, String password) {
        Task<Void> asyncUpdateCollectionDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                writeLock.lock();
                Vector<String[]> collection = new Vector<>();
                try {
                    if (DataSheet.keyFlagSort != null) {
                        if (DataSheet.keyFlagSort) {
                            collection.addAll(SortByKey.sortByKeyAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByKey.sortByKeyDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.idFlagSort != null) {
                        if (DataSheet.idFlagSort) {
                            collection.addAll(SortById.sortByIdAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortById.sortByIdDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.nameFlagSort != null) {
                        if (DataSheet.nameFlagSort) {
                            collection.addAll(SortByName.sortByNameAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByName.sortByNameDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.xFlagSort != null) {
                        if (DataSheet.xFlagSort) {
                            collection.addAll(SortByX.sortByXAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByX.sortByXDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.yFlagSort != null) {
                        if (DataSheet.yFlagSort) {
                            collection.addAll(SortByY.sortByYAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByY.sortByYDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.creationDateFlagSort != null) {
                        if (DataSheet.creationDateFlagSort) {
                            collection.addAll(SortByCreationDate.sortByCreationDateAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByCreationDate.sortByCreationDateDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.priceFlagSort != null) {
                        if (DataSheet.priceFlagSort) {
                            collection.addAll(SortByPrice.sortByPriceAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByPrice.sortByPriceDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.typeFlagSort != null) {
                        if (DataSheet.typeFlagSort) {
                            collection.addAll(SortByType.sortByTypeAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByType.sortByTypeDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.birthdayFlagSort != null) {
                        if (DataSheet.birthdayFlagSort) {
                            collection.addAll(SortByBirthday.sortByBirthdayAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByBirthday.sortByBirthdayDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.eyeFlagSort != null) {
                        if (DataSheet.eyeFlagSort) {
                            collection.addAll(SortByEye.sortByEyeAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByEye.sortByEyeDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.hairFlagSort != null) {
                        if (DataSheet.hairFlagSort) {
                            collection.addAll(SortByHair.sortByHairAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByHair.sortByHairDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.countryFlagSort != null) {
                        if (DataSheet.countryFlagSort) {
                            collection.addAll(SortByCountry.sortByCountryAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collection.addAll(SortByCountry.sortByCountryDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else {
                        collection.addAll(collectionDataRun(login, password));
                    }
                } finally {
                    collectionVectorData = collection;
                    writeLock.unlock();
                }

                Thread.sleep(500);
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

    public Vector<String[]> getCollectionData() {
        return collectionVectorData;
    }

}
