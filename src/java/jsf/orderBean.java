/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import jpa.entities.Creditcard;
import jpa.entities.Customer;
import jpa.entities.CustomerOrder;
import jpa.entities.OrderLine;

/**
 *
 * @author Yi
 */
@ManagedBean
@RequestScoped
public class orderBean {
    
    @ManagedProperty(value="#{logonBean.current.customer}")
    private Customer customer; 
    
    @ManagedProperty(value="#{logonBean.current.customer.customerOrderCollection}")
    private List<CustomerOrder> orders;
    
    CustomerOrder current;
    double orderTotal;


    /**
     * Creates a new instance of orderBean
     */
    public orderBean() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public CustomerOrder getCurrent() {
        return current;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    
    public void setCurrent(CustomerOrder current) {
        this.current = current;
        
    }
    
    
    
    public String viewDetail(CustomerOrder order){
        current=order;
        orderTotal=0;
        for(OrderLine ol: current.getOrderLineCollection()){
            orderTotal += ol.getPrice().doubleValue();
            
        }
        return "orderDetail";
        
    }
}
