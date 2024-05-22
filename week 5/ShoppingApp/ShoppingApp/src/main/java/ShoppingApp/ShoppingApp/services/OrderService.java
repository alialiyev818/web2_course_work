package ShoppingApp.ShoppingApp.service;

import ShoppingApp.ShoppingApp.model.Order;
import ShoppingApp.ShoppingApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    // Add methods for save, get, and delete similar to ProductService
}
