package DemoSpringMVC.demo.validator.customer.create;

import DemoSpringMVC.demo.entity.CustomerEntity;

public class CreateValidateCustomerPassword implements ICreateValidateCustomer{
    @Override
    public void validate(CustomerEntity customer) {
        if (customer.getPassword().isEmpty()){
            throw new RuntimeException("Mật khẩu không được để trống !");
        }
        if(customer.getPassword().trim().length() < 8 || customer.getPassword().trim().length() > 25){
            throw new RuntimeException("Mật khẩu chỉ từ 8 -> 25 ki tự !");
        }
    }
}
