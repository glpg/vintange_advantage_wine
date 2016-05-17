/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jpa.entities.Account;
import jpa.entities.Address;
import jpa.entities.Creditcard;
import jpa.entities.CustomerOrder;
import jpa.entities.OrderLine;
import jpa.session.CustomerOrderFacade;
import jpa.session.OrderLineFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@SessionScoped
public class shopCartBean implements Serializable {
    private ArrayList<OrderLine> cart;
    
    private double total;
    private int count;
    private CustomerOrder order;
    
    @EJB
    private CustomerOrderFacade customerorderFacade;
    
    @EJB
    private OrderLineFacade orderlineFacade;
    
    
    

    /**
     * Creates a new instance of shopCartBean
     */
    public shopCartBean() {
        cart= new ArrayList<>();
        total= 0;
    }

    public ArrayList<OrderLine> getCart() {
        return cart;
    }

    public void setCart(ArrayList<OrderLine> cart) {
        this.cart = cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public CustomerOrderFacade getCustomerorderFacade() {
        return customerorderFacade;
    }

    public void setCustomerorderFacade(CustomerOrderFacade customerorderFacade) {
        this.customerorderFacade = customerorderFacade;
    }

    public OrderLineFacade getOrderlineFacade() {
        return orderlineFacade;
    }

    public void setOrderlineFacade(OrderLineFacade orderlineFacade) {
        this.orderlineFacade = orderlineFacade;
    }
    
    
    

    
    
    public void addtoCart(OrderLine wineitem){
        System.out.println("added "+wineitem.getQuantity());
        for(OrderLine tmp: cart) {
            if (wineitem.getWineId().getId() == tmp.getWineId().getId()) {
                
                int count = tmp.getQuantity()+wineitem.getQuantity();
                tmp.setQuantity(count);
                double price = count * tmp.getWineId().getPrice().doubleValue();
                tmp.setPrice(BigDecimal.valueOf(price));
                calculateTotal(cart);
                calculateCount(cart);
                
                return;
            }
            
        }
        wineitem.setPrice(BigDecimal.valueOf(wineitem.getQuantity()* wineitem.getWineId().getPrice().doubleValue()));
        OrderLine ol = new OrderLine();
        ol.setWineId(wineitem.getWineId());
        ol.setQuantity(wineitem.getQuantity());
        ol.setPrice(wineitem.getPrice());
        cart.add(ol);
        calculateTotal(cart);
        calculateCount(cart);
        
     
    }
    
    public String remove(OrderLine item){
       
        for(int i=0; i< cart.size(); i++) {
            if (cart.get(i).getWineId().getId() == item.getWineId().getId()){
                cart.remove(i);
                break;
            }
           
        }
       
        calculateTotal(cart);
        calculateCount(cart);
        
        return "shoppingCart?faces-redirect=true";
        
    }
    public void update(){
        for(OrderLine item: cart) {
            
            item.setPrice(BigDecimal.valueOf(item.getQuantity()* item.getWineId().getPrice().doubleValue()));
            
        }
        calculateTotal(cart);
        calculateCount(cart);
        
    }
    
    
    
    
    
    private void calculateTotal(ArrayList<OrderLine> cart) {
        total=0;
        for(OrderLine item: cart) {
            total += item.getQuantity()* item.getWineId().getPrice().doubleValue();
        }
        
    }
    
    private void calculateCount(ArrayList<OrderLine> cart) {
        count=0;
        for(OrderLine item: cart) {
            count += item.getQuantity();
        }
    }
    
    public String checkout(){
        order=new CustomerOrder();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("order", order);
        
        Account acctCurrent = (Account)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("account");
        if(acctCurrent!=null) {
            order.setCustomerId(acctCurrent.getCustomer());
            order.setAddressId(acctCurrent.getCustomer().getAddressIdDft());
            order.setCcId(acctCurrent.getCustomer().getCcIdDft());
            return "reviewOrder";
        }
        else
            return "checkout";
        
    }
    
    public String changeShipAddr(Address addr) {
        order.setAddressId(addr);
        
        return "reviewOrder";
    }
    
    public String changePayment(Creditcard card) {
        order.setCcId(card);
        
        return "reviewOrder";
    }
    
    public String submitOrder(){
        order.setStatus("processing");
        order.setDate(new Date());
        
        getCustomerorderFacade().create(order);
        
        for(OrderLine ol: cart) {
            ol.setOrderId(order);
            getOrderlineFacade().create(ol);
            order.getOrderLineCollection().add(ol);
        }
        
        order.getCustomerId().getCustomerOrderCollection().add(order);
        reset();
        return "success";
    }
    
    private void reset(){
        total=0;
        count=0;
        order=null;
        cart.clear();
    }
    
}

