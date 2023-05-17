package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Customer;

import java.util.List;

public interface ICustomerEntityService {

    Customer create(Customer customer);
    List<Customer> getAll();

    Customer getByEmailAddress(String emailAddress);

    Customer getById(String id);
}
