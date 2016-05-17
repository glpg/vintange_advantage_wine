/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Creditcard;

/**
 *
 * @author Yi
 */
@Stateless
public class CreditcardFacade extends AbstractFacade<Creditcard> {
    @PersistenceContext(unitName = "vintage_advantagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditcardFacade() {
        super(Creditcard.class);
    }
    
}
