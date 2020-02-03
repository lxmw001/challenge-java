package challenge.avalith.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(Class resourceClass, String fieldName, Object fieldValue) {
        super(String.format("%s already exists for %s : %s", resourceClass.getSimpleName(), fieldName, fieldValue));
    }
}