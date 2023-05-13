package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerEntityService customerEntityService;


//    @Override
//    public UserDto login(LoginRequest loginRequest) {
//        return convertToUserDto(adminEntityService.login());
//    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerEntityService.getAll();
    }

    @Override
    public UserDto getByEmailAdress(String emailAddress) {
        return convertToUserDto(customerEntityService.getByEmailAddress(emailAddress));
    }

    public UserDto convertToUserDto(Customer customer){
        return customer.convertToUserDto();
    }
}

