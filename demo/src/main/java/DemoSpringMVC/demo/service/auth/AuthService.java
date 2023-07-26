package DemoSpringMVC.demo.service.auth;

import DemoSpringMVC.demo.domain.customer.Customer;
import DemoSpringMVC.demo.domain.customer.CustomerLogin;
import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.repository.CustomerRepository;
import DemoSpringMVC.demo.service.customer.ICustomerService;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements IAuthService{
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
        if (!customerRepository.existsByEmail(customerLogin.getEmail())){
            throw new RuntimeException("Đăng nhập không thành công !");
        }
        CustomerEntity customer = customerRepository.findByEmail(customerLogin.getEmail());
        if(!passwordEncoder.matches(customerLogin.getPassword(), customer.getPassword())){
            throw new RuntimeException("Đăng nhập không thành công !");
        }
        Customer getCustomer = modelMapper.map(customer, Customer.class);
        return getCustomer;
    }
}
