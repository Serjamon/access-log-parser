import javax.naming.CannotProceedException;

public class CannotParseLogException extends CannotProceedException {

    //ToDo вписать нормальное сообщение
    public CannotParseLogException(String explanation) {
        super(explanation);
    }
}
