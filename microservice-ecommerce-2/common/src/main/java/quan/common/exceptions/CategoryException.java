package quan.common.exceptions;

import lombok.Getter;

@Getter
public class CategoryException extends RuntimeException{
    private String message;

    public CategoryException(String message){
        super(message);
        this.message = message;
    }
}
