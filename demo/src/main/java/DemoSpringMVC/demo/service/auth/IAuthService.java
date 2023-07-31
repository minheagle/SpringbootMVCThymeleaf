package DemoSpringMVC.demo.service.auth;

import DemoSpringMVC.demo.domain.customer.Customer;
import DemoSpringMVC.demo.domain.customer.CustomerLogin;
import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IAuthService {
    void register(CustomerRegister customerRegister);
    Customer login(CustomerLogin customerLogin);
    String login(CustomerLogin customerLogin, BindingResult bindingResult, Model model, HttpSession session);
}
