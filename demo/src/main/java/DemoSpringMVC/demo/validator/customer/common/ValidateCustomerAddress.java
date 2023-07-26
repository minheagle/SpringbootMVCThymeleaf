package DemoSpringMVC.demo.validator.customer.common;

import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import DemoSpringMVC.demo.validator.customer.update.IUpdateValidateCustomer;
import org.springframework.stereotype.Component;

@Component
public class ValidateCustomerAddress implements ICreateValidateCustomer, IUpdateValidateCustomer {
    @Override
    public void validate(CustomerEntity customer) {
        if (customer.getAddress().isEmpty()){
            throw new RuntimeException("Địa chỉ không được để trống !");
        }
        if(customer.getAddress().length() > 255){
            throw new RuntimeException("Địa chỉ không được quá 255 ki tự !");
        }
    }
}
