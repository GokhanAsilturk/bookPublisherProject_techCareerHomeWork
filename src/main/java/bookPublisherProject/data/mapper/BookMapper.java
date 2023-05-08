package bookPublisherProject.data.mapper;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring")
public interface BookMapper {

    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto convertToBookDto(Book book);

    Book createBook(CreateBookRequest createBookRequest);

    Book createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest);

    Book updateBook(UpdateBookRequest updateBookRequest);

    Book updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest);

    CreateBookRequest publishBook(PublishNewBookRequest publishNewBookRequest);



}