package bookPublisherProject.service.adminServices;

import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEntityService implements IAdminEntityService{

    private final AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public User login(Admin admin) {
        return null;
    }

    @Override
    public Admin getByEmailAddress(String emailAddress) {
        return adminRepository.findByEmailAddress(emailAddress);
    }
}
