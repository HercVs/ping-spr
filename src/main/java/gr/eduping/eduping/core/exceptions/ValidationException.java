package gr.eduping.eduping.core.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends Exception {
    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super("Validation error");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}