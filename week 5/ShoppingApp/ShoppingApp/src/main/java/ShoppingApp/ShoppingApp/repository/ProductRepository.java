package ShoppingApp.ShoppingApp.repository;

import ShoppingApp.ShoppingApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

