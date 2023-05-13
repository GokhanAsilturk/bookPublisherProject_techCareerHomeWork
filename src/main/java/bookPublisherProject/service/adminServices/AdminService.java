package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.request.userRequests.LoginRequest;
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
    public List<Admin> getAllAdmins() {
        return adminEntityService.getAll();
    }

    @Override
    public UserDto getByEmailAdress(String emailAddress) {
        return convertToUserDto(adminEntityService.getByEmailAddress(emailAddress));
    }

    public UserDto convertToUserDto(Admin admin){
        return admin.convertToUserDto();
    }
}

