package bookPublisherProject.service.CustomerServices;

import bookPublisherProject.data.entity.users.CustomerEntity;

import java.util.List;

public interface ICustomerEntityService {

    CustomerEntity create(CustomerEntity customerEntity);

    List<CustomerEntity> getAll();

    CustomerEntity getByEmailAddress(String emailAddress);

    CustomerEntity getById(String id);
}
