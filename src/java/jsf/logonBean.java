/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import static java.lang.Math.log;
import static java.rmi.server.LogStream.log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jpa.entities.Account;
import jpa.entities.Creditcard;
import jpa.entities.CustomerOrder;
import jpa.session.AccountFacade;
import jsf.helper.md5encryption;

/**
 *
 * @author Yi
 */
@ManagedBean
@SessionScoped
public class logonBean implements Serializable {

    /**
     * Creates a new instance of logonBean
     */
    private String email;
    private String password;
    private Boolean flag;
    private Account current;
    
    @EJB
    private AccountFacade accountFacade;
    
    public logonBean() {
        flag=false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFlag() {
        return flag;
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
 
    
        public String Signin(){
        
        md5encryption md5en = new md5encryption();
        md5en.setStr(password);
        md5en.encrypt();
        current = getAccountFacade().findAccount(email,md5en.getDigest());
        FacesContext context = FacesContext.getCurrentInstance();
        
        //System.out.println("Current account :"+current.toString());
        
        if(current!=null) {
            flag=true;
            context.getExternalContext().getSessionMap().put("account", current);
            return "/index";
        }
        else{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("email/password does not match");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("signin-form:password",msg);
            fc.renderResponse();
            return  "";
        }
        
        
          
    }
    
    
    public int paymentType(Creditcard cc){
       
        return Integer.parseInt(cc.getCcnumber().substring(0,1));
          
    }
    
    public String showCard(Creditcard cc){
        
        String cardnumber = cc.getCcnumber();
        StringBuilder sb = new StringBuilder(cardnumber);
        for(int i=0; i<sb.length()-4; i++)
            sb.setCharAt(i,'*');
        
        return sb.toString();
        
    }

    
    
    public String signinCheckout(){
        
        md5encryption md5en = new md5encryption();
        md5en.setStr(password);
        md5en.encrypt();
        current = getAccountFacade().findAccount(email,md5en.getDigest());
        FacesContext context = FacesContext.getCurrentInstance();
        
        //System.out.println("Current account :"+current.toString());
        
        if(current!=null) {
            flag=true;
            context.getExternalContext().getSessionMap().put("account", current);
            CustomerOrder orderCurrent = (CustomerOrder)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("order");
            orderCurrent.setCustomerId(current.getCustomer());
            orderCurrent.setAddressId(current.getCustomer().getAddressIdDft());
            orderCurrent.setCcId(current.getCustomer().getCcIdDft());
            return "reviewOrder";
        }
        else{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("email/password does not match");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("signin-form:password",msg);
            fc.renderResponse();
            return  "";
        }
        
        
          
    }
    

    public String signout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
        
        return "/signin";
    }
    
    
   
    
    
}
