package bookPublisherProject.service.userServices;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.entity.users.Author;
import bookPublisherProject.data.entity.users.Customer;
import bookPublisherProject.data.request.userRequests.LoginRequest;
import bookPublisherProject.service.CustomerServices.CustomerEntityService;
import bookPublisherProject.service.adminServices.AdminEntityService;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final AuthorEntityService authorEntityService;

    private final AdminEntityService adminEntityService;

    private final CustomerEntityService customerEntityService;


    @Override
    public UserDto login(LoginRequest loginRequest) {


//        HashMap<UserType, User> userTypeMap = new HashMap<>();
//        userTypeMap.put(ADMIN, new Admin());
//        userTypeMap.put(CUSTOMER, new Customer());
//        userTypeMap.put(AUTHOR, new Author());


        //!!!! PASSWORD KONTROLÜ HENÜZ YAPILMADI
        if (authorEntityService.getAll()
                .stream()
                .map(Author::getEmailAddress)
                .toList()
                .contains(loginRequest.emailAddress())) {
            return authorEntityService.getByEmailAdress(loginRequest.emailAddress()).convertToUserDto();

        } else if (
                adminEntityService.getAll().stream()
                        .map(Admin::getEmailAddress)
                        .toList()
                        .contains(loginRequest.emailAddress())) {
            return adminEntityService.getByEmailAddress(loginRequest.emailAddress()).convertToUserDto();

        } else if (customerEntityService.getAll().stream()
                .map(Customer::getEmailAddress)
                .toList()
                .contains(loginRequest.emailAddress())) {
            return customerEntityService.getByEmailAddress(loginRequest.emailAddress()).convertToUserDto();
        }
        return null;
    }
}