package bookPublisherProject.service.userServices;

import bookPublisherProject.data.entity.baseEntitties.User;
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
    public User login(User user) {

        return this.getByEmailAddress(user.getEmailAddress());
    }

    @Override
    public User getUserById(String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found:)"));
    }

    @Override
    public User getByEmailAddress(String emailAddress) {

        return (User) userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new NotFoundException("user not found:)"));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
