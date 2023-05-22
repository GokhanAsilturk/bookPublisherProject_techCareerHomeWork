package bookPublisherProject.service.userServices;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;

import java.util.List;

public interface IUserEntityService {

    UserEntity login(UserEntity userEntity);

    UserEntity getUserById(String id);

    UserEntity getByEmailAddress(String emailAddress);

    List<UserEntity> getAllUsers();

}
