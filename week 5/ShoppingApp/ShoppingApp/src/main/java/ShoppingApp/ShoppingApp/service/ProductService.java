package ShoppingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        return null; 
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}