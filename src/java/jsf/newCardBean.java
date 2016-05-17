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
import jpa.entities.Address;
import jpa.entities.Creditcard;
import jpa.entities.Customer;
import jpa.entities.CustomerOrder;
import jpa.session.CreditcardFacade;
import jpa.session.CustomerFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@ViewScoped
public class newCardBean implements Serializable {

    /**
     * Creates a new instance of newCardBean
     */
    private Creditcard card;
    private String month;
    private String year;
    private Address addr;
    private String ccnum;
    
    
    @ManagedProperty(value="#{logonBean.current.customer}")
    private Customer customer; 
    @ManagedProperty(value="#{shopCartBean.order}")
    private CustomerOrder order; 
    
    @EJB
    private CreditcardFacade creditcardFacade;
    @EJB
    private CustomerFacade customerFacade;
    
    public newCardBean() {
        
    }

    public CreditcardFacade getCreditcardFacade() {
        return creditcardFacade;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public Creditcard getCard() {
        return card;
    }

    public void setCard(Creditcard card) {
        this.card = card;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public String getCcnum() {
        return ccnum;
    }

    public void setCcnum(String ccnum) {
        this.ccnum = ccnum;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }
    
    public String register(){
        card=new Creditcard();
        
        card.setCustomerId(customer);
        card.setAddressId(addr);
        if (month.length()==1) 
            card.setCardExpirDate("0"+month+"/"+year);
        else
            card.setCardExpirDate(month+"/"+year);
        
        card.setCcnumber(ccnum);
        getCreditcardFacade().create(card);        
        customer.getCreditcardCollection().add(card);
        return "cardManage";
    }
    
    
    public String registerCheckOut(){
        
        
        card=new Creditcard();
        
        card.setCustomerId(order.getCustomerId());
        card.setAddressId(addr);
        if (month.length()==1) 
            card.setCardExpirDate("0"+month+"/"+year);
        else
            card.setCardExpirDate(month+"/"+year);
        
        card.setCcnumber(ccnum);
        getCreditcardFacade().create(card);
        customer.getCreditcardCollection().add(card);
        order.setCcId(card);
        return "reviewOrder";
        
    }
    
    
}
