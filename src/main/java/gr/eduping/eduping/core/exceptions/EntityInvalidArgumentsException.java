package gr.eduping.eduping.core.exceptions;

public class EntityInvalidArgumentsException extends EntityGenericException {

    private static final String DEFAULT_CODE = "InvalidArguments";

    public EntityInvalidArgumentsException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}