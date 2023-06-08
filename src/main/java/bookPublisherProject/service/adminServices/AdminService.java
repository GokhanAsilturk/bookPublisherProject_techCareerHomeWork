package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;
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
        return adminEntityService.getAll();
    }

    @Override
    public UserDto getByEmailAdress(String emailAddress) {
        return convertToUserDto(adminEntityService.getByEmailAddress(emailAddress));
    }

    public UserDto convertToUserDto(AdminEntity adminEntity) {
        return adminEntity.convertToUserDto();
    }
}

