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

    @Override
    public String toString() {
        return "" + id + " " + name + " по цене " + price;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
