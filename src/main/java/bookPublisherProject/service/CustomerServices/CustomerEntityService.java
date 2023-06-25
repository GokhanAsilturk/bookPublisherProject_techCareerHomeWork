package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.users.CustomerEntity;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
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
        return customerRepository.findAllByIsDeletedFalse().orElseThrow(()->
                new DataNotFoundException(ExceptionType.USER_LIST_NOT_FOUND,"User List is Empty! :)"));
    }

    @Override
    public CustomerEntity getByEmailAddress(String emailAddress) {
        return customerRepository.findByEmailAddress(emailAddress).orElseThrow(()->
                new DataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND,"User Not Found! :)"));
    }

    @Override
    public CustomerEntity getById(String id) {
        return customerRepository.findById(id).orElseThrow(()->
                new DataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND));
    }
}
