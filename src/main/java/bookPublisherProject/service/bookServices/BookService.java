package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bookPublisherProject.data.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {

        return BOOK_MAPPER.convertToBookDto(
                this.bookEntityService.save(BOOK_MAPPER.createBook(createBookRequest)));
    }

    @Override
    public BookDto softDeleteBook(int id) {

        return BOOK_MAPPER.convertToBookDto(this.bookEntityService.softDelete(id));
    }

    @Override
    public BookDto permanentlyDeleteBook(int id) {
        return BOOK_MAPPER.convertToBookDto(this.bookEntityService.permanentlyDelete(id));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.bookEntityService.getAll().stream()
                .map(BOOK_MAPPER::convertToBookDto).collect(Collectors.toList());
    }
}
