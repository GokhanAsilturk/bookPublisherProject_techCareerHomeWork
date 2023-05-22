package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;

import java.util.List;

public interface IAdminService {

//UserDto login(LoginRequest loginRequest);

    UserDto createAdmin(CreateAdminRequest createAdminRequest);

    List<AdminEntity> getAllAdmins();

    UserDto getByEmailAdress(String emailAddress);
}
