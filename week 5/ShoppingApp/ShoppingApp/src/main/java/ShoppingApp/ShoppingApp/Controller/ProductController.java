package ShoppingApp.ShoppingApp.controller;

import ShoppingApp.ShoppingApp.model.Product;
import ShoppingApp.ShoppingApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "products"; // View for listing products
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct"; // View for adding a new product
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Product added successfully!");
        return "redirect:/products"; // Redirect to list of products
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.get(id));
        return "editProduct"; // View for editing a product
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        Product existingProduct = productService.get(id);
        existingProduct.setName(product.getName());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setPrice(product.getPrice());
        productService.save(existingProduct);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/products"; // Redirect to list of products
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        return "redirect:/products"; // Redirect to list of products
    }
}
