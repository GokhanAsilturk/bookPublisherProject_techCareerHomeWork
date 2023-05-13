package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Customer;
import bookPublisherProject.repository.AdminRepository;
import bookPublisherProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerEntityService implements ICustomerEntityService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public User login(Customer customer) {
        return null;
    }

    @Override
    public Customer getByEmailAddress(String emailAddress) {
        return customerRepository.findByEmailAddress(emailAddress);
    }
}
