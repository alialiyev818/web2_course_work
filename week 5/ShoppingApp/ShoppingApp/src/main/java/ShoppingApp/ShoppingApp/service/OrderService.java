package ShoppingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            return order.get();
        }
        return null; 
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
