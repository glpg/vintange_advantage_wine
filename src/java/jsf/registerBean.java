package jsf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import jpa.entities.Account;
import jpa.entities.Customer;
import jpa.session.AccountFacade;
import jpa.session.CustomerFacade;
import jsf.helper.md5encryption;

/**
 *
 * @author Yi
 */
@ManagedBean
@RequestScoped
public class registerBean {

    private Account current; 
    private String confirmPwd;
    private Customer customer;
    
    @EJB
    private AccountFacade accountFacade;
    
    @EJB
    private CustomerFacade customerFacade;
    
   // @PersistenceContext(unitName = "vintage_advantagePU")
    //private EntityManager em;
    
    public registerBean() {
        current=new Account();
        customer=new Customer();
    }

    public Account getCurrent() {
        return current;
    }

    public void setCurrent(Account current) {
        this.current = current;
    }

    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    
    
    public String register() {
        
        if(!current.getPassword().equals(confirmPwd)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("resetpwd-form:pwdrepeat", msg);
            fc.renderResponse();
            
            return "";
            
        }
        else{
            md5encryption md5en = new md5encryption();
            md5en.setStr(current.getPassword());
            md5en.encrypt();
            current.setPassword(md5en.getDigest());
            
            getAccountFacade().create(current);
            customer.setAccountId(current);
            getCustomerFacade().create(customer);
            
            return "signin";
        }
        
      
        
    }
   
    
    
    
}
