package java.com.javatech.finance.exceptions;

public class InsufficientFundsException extends SimpleBankingGlobalException {
    public InsufficientFundsException(String message, String code) {
        super(message, code);
    }
}
