package DemoSpringMVC.demo.validator.customer.common;

import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import DemoSpringMVC.demo.validator.customer.update.IUpdateValidateCustomer;
import org.springframework.stereotype.Component;

@Component
public class ValidateCustomerName implements ICreateValidateCustomer, IUpdateValidateCustomer {
    @Override
    public void validate(CustomerEntity customer) {
        if (customer.getName().isEmpty()){
            throw new RuntimeException("Họ và tên không được để trống !");
        }
        if(customer.getName().length() > 255){
            throw new RuntimeException("Họ và tên không được quá 255 kí tự !");
        }
    }
}
