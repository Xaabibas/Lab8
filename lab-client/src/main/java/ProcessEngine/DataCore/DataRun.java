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
import java.util.TreeSet;

public class DataRun {

    protected NetworkManager networkManager;
    private final Vector<String[]> collectionVectorData = new Vector<>();

    public static final TreeSet<String> uniqueKeyElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueIdElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueNameElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueXElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueYElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueCreationDateElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniquePriceElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueTypeElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueBirthdayElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueEyeElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueHairElementsSet = new TreeSet<String>();
    public static final TreeSet<String> uniqueCountryElementsSet = new TreeSet<String>();

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
                    updateUniqueElementsSet();
                    writeLock.unlock();
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

    public Vector<String[]> getCollectionData() {
        readLock.lock();
        try {
            return collectionVectorData;
        } finally {
            readLock.unlock();
        }
    }

    public void updateUniqueElementsSet() {
        uniqueKeyElementsSet.clear();
        uniqueIdElementsSet.clear();
        uniqueNameElementsSet.clear();
        uniqueXElementsSet.clear();
        uniqueYElementsSet.clear();
        uniqueCreationDateElementsSet.clear();
        uniquePriceElementsSet.clear();
        uniqueTypeElementsSet.clear();
        uniqueBirthdayElementsSet.clear();
        uniqueEyeElementsSet.clear();
        uniqueHairElementsSet.clear();
        uniqueCountryElementsSet.clear();

        for (String[] element : collectionVectorData) {
            uniqueKeyElementsSet.add(element[0]);
            uniqueIdElementsSet.add(element[1]);
            uniqueNameElementsSet.add(element[2]);
            uniqueXElementsSet.add(element[3]);
            uniqueYElementsSet.add(element[4]);
            uniqueCreationDateElementsSet.add(element[5]);
            uniquePriceElementsSet.add(element[6]);
            uniqueTypeElementsSet.add(element[7]);
            uniqueBirthdayElementsSet.add(element[8]);
            uniqueEyeElementsSet.add(element[9]);
            uniqueHairElementsSet.add(element[10]);
            uniqueCountryElementsSet.add(element[11]);
        }
    }

}
