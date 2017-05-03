package ru.rustem.validator;

import org.apache.wicket.validation.CompoundValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class UserNameValidator extends CompoundValidator {
    public UserNameValidator() {
        add(StringValidator.lengthBetween(5, 15));
        add(new PatternValidator("[a-z0-9_-]+"));
    }

}
