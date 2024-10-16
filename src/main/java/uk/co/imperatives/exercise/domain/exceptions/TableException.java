package uk.co.imperatives.exercise.domain.exceptions;

public class TableException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TableException(String mensagem) {
        super(mensagem);
    }

    public TableException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}