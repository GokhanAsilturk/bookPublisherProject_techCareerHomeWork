package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;
import bookPublisherProject.exception.AdminNotFoundException;
import bookPublisherProject.exception.UserListIsEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final AdminEntityService adminEntityService;


//    @Override
//    public UserDto login(LoginRequest loginRequest) {
//        return convertToUserDto(adminEntityService.login());
//    }

    @Override
    public UserDto createAdmin(CreateAdminRequest createAdminRequest) {
        return adminEntityService.create(createAdminRequest.convertToEntity()).convertToUserDto();
    }

    @Override
    public List<AdminEntity> getAllAdmins() {
        if (adminEntityService.getAll().isEmpty()) {
            throw new UserListIsEmptyException("Admin List is Empty! :D");
        }
        return adminEntityService.getAll();
    }

    @Override
    public UserDto getByEmailAdress(String emailAddress) {
        if (adminEntityService.getByEmailAddress(emailAddress) == null) {
            throw new AdminNotFoundException("Admin not found! :D");
        }
        return convertToUserDto(adminEntityService.getByEmailAddress(emailAddress));
    }

    public UserDto convertToUserDto(AdminEntity adminEntity) {
        return adminEntity.convertToUserDto();
    }
}

