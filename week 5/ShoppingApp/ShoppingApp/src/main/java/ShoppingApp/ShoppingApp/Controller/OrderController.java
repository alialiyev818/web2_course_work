package ShoppingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.listAllOrders());
        return "orders";
    }

    @GetMapping("/add")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "addOrder";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
        orderService.saveOrder(order);
        redirectAttributes.addFlashAttribute("message", "Order saved successfully!");
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        orderService.deleteOrder(id);
        redirectAttributes.addFlashAttribute("message", "Order deleted successfully!");
        return "redirect:/orders";
    }

    @GetMapping("/view/{id}")
    public String viewOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrder(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "viewOrder";
        }
        return "redirect:/orders";
    }
}
