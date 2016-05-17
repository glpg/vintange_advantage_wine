/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jpa.entities.Account;
import jpa.entities.Address;
import jpa.entities.Customer;
import jpa.entities.CustomerOrder;
import jpa.session.AddressFacade;
import jpa.session.CustomerFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@ViewScoped
public class newAddrBean implements Serializable {
    
    private Address address;
    
    @ManagedProperty(value="#{logonBean.current.customer}")
    private Customer customer; 
    
    @ManagedProperty(value="#{shopCartBean.order}")
    private CustomerOrder order; 
    
    @EJB
    private AddressFacade addressFacade;
    
    @EJB
    private CustomerFacade customerFacade;
   

    /**
     * Creates a new instance of newAddrBean
     */
    public newAddrBean() {
        address = new Address();
       
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AddressFacade getAddressFacade() {
        return addressFacade;
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

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }
    
    
    public String register() {
        
        address.setCustomerId(customer);
        getAddressFacade().create(address);
        customer.getAddressCollection().add(address);
        return "addressManage";
        
    }
    
    public String registerCheckOut() {
        //System.out.println(customer.getAddressCollection().size());
        address.setCustomerId(customer);
        getAddressFacade().create(address);
        customer.getAddressCollection().add(address);
        order.setAddressId(address);
        
        return "reviewOrder";
        
    }
}
