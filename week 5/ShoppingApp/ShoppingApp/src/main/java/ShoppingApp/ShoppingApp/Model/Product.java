package ShoppingApp.ShoppingApp.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double weight;
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}