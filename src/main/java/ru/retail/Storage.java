package ru.retail;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Storage {

    private static Storage instance;
    private final HashMap<Integer,Product> mapOfProducts;
    private Connection conn;
    //private static final String CATALOG_NAME = "file/products/";


    private Storage(){
        this.mapOfProducts = new HashMap<Integer, Product>();
        this.conn = null;
        init();
    }

    public static Storage getInstance() {
        if (instance == null){
            instance = new Storage();
        }
        return instance;
    }

    private void init() {
//        File catalog = new File(CATALOG_NAME);
//        if (!catalog.exists()) {
//            if (!catalog.mkdir()) {
//                System.err.println("Ошибка создания каталога " + CATALOG_NAME);
//            }
//        }
//        File[] files = catalog.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                try (DataInputStream dis = new DataInputStream(new FileInputStream(CATALOG_NAME + file.getName()))) {
//                    String name = dis.readUTF();
//                    int productId = dis.readUTF();
//                    float price = dis.readFloat();
//                    Product p = new Product(name, productId, price);
//                    mapOfProducts.put(productId,p);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection successful!");
            String url = "jdbc:mysql://localhost/magazin";
            String username = "magazin_robot";
            String password = "magazin_password";
            try{
                conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                String sqlCommand = "select id, productName, price, productCategory, productCountry, discounted from products";
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String productName = resultSet.getString("productName");
                    float price = resultSet.getFloat("price");
                    String productCategory = resultSet.getString("productCategory");
                    String productCountry = resultSet.getString("productCountry");
                    boolean discounted = resultSet.getBoolean("discounted");
                    Product p = new Product(productName, id, price, productCategory, productCountry, discounted);
                    mapOfProducts.put(id, p);
                }
            }
            catch (Exception exc) {

            }
        }
        catch (Exception exc) {
            System.out.println("Connection failed...");
            System.out.println(exc);
        }
    }

    public HashMap<Integer,Product> getMapOfProducts() {
        return mapOfProducts;
    }

    public Connection getConn() {
        return conn;
    }
}
