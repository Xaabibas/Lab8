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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataRun {

    protected NetworkManager networkManager;
    private final Vector<String[]> collectionVectorData = new Vector<>();

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

        for (String tickets : networkResponse.split("\n")) {
            vectorString.add(tickets.split(", "));
        }

        return vectorString; // [ [key, id, name, Coordinates.x, Coordinates.y, creationDate, price, type, Person.birthday, Person.eyeColor, Person.hairColor, Person.nationality] ]
    }

    public void asyncAutoUpdateCollectionData(String login, String password) {
        Task<Void> asyncUpdateCollectionDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                writeLock.lock();
                collectionVectorData.clear();
                try {
                    if (DataSheet.keyFlagSort != null) {
                        if (DataSheet.keyFlagSort) {
                            collectionVectorData.addAll(SortByKey.sortByKeyAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByKey.sortByKeyDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.idFlagSort != null) {
                        if (DataSheet.idFlagSort) {
                            collectionVectorData.addAll(SortById.sortByIdAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortById.sortByIdDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.nameFlagSort != null) {
                        if (DataSheet.nameFlagSort) {
                            collectionVectorData.addAll(SortByName.sortByNameAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByName.sortByNameDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.xFlagSort != null) {
                        if (DataSheet.xFlagSort) {
                            collectionVectorData.addAll(SortByX.sortByXAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByX.sortByXDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.yFlagSort != null) {
                        if (DataSheet.yFlagSort) {
                            collectionVectorData.addAll(SortByY.sortByYAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByY.sortByYDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.creationDateFlagSort != null) {
                        if (DataSheet.creationDateFlagSort) {
                            collectionVectorData.addAll(SortByCreationDate.sortByCreationDateAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByCreationDate.sortByCreationDateDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.priceFlagSort != null) {
                        if (DataSheet.priceFlagSort) {
                            collectionVectorData.addAll(SortByPrice.sortByPriceAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByPrice.sortByPriceDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.typeFlagSort != null) {
                        if (DataSheet.typeFlagSort) {
                            collectionVectorData.addAll(SortByType.sortByTypeAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByType.sortByTypeDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.birthdayFlagSort != null) {
                        if (DataSheet.birthdayFlagSort) {
                            collectionVectorData.addAll(SortByBirthday.sortByBirthdayAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByBirthday.sortByBirthdayDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.eyeFlagSort != null) {
                        if (DataSheet.eyeFlagSort) {
                            collectionVectorData.addAll(SortByEye.sortByEyeAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByEye.sortByEyeDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.hairFlagSort != null) {
                        if (DataSheet.hairFlagSort) {
                            collectionVectorData.addAll(SortByHair.sortByHairAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByHair.sortByHairDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else if (DataSheet.countryFlagSort != null) {
                        if (DataSheet.countryFlagSort) {
                            collectionVectorData.addAll(SortByCountry.sortByCountryAscendingOrder(collectionDataRun(login, password)));
                        } else {
                            collectionVectorData.addAll(SortByCountry.sortByCountryDescendingOrder(collectionDataRun(login, password)));
                        }
                    } else {
                        collectionVectorData.addAll(collectionDataRun(login, password));
                    }
                } finally {
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
        readLock.lock();
        try {
            return collectionVectorData;
        } finally {
            readLock.unlock();
        }
    }

}
