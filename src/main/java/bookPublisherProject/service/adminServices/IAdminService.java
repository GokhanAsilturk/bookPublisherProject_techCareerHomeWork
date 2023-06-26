package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.AdminDetailedDto;
import bookPublisherProject.data.dto.AdminDto;
import bookPublisherProject.data.dto.ItemDetailedDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;

import java.util.List;

public interface IAdminService {

//UserDto login(LoginRequest loginRequest);

    UserDto createAdmin(CreateAdminRequest createAdminRequest) throws Exception;

    ItemDetailedDto<Object> getDetailedBookById(String id);

    List<AdminDto> getAllAdmins() throws Exception;

    List<AdminDetailedDto> getAllAdminsDetailed();

    AdminDto getAdminByEmailAddress(String emailAddress);

    AdminDetailedDto getAdminDetailedByEmailAddress(String id);
}
