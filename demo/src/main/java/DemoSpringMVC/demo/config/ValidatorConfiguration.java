package DemoSpringMVC.demo.config;

import DemoSpringMVC.demo.validator.customer.common.*;
import DemoSpringMVC.demo.validator.customer.create.CreateValidateCustomerPassword;
import DemoSpringMVC.demo.validator.customer.create.ICreateValidateCustomer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ValidatorConfiguration {
    @Bean
    public List<ICreateValidateCustomer> createValidateCustomers(){
        List<ICreateValidateCustomer> bean = new ArrayList<>();
        bean.add(new ValidateCustomerName());
        bean.add(new ValidateCustomerAge());
        bean.add(new ValidateCustomerAddress());
        bean.add(new ValidateCustomerPhone());
        bean.add(new ValidateCustomerEmail());
        bean.add(new CreateValidateCustomerPassword());

        return bean;
    }
}
