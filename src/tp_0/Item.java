package tp_0;

public class Item {
    private String name;
    private double price;
    private double taxRate;
    private double discount;

    public Item(String name, double price, double taxRate) {
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
    }

    public double getTotalPrice() {
        double totalPrice = this.price + this.price * this.taxRate;
        return totalPrice - totalPrice * this.discount / 100.0;
    }

    public void applyDiscount(double percentage) {
        this.discount = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
