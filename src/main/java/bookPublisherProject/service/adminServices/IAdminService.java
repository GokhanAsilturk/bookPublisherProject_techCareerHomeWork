package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.request.userRequests.LoginRequest;

import java.util.List;

public interface IAdminService {

//UserDto login(LoginRequest loginRequest);

    List<Admin> getAllAdmins();

    UserDto getByEmailAdress(String emailAddress);
}
