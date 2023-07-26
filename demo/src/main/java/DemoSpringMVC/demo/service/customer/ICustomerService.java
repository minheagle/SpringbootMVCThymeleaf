package DemoSpringMVC.demo.service.customer;

import DemoSpringMVC.demo.domain.customer.CustomerRegister;
import DemoSpringMVC.demo.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> getAll();
    CustomerEntity getById(long id);
    CustomerEntity create(CustomerEntity newCustomer);
    CustomerEntity update(long id, CustomerEntity updateCustomer);
    void delete(long id);
}
