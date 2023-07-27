package DemoSpringMVC.demo.controller;

import DemoSpringMVC.demo.domain.cart.Checkout;
import DemoSpringMVC.demo.service.cart.ICartService;
import DemoSpringMVC.demo.service.order.IOrderService;
import DemoSpringMVC.demo.service.product.IProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String cartPage(Model model, HttpSession session){
        model.addAttribute("carts", cartService.getAll());
        model.addAttribute("totalCost", cartService.getTotalCost());
        session.setAttribute("countCart", cartService.count());
        return "home/cart/cart";
    }

    @GetMapping("/add/{productId}")
    public String addItem(@PathVariable("productId") Long productId){
        cartService.addItem(productService.getById(productId));
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable("productId") long productId ,@RequestParam("quantity") Integer quantity){
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{cartId}")
    public String removeItem(@PathVariable("cartId") Long cartId){
        System.out.println(cartId);
        cartService.removeItem(cartId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model){
        model.addAttribute("checkoutForm", new Checkout());
        return "home/cart/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute @Valid Checkout checkout, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "home/cart/checkout";
        }
        orderService.create(checkout, cartService.getAll());
        cartService.emptyCart();
        return "redirect:/cart";
    }
}
