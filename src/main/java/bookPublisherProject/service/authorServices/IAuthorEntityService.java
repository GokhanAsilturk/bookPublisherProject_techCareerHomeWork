package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;

import java.util.List;

public interface IAuthorEntityService {

    Author save(Author author);

    Author permanentlyDelete(int id);
    Author softDelete(int id);

    List<Author> getAll();

    Author getById(int id);


}
