package DemoSpringMVC.demo.validator.customer.common;

import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.repository.CustomerRepository;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import DemoSpringMVC.demo.validator.customer.update.IUpdateValidateCustomer;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateCustomerPhone implements ICreateValidateCustomer, IUpdateValidateCustomer {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void validate(CustomerEntity customer) {
        if (customer.getPhone().isEmpty()){
            throw new RuntimeException("Số điện thoại không được để trống !");
        }
        if(!customer.getPhone().startsWith("0")){
            throw new RuntimeException("Số điện thoại không đúng định dạng !");
        }
        if(customerRepository.existsByPhone(customer.getPhone())){
            throw new RuntimeException("Số điện thoại đã đăng kí !");
        }
    }
}
