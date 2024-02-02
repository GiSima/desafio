package buy.desafio.api.infra;

public class PurchaseNotAllowedException extends RuntimeException{
    public PurchaseNotAllowedException(String message) {
        super(message);
    }
}
