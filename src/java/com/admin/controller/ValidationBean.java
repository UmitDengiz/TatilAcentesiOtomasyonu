package com.admin.controller;


import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;


@Named
@SessionScoped
public class ValidationBean implements Serializable  {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        if (!matcher.matches()) {        matcher = pattern.matcher(value.toString());
            FacesMessage msg = new FacesMessage("E-mail formatinda giriniz.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);     
        }
    }
}
