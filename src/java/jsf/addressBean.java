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
import jpa.entities.Customer;
import jpa.session.AddressFacade;
import jpa.session.CustomerFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@ViewScoped
public class addressBean implements Serializable {
    
    @ManagedProperty(value="#{logonBean.current.customer.addressIdDft}")
    private Address addrDft;
    
    @ManagedProperty(value="#{logonBean.current.customer.addressCollection}")
    private List<Address> customeraddresses;
    
    @ManagedProperty(value="#{logonBean.current.customer}")
    private Customer customer; 
    
   
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private AddressFacade addressFacade;
    
   

    /**
     * Creates a new instance of addressBean
     */
    public addressBean() {
    }

    public Address getAddrDft() {
        return addrDft;
    }

    public void setAddrDft(Address addrDft) {
        this.addrDft = addrDft;
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

    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    
    
    
    
    public void setAddressFacade(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    public List<Address> getCustomeraddresses() {
        return customeraddresses;
    }

    public void setCustomeraddresses(List<Address> customeraddresses) {
        this.customeraddresses = customeraddresses;
    }
    
    
    
    public String edit(Address addr){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("changeAddr", addr);
        
        return "editAddr";
        
    }
    
    public String editCheckOut(Address addr){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("changeAddr", addr);
        
        return "orderEditAddr";
        
    }
    
    public String remove(Address addr){
        
        
        
        getAddressFacade().remove(addr);
       
        customer.getAddressCollection().remove(addr);
        
        
        return "addressManage?faces-redirect=true";
        
        
    }
    
    public String makeDefault(Address addr){
       customer.setAddressIdDft(addr);
       getCustomerFacade().edit(customer);
       String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
       return viewId+"?faces-redirect=true";
 
    }
    
    public String update(Address addr) {
        getAddressFacade().edit(addr);
        return "addressManage?faces-redirect=true";
    }
    
    public String updateCheckOut(Address addr) {
        getAddressFacade().edit(addr);
        
        return "reviewOrder";
    }

 
}
