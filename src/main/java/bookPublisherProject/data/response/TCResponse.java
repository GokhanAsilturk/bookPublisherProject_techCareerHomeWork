package bookPublisherProject.data.response;

import lombok.Builder;


@Builder
public record TCResponse<T>(
        T response,
        boolean isSuccess,
        String errorMessage) {

}
