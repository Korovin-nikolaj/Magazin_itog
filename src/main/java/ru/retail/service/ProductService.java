package ru.retail.service;

import ru.retail.Storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class ProductService {
    public static LinkedHashMap<Integer, String> getAllProducts (){
        Connection conn = Storage.getInstance().getConn();
        LinkedHashMap<Integer, String> allProducts = new LinkedHashMap<>();
        try(Statement statement = conn.createStatement()) {
            String sqlCommand = "select id, productName, price from products;";
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                float price = resultSet.getFloat("price");
                String productView = productName + " по цене " + price;
                allProducts.put(id, productView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public static LinkedHashMap<Integer, String> findProducts (String conditionText){
        Connection conn = Storage.getInstance().getConn();
        LinkedHashMap<Integer, String> foundProducts = new LinkedHashMap<>();
        try(Statement statement = conn.createStatement()) {
            String sqlCommand = "select id, productName, price from products;";
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                float price = resultSet.getFloat("price");
                String productView = productName + " по цене " + price;
                foundProducts.put(id, productView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProducts;
    }
}
