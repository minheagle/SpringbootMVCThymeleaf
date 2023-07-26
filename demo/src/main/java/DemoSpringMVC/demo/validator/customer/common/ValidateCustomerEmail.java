package DemoSpringMVC.demo.validator.customer.common;

import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.repository.CustomerRepository;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import DemoSpringMVC.demo.validator.customer.update.IUpdateValidateCustomer;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateCustomerEmail implements ICreateValidateCustomer, IUpdateValidateCustomer {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void validate(CustomerEntity customer) {
        if(customer.getEmail().isEmpty()){
            throw new RuntimeException("Email không được để trống !");
        }
        if(!customer.getEmail().endsWith("@gmail.com") && !customer.getEmail().endsWith("@outlook.com")){
            throw new RuntimeException("Dạng email không được hỗ trợ !");
        }
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email đã được đăng ký !");
        }
    }
}
