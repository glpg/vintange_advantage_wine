/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jpa.entities.Account;
import jpa.entities.Customer;
import jpa.session.AccountFacade;
import jpa.session.CustomerFacade;

/**
 *
 * @author Yi
 */
@ManagedBean
@ViewScoped
public class updateBean {
    
    @ManagedProperty(value="#{logonBean.current}")
    private Account account; 
    //all updated information here
    private String pwd;
    private String confirmPwd;
    private String infoMessage;
    private String phone;

    @EJB
    private AccountFacade accountFacade;
    
    @EJB
    private CustomerFacade customerFacade;

    /**
     * Creates a new instance of updateBean
     */
    public updateBean() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    
    
   
    public String resetPwd() {
         if(!pwd.equals(confirmPwd)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("newacct-form:pwdrepeat", msg);
            fc.renderResponse();
            
            return "";
            
        }
        else{
            account.setPassword(pwd);
 
            getAccountFacade().edit(account);
            infoMessage = "Your password has been reset successfully.";
            
            return "";
        }
        
    }
    
    public String updatePhone() {
        account.getCustomer().setPhone(phone);
        getCustomerFacade().edit(account.getCustomer());
        return "accountManage";
    }
    
    
    
    
    
}
