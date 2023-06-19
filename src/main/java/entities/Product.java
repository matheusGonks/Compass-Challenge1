package entities;

public class Product{

    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    };

    public int getId(){ return this.id; };
    public String getName(){
        return  this.name;
    };

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() {
        String correctedPrice = String.format("%.2f", this.price);
        return String.format("%-5s %-20s %-8s %-10s\n", this.id, this.name, correctedPrice, this.quantity);
    }
}