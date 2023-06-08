package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.dto.CustomerDto;
import bookPublisherProject.data.request.customerRequests.RegisterCustomerRequest;

import java.util.List;

public interface ICustomerService {

//UserDto login(LoginRequest loginRequest);

    CustomerDto registerCustomer(RegisterCustomerRequest registerCustomerRequest);

    List<CustomerDto> getAllCustomers();

    CustomerDto getByEmailAdress(String emailAddress);

    CustomerDto getCustomerById(String id);
}
