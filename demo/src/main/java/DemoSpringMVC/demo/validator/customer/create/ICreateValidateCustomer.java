package DemoSpringMVC.demo.validator.customer.create;

import DemoSpringMVC.demo.entity.CustomerEntity;

public interface ICreateValidateCustomer {
    void validate(CustomerEntity customer);
}
