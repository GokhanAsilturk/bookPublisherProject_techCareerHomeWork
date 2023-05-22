package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.users.CustomerEntity;
import bookPublisherProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerEntityService implements ICustomerEntityService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity getByEmailAddress(String emailAddress) {
        return customerRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public CustomerEntity getById(String id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
