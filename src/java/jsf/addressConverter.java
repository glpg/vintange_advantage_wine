/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import jpa.entities.Address;
import jpa.session.AddressFacade;
import jpa.session.CreditcardFacade;


/**
 *
 * @author Yi
 */
@ManagedBean
@RequestScoped
public class addressConverter implements Converter {
    
    
    @EJB
    private AddressFacade addressFacade; 
    
    public addressConverter() {
    }
    
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        else
            return String.valueOf(((Address) modelValue).getId());
        
}

    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    
    
    
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return getAddressFacade().find(Integer.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid address ID"), e);
        }
}
    
}
