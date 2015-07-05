/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author emejia
 */
@FacesValidator("validadorEspacio")
public class ValidadorEspacio implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        HtmlInputText input = (HtmlInputText)component;
        String label;
        if(input.getLabel()==null || input.getLabel().trim().equals(""))
        {
            label=input.getId();
        }
        else
        {
            label=input.getLabel();
        }
        
        if(value.toString().trim().equals(""))
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR:", label+" es un campo obligatorio"));
        }
        
    }
    
    
}
