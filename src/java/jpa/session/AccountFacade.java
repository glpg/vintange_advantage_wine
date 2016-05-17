/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.entities.Account;

/**
 *
 * @author Yi
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {
    @PersistenceContext(unitName = "vintage_advantagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public List<Account> queryByEmail(String email) {
        Query queryEmail =getEntityManager().createNamedQuery("Account.findByEmail");
        queryEmail.setParameter("email", email);
        return queryEmail.getResultList();
        
    }
    
    public Account findAccount(String email, String password) {
        Query queryAcct =getEntityManager().createQuery("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password");
        queryAcct.setParameter("email", email);
        queryAcct.setParameter("password",password);
        try{
           Account current= (Account)queryAcct.getSingleResult();
           getEntityManager().refresh(current);
           return current;
        }
        catch(NoResultException ex){ 
            return null;
        }
     
    }

    public Account findAccount(String email) {
        Query queryAcct =getEntityManager().createQuery("SELECT a FROM Account a WHERE a.email = :email");
        queryAcct.setParameter("email", email);
        
        try{
           Account current= (Account)queryAcct.getSingleResult();
           getEntityManager().refresh(current);
           return current;
        }
        catch(NoResultException ex){ 
            return null;
        }
    }
}
