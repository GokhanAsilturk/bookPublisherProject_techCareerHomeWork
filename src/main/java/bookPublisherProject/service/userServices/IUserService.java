package bookPublisherProject.service.userServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.request.userRequests.LoginRequest;

public interface IUserService {

    UserDto login(LoginRequest loginRequest);


}
