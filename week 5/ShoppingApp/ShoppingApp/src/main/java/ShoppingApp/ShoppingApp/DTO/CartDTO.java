package ShoppingApp;

import java.util.List;

public class CartDTO {
    private List<Product> products;
    private double totalWeight;
    private double totalPrice;

    public CartDTO() {}

    public CartDTO(List<Product> products, double totalWeight, double totalPrice) {
        this.products = products;
        this.totalWeight = totalWeight;
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
