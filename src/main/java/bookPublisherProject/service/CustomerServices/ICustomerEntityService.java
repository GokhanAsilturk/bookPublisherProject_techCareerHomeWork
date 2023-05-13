package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Customer;

import java.util.List;

public interface ICustomerEntityService {

    List<Customer> getAll();

    User login(Customer customer);

    Customer getByEmailAddress(String emailAddress);
}
