package bookPublisherProject.service.userServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.request.userRequests.LoginRequest;
import bookPublisherProject.service.CustomerServices.CustomerEntityService;
import bookPublisherProject.service.adminServices.AdminEntityService;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserEntityService userEntityService;

    private final AuthorEntityService authorEntityService;

    private final AdminEntityService adminEntityService;

    private final CustomerEntityService customerEntityService;


    @Override
    public UserDto login(LoginRequest loginRequest) {

        if (userIsValid(loginRequest)) {
            return userEntityService.getByEmailAddress(loginRequest.emailAddress()).convertToUserDto();
        } else {
            throw new IllegalStateException("yanlış yaptın");
        }
    }


    public boolean passwordIsValid(LoginRequest loginRequest) {

        return loginRequest.password().equals(
                userEntityService.getByEmailAddress(loginRequest.emailAddress()).getPassword());
    }

    public boolean emailIsValid(LoginRequest loginRequest) {
        return userEntityService.getAllUsers()
                .stream()
                .map(UserEntity::getEmailAddress)
                .toList()
                .contains(loginRequest.emailAddress());
    }

    public boolean userIsValid(LoginRequest loginRequest) {

        return (emailIsValid(loginRequest) && passwordIsValid(loginRequest));
    }


}