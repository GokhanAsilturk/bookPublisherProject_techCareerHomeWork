package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;
import bookPublisherProject.exception.CustomExceptionHandler;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
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
    public UserDto createAdmin(CreateAdminRequest createAdminRequest) throws Exception {
        try{
            return adminEntityService.create(createAdminRequest.convertToEntity()).convertToUserDto();
        }catch(Exception e){
            throw new Exception();
        }

    }

    @Override
    public List<AdminEntity> getAllAdmins() throws Exception {
try {
    return adminEntityService.getAll();
}catch(Exception e){
    throw new Exception(e.getMessage());
}
    }

    @Override
    public UserDto getByEmailAdress(String emailAddress) {

        return convertToUserDto(adminEntityService.getByEmailAddress(emailAddress));
    }

    public UserDto convertToUserDto(AdminEntity adminEntity) {
        return adminEntity.convertToUserDto();
    }
}

