package bookPublisherProject.service.userServices;

import bookPublisherProject.data.entity.baseEntitties.User;

import java.util.List;

public interface IUserEntityService {

    User login(User user);

    User getUserById(String id);

    User getByEmailAddress(String emailAddress);

    List<User> getAllUsers();

}
