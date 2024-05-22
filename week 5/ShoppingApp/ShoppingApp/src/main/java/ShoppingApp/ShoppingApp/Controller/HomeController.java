package ShoppingApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";  
    }

    @PostMapping("/greet")
    public String greetUser(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "redirect:/greet";
    }

    @GetMapping("/greet")
    public String greet(Model model) {
        return "greet"; 
    }
}
