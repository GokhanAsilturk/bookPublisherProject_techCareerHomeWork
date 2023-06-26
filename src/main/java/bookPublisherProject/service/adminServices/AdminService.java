package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.dto.*;
import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.baseEntitties.BaseEntity;
import bookPublisherProject.data.entity.baseEntitties.ItemEntity;
import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.service.bookServices.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminEntityService adminEntityService;

    private final BookEntityService bookEntityService;

//    @Override
//    public UserDto login(LoginRequest loginRequest) {
//        return convertToUserDto(adminEntityService.login());
//    }

    @Override
    public UserDto createAdmin(CreateAdminRequest createAdminRequest) {
        AdminEntity adminEntity = adminEntityService.create(createAdminRequest.convertToEntity());
        return adminEntity.convertToUserDto();
    }

    @Override
    public ItemDetailedDto<Object> getDetailedBookById(String id) {
        BookEntity bookEntity = bookEntityService.getById(id);
        bookEntity.getAuthorEntity().setPassword("***");
        return convertToDetailedItemDto(bookEntity);
    }

    @Override
    public List<AdminDto> getAllAdmins() {

        List<AdminEntity> adminEntities = adminEntityService.getAll();

        if (adminEntities.isEmpty()) {
            throw new DataNotFoundException(ExceptionType.ADMIN_LIST_NOT_FOUND, "Admin list is Empty! :D");
        }
        return adminEntities.stream().map(AdminEntity::convertToDto).toList();
    }

    @Override
    public List<AdminDetailedDto> getAllAdminsDetailed() {

        List<AdminEntity> adminEntities = adminEntityService.getAll();

        if (adminEntities.isEmpty()) {
            throw new DataNotFoundException(ExceptionType.ADMIN_LIST_NOT_FOUND, "Admin list is Empty! :D");
        }
        return adminEntities.stream().map(this::convertToDetailedAdminDto).toList();
    }

    @Override
    public AdminDto getAdminByEmailAddress(String emailAddress) {

        return adminEntityService.getByEmailAddress(emailAddress).convertToDto();
    }

    @Override
    public AdminDetailedDto getAdminDetailedByEmailAddress(String emailAddress) {

        return convertToDetailedAdminDto(adminEntityService.getByEmailAddress(emailAddress));
    }

    public UserDetailedDto convertToDetailedUserDto(UserEntity userEntity) {
        return UserDetailedDto.builder()
                .userType(userEntity.getUserType())
                .emailAddress(userEntity.getEmailAddress())
                .id(userEntity.getId())
                .isDeleted(userEntity.getIsDeleted())
                .createdDate(userEntity.getCreatedDate())
                .lastModified(userEntity.getLastModified())
                .build();
    }

    public AdminDetailedDto convertToDetailedAdminDto(AdminEntity adminEntity) {
        return AdminDetailedDto.builder()
                .position(adminEntity.getPosition())
                .emailAddress(adminEntity.getEmailAddress())
                .id(adminEntity.getId())
                .isDeleted(adminEntity.getIsDeleted())
                .createdDate(adminEntity.getCreatedDate())
                .lastModified(adminEntity.getLastModified())
                .build();
    }

    public ItemDetailedDto<Object> convertToDetailedItemDto(ItemEntity itemEntity) {

        return ItemDetailedDto.builder()
                .optionalItem(itemEntity)
                .build();
    }

}

