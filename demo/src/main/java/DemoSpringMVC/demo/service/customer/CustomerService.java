package DemoSpringMVC.demo.service.customer;

import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.repository.CustomerRepository;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private List<ICreateValidateCustomer> createValidateCustomers;

    @Override
    public List<CustomerEntity> getAll() {
        return null;
    }

    @Override
    public CustomerEntity getById(long id) {
        return null;
    }

    @Override
    @Transactional
    public CustomerEntity create(CustomerEntity newCustomer) {
        return customerRepository.save(newCustomer);
    }

    private void createValidateCustomer(CustomerEntity newCustomer){
        createValidateCustomers.forEach(validator -> validator.validate(newCustomer));
    }

    @Override
    public CustomerEntity update(long id, CustomerEntity updateCustomer) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
