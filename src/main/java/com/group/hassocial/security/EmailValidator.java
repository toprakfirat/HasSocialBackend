package com.group.hassocial.security;

import com.group.hassocial.data.dto.enums.universityDomains;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class EmailValidator{

    private final static String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateEmail(String email) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }

    public boolean checkIfUniversityEmail(String email) {
        return universityDomains.checksIfDomainIsValid(email.split("@")[1]);
    }
}
