package ShoppingApp.ShoppingApp.repository;

import ShoppingApp.ShoppingApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

