package ru.retail;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

public class Storage {

    private static Storage instance;
    //private String name;
    private HashSet<Product> setOfProducts;
    private static final String CATALOG_NAME = "file/products/";



    private Storage(){
        //this.name = "Основной склад";
        this.setOfProducts = new HashSet<Product>();
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
                    Product p = new Product(dis.readUTF(), dis.readUTF(), dis.readFloat());
                    setOfProducts.add(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HashSet<Product> getSetOfProducts() {
        return setOfProducts;
    }

}
