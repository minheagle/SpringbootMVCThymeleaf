package DemoSpringMVC.demo.controller;

import DemoSpringMVC.demo.domain.customer.CustomerLogin;
import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import DemoSpringMVC.demo.service.auth.IAuthService;
import DemoSpringMVC.demo.service.category.ICategoryService;
import DemoSpringMVC.demo.service.customer.ICustomerService;
import DemoSpringMVC.demo.service.product.IProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private UserDetailsService customerUserDetailsService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("products", productService.getAll());
        return "index";
    }

    @GetMapping("login")
    public String loginForm(Model model){
        model.addAttribute("customerLogin", new CustomerLogin());
        return "home/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("customerLogin")  CustomerLogin customer , BindingResult bindingResult, Model model, HttpSession session){
        return authService.login(customer, bindingResult, model, session);
    }

    @GetMapping("register")
    public String registerForm(Model model){
        model.addAttribute("newCustomer", new CustomerRegister());
        return "home/register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("newCustomer") @Valid CustomerRegister newCustomer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "home/register";
        }
        authService.register(newCustomer);
        return "redirect:/login";
    }

    @GetMapping("products/{slug}")
    public String productDetail(@PathVariable("slug") String slug,Model model){
        model.addAttribute("product", productService.getByProductSlug(slug));
        return "home/product/product-detail";
    }
}
