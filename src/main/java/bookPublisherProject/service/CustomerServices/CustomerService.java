package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.dto.CustomerDto;
import bookPublisherProject.data.entity.users.CustomerEntity;
import bookPublisherProject.data.request.customerRequests.RegisterCustomerRequest;
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
    public CustomerDto registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        return customerEntityService.create(registerCustomerRequest.convertToEntity()).convertToDto();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customerEntityList = customerEntityService.getAll();

        return customerEntityList.stream().map(CustomerEntity::convertToDto).toList();
    }

    @Override
    public CustomerDto getByEmailAdress(String emailAddress) {
        CustomerEntity customerEntity = customerEntityService.getByEmailAddress(emailAddress);

        return customerEntity.convertToDto();
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        CustomerEntity customerEntity = customerEntityService.getById(id);

        return customerEntity.convertToDto();
    }

}

