/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jpa.entities.Address;
import jpa.entities.Creditcard;
import jpa.entities.Customer;
import jpa.session.AddressFacade;
import jpa.session.CreditcardFacade;
import jpa.session.CustomerFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@ViewScoped
public class creditcardBean implements Serializable{
    
    @ManagedProperty(value="#{logonBean.current.customer.ccIdDft}")
    private Creditcard ccDft;
    
    @ManagedProperty(value="#{logonBean.current.customer.creditcardCollection}")
    private List<Creditcard> customercards;
    
    @ManagedProperty(value="#{logonBean.current.customer}")
    private Customer customer; 

    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private CreditcardFacade creditcardFacade;
    /**
     * Creates a new instance of creditcardBean
     */
    public creditcardBean() {
    }

    public Creditcard getCcDft() {
        return ccDft;
    }

    public void setCcDft(Creditcard ccDft) {
        this.ccDft = ccDft;
    }

    public List<Creditcard> getCustomercards() {
        return customercards;
    }

    public void setCustomercards(List<Creditcard> customercards) {
        this.customercards = customercards;
    }

    

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public CreditcardFacade getCreditcardFacade() {
        return creditcardFacade;
    }
    
    
    
    
    
    public String remove(Creditcard card){
        
        
        
        getCreditcardFacade().remove(card);
       
        customercards.remove(card);
        
        
        return "cardManage?faces-redirect=true";
        
        
    }
    
    public String makeDefault(Creditcard card){
       customer.setCcIdDft(card);
       getCustomerFacade().edit(customer);
       return "cardManage?faces-redirect=true";
 
    }
    
    public String removeCheckOut(Creditcard card){
        
        
        
        getCreditcardFacade().remove(card);
       
        customercards.remove(card);
        
        
        return "orderPayment?faces-redirect=true";
        
        
    }
    
    public String makeDefaultCheckOut(Creditcard card){
       customer.setCcIdDft(card);
       getCustomerFacade().edit(customer);
       return "orderPayment?faces-redirect=true";
 
    }
    
    
    
    
}
