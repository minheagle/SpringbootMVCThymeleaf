package DemoSpringMVC.demo.service.auth;

import DemoSpringMVC.demo.domain.customer.Customer;
import DemoSpringMVC.demo.domain.customer.CustomerLogin;
import DemoSpringMVC.demo.domain.customer.CustomerRegister;

public interface IAuthService {
    void register(CustomerRegister customerRegister);
    Customer login(CustomerLogin customerLogin);
}
