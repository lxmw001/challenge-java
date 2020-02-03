package challenge.avalith.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
//    TODO: remove fields
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException( Class resourceClass, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : %s", resourceClass.getSimpleName(), fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}