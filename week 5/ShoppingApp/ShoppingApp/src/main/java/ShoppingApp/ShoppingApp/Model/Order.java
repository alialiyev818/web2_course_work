package ShoppingApp.ShoppingApp.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerName;
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    private double totalWeight;
    private double totalPrice;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Getters and Setters
}
