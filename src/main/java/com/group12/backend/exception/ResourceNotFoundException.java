package com.group12.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles it when a request resource is not found.
 * As you can see it extends RunTimeException.
 */
@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    /**
     * When resource is not found, this function is called to handle exception.
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * ResourceName getter
     * @return
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * FieldName getter
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * FieldValue getter
     * @return
     */
    public Object getFieldValue() {
        return fieldValue;
    }
}
