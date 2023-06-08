package bookPublisherProject.service.userServices;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityService implements IUserEntityService {

    private final UserRepository userRepository;

    @Override
    public UserEntity login(UserEntity userEntity) {

        return this.getByEmailAddress(userEntity.getEmailAddress());
    }

    @Override
    public UserEntity getUserById(String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found:)"));
    }

    @Override
    public UserEntity getByEmailAddress(String emailAddress) {

        return (UserEntity) userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new NotFoundException("user not found:)"));
    }

    @Override
    public List<UserEntity> getAllUsers() {

        return userRepository.findAllByIsDeletedFalse().orElseThrow(() -> new NotFoundException("users not found! :)"));
    }
}
