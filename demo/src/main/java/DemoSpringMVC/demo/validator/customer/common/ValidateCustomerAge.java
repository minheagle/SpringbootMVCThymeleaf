package DemoSpringMVC.demo.validator.customer.common;

import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import DemoSpringMVC.demo.validator.customer.update.IUpdateValidateCustomer;
import org.springframework.stereotype.Component;

@Component
public class ValidateCustomerAge implements ICreateValidateCustomer, IUpdateValidateCustomer {
    @Override
    public void validate(CustomerEntity customer) {
        if (customer.getAge() == 0){
            throw new RuntimeException("Tuổi không được bỏ trống !");
        }
        if (customer.getAge() < 18){
            throw new RuntimeException("Bạn chưa đủ tuổi !");
        }
    }
}
