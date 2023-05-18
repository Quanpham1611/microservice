package quan.common.exceptions;

public class ProductQuantityUnavailableException extends RuntimeException{
    private String message;

    public ProductQuantityUnavailableException(String message){
        super(message);
        this.message = message;
    }
}
