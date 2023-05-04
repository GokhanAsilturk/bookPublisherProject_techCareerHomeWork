package bookPublisherProject.data.mapper;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "BookMapperImpl",componentModel = "spring" )
public interface BookMapper {

    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto convertToBookDto(Book book);

    Book createBook(CreateBookRequest createBookRequest);

}