package buy.desafio.api.infra;

public class PurchaseException extends RuntimeException{
    public PurchaseException(String message) {
        super(message);
    }
}
