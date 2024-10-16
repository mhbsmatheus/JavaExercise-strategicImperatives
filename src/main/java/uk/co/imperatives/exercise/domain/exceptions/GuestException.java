package uk.co.imperatives.exercise.domain.exceptions;

public class GuestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public GuestException(String mensagem) {
        super(mensagem);
    }

    public GuestException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}