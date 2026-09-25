public class Item {
    private String name;
    private double price;
    private double taxRate;

    public Item(String name, double price, double taxRate) {
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
    }

    public double getTotalPrice() {
        return price + price * taxRate;
    }

    public void applyDiscount(double percentage) {
        price =  price - price * percentage / 100;
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
