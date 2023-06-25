package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.entity.users.AdminEntity;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEntityService implements IAdminEntityService {

    private final AdminRepository adminRepository;

    @Override
    public AdminEntity create(AdminEntity adminEntity) {

        return adminRepository.save(adminEntity);
    }

    @Override
    public List<AdminEntity> getAll() {
        return adminRepository.findAllByIsDeletedFalse().orElseThrow(()->
                new DataNotFoundException(ExceptionType.ADMIN_LIST_NOT_FOUND,"Admin List is Empty! :)"));
    }

    @Override
    public UserEntity login(AdminEntity adminEntity) {

        return null;
    }

    @Override
    public AdminEntity getByEmailAddress(String emailAddress) {
        return adminRepository.findByEmailAddress(emailAddress).orElseThrow(() ->
                new DataNotFoundException(ExceptionType.ADMIN_DATA_NOT_FOUND,"Admin Not Found! :)"));
    }
}
