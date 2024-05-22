package ShoppingApp.ShoppingApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    @OneToMany
    private List<Product> products;
    private double totalWeight;
    private double totalPrice;

    // Constructors, getters, and setters
    public Order() {}

    public Order(String customerName, List<Product> products, double totalWeight, double totalPrice) {
        this.customerName = customerName;
        this.products = products;
        this.totalWeight = totalWeight;
        this.totalPrice = totalPrice;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
    public double getTotalWeight() { return totalWeight; }
    public void setTotalWeight(double totalWeight) { this.totalWeight = totalWeight; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
