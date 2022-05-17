package ru.retail;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Storage {

    private static Storage instance;
    private final HashMap<String,Product> mapOfProducts;
    private static final String CATALOG_NAME = "file/products/";



    private Storage(){
        this.mapOfProducts = new HashMap<String,Product>();
        init();
    }

    public static Storage getInstance() {
        if (instance == null){
            instance = new Storage();
        }
        return instance;
    }

    private void init() {
        File catalog = new File(CATALOG_NAME);
        if (!catalog.exists()) {
            if (!catalog.mkdir()) {
                System.err.println("Ошибка создания каталога " + CATALOG_NAME);
            }
        }
        File[] files = catalog.listFiles();
        if (files != null) {
            for (File file : files) {
                try (DataInputStream dis = new DataInputStream(new FileInputStream(CATALOG_NAME + file.getName()))) {
                    String name = dis.readUTF();
                    String productId = dis.readUTF();
                    float price = dis.readFloat();
                    Product p = new Product(name, productId, price);
                    mapOfProducts.put(productId,p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HashMap<String,Product> getMapOfProducts() {
        return mapOfProducts;
    }

}
