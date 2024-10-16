package uk.co.imperatives.exercise.domain.exceptions;

public class GuestListException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public GuestListException(String mensagem) {
        super(mensagem);
    }

    public GuestListException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}