/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.entities.Wine;

/**
 *
 * @author Yi
 */
@Stateless
public class WineFacade extends AbstractFacade<Wine> {
    @PersistenceContext(unitName = "vintage_advantagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WineFacade() {
        super(Wine.class);
    }

    public int getCountByVarietal(String keyword) {
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.varietal LIKE :varietal");
        queryWine.setParameter("varietal", "%"+keyword+"%");
        return queryWine.getResultList().size();
        
    }
    
    
    public int getCountByPrice(int low, int high){
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.price>= :low AND a.price< :high ");
        queryWine.setParameter("low", low);
        queryWine.setParameter("high", high);
        
        return queryWine.getResultList().size();
        
    }

    

    public int getCountByRegion(String region) {
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.region =:region ");
        queryWine.setParameter("region", region);
        
        return queryWine.getResultList().size();
    }

    public List<Wine> fetchByVarietal(String keyword) {
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.varietal LIKE :varietal");
        queryWine.setParameter("varietal", "%"+keyword+"%");
        return queryWine.getResultList();
       
    }

    public List<Wine> fetchByPrice(int low, int high) {
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.price>= :low AND a.price< :high ");
        queryWine.setParameter("low", low);
        queryWine.setParameter("high", high);
        return queryWine.getResultList();
    }

    public List<Wine> fetchByRegion(String region) {
        Query queryWine =getEntityManager().createQuery("SELECT a FROM Wine a WHERE a.region =:region ");
        queryWine.setParameter("region", region);
        return queryWine.getResultList();
    }
    
    
    
    
}
