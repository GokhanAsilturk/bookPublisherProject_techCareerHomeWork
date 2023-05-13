package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Customer;

import java.util.List;

public interface ICustomerService {

//UserDto login(LoginRequest loginRequest);

    List<Customer> getAllCustomers();

    UserDto getByEmailAdress(String emailAddress);
}
