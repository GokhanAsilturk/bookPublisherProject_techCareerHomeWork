package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.entity.users.AdminEntity;

import java.util.List;

public interface IAdminEntityService {

    AdminEntity create(AdminEntity adminEntity);

    List<AdminEntity> getAll();

    UserEntity login(AdminEntity adminEntity);

    AdminEntity getByEmailAddress(String emailAddress);
}
