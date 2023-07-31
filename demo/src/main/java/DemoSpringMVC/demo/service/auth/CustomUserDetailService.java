package DemoSpringMVC.demo.service.auth;

import DemoSpringMVC.demo.domain.customer.Customer;
import DemoSpringMVC.demo.entity.CustomerEntity;
import DemoSpringMVC.demo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Not Found !"));
        Customer customer = modelMapper.map(customerEntity, Customer.class);
        customer.setAuthorities(List.of(new SimpleGrantedAuthority(customerEntity.getRole())));
        return customer;
    }
}
