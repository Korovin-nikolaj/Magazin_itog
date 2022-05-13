package ru.retail;

public class Product {

    private String name;
    private String id;
    private float price;

    public Product(String name, String id, float price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
}
