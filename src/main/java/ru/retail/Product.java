package ru.retail;

public class Product {

    private String name;
    private int id;
    private float price;
    private String productCategory;
    private String productCountry;
    private boolean discounted;


    public Product(String name, int id, float price, String productCategory, String productCountry, boolean discounted) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.productCategory = productCategory;
        this.productCountry = productCountry;
        this.discounted = discounted;
    }

    @Override
    public String toString() {
        return "" + id + " " + name + " по цене " + price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
