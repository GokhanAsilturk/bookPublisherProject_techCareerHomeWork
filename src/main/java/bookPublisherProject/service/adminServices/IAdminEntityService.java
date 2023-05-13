package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.baseEntitties.User;

import java.util.List;

public interface IAdminEntityService {

    List<Admin> getAll();
    User login(Admin admin);

    Admin getByEmailAddress(String emailAddress);
}
