package bookPublisherProject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1, "Unkown Error!"),

    //DATA NOT FOUND Types:
    USER_DATA_NOT_FOUND(1001, "User Not Found!"),
    USER_LIST_NOT_FOUND(1002, "User List is Empty!"),

    AUTHOR_DATA_NOT_FOUND(1003, "Author Not Found!"),
    AUTHOR_LIST_NOT_FOUND(1004, "Author List is Empty!"),

    ADMIN_DATA_NOT_FOUND(1005, "Admin Not Found!"),
    ADMIN_LIST_NOT_FOUND(1006, "Admin List is Empty!"),

    BOOK_DATA_NOT_FOUND(1007, "Book Not Found!"),
    BOOK_LIST_NOT_FOUND(1008, "Book List is Empty!");
    //------------------------------------------------------------------

    private final Integer errorCode;
    private final String message;

}
