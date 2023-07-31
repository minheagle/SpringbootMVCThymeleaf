package DemoSpringMVC.demo.service.auth;

import DemoSpringMVC.demo.domain.customer.Customer;
import DemoSpringMVC.demo.domain.customer.CustomerLogin;
import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.filter.redirectFilter.HandleAuthenticationSuccess;
import DemoSpringMVC.demo.repository.CustomerRepository;
import DemoSpringMVC.demo.service.customer.ICustomerService;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HandleAuthenticationSuccess handleAuthenticationSuccess;
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private List<ICreateValidateCustomer> createValidateCustomers;

    @Override
    public void register(CustomerRegister customerRegister) {
        if(!customerRegister.getPassword().equals(customerRegister.getRePassword())){
            throw new RuntimeException("Nhập lại mật khẩu không khớp với mật khẩu !");
        }
        CustomerEntity customerEntityMapper = modelMapper.map(customerRegister, CustomerEntity.class);
        createValidateCustomer(customerEntityMapper);
        customerEntityMapper.setPassword(passwordEncoder.encode(customerEntityMapper.getPassword()));
        customerService.create(customerEntityMapper);
    }

    private void createValidateCustomer(CustomerEntity newCustomer){
        createValidateCustomers.forEach(validator -> validator.validate(newCustomer));
    }

    @Override
    public Customer login(CustomerLogin customerLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerLogin.getEmail(), customerLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomerEntity customer = customerRepository.findByEmail(customerLogin.getEmail()).get();
        return modelMapper.map(customer, Customer.class);
    }

    @Override
    public String login(@Valid CustomerLogin customerLogin, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()){
            return "home/login";
        }
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customerLogin.getEmail(), customerLogin.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomerEntity customer = customerRepository.findByEmail(customerLogin.getEmail()).get();
            session.setAttribute("customer", modelMapper.map(customer, Customer.class));
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                return "redirect:/admin";
            }
            return "redirect:/";
        }catch (Exception e){
            model.addAttribute("error", "Đăng nhập thất bại !");
            return "home/login";
        }
    }
}
