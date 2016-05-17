/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import jpa.entities.OrderLine;
import jpa.entities.Wine;
import jpa.session.WineFacade;
import jsf.helper.Pagination;

/**
 *
 * @author Yi
 */
@ManagedBean(eager=true)
@SessionScoped
public class wineBean implements Serializable{

    /**
     * Creates a new instance of wineBean
     */
    private Wine wine;
    private Pagination pagination;
    
    private List<Wine> wineList;
    private ArrayList<OrderLine> olList;
    private List<OrderLine> displayList;
   
   
    @EJB
    private WineFacade wineFacade;
    
    public wineBean() {
        olList = new ArrayList<OrderLine>();
        
    }

    public WineFacade getWineFacade() {
        return wineFacade;
    }

    public Wine getWine() {
        return wine;
    }

   

    public List<Wine> getWineList() {
        return wineList;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public void setWineList(List<Wine> wineList) {
        this.wineList = wineList;
    }

    public ArrayList<OrderLine> getOlList() {
        return olList;
    }

    public void setOlList(ArrayList<OrderLine> olList) {
        this.olList = olList;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<OrderLine> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<OrderLine> displayList) {
        this.displayList = displayList;
    }

    
    
    public int getCountByVarietal(String keyword){
        return getWineFacade().getCountByVarietal(keyword);
        
    }
    
    public int getCountByPrice(int low, int high){
        return getWineFacade().getCountByPrice(low, high);
        
    }
    
    public int getCountByRegion(String region){
        return getWineFacade().getCountByRegion(region);
        
    }
    
    
    public String fetchByVarietal(String keyword) {
        wineList = getWineFacade().fetchByVarietal(keyword);
        olList.clear();
        for(Wine tmp: wineList) {
            OrderLine newol = new OrderLine();
            newol.setWineId(tmp);
            newol.setQuantity(1);
            olList.add(newol);
        }
        pagination = new Pagination(olList.size());
        if(olList.size()>=10)
            displayList= olList.subList(0, 10);
        else
            displayList= olList.subList(0, olList.size());
        return "productList";
        
    }
    
    public String fetchByPrice(int low, int high) {
        wineList = getWineFacade().fetchByPrice(low, high);
        olList.clear();
        for(Wine tmp: wineList) {
            OrderLine newol = new OrderLine();
            newol.setWineId(tmp);
            newol.setQuantity(1);
            olList.add(newol);
        }
        
        pagination = new Pagination(olList.size());
        if(olList.size()>=10)
            displayList= olList.subList(0, 10);
        else
            displayList= olList.subList(0, olList.size());
        return "productList";
        
    }
    
    public String fetchByRegion(String region) {
        wineList = getWineFacade().fetchByRegion(region);
        olList.clear();
        for(Wine tmp: wineList) {
            OrderLine newol = new OrderLine();
            newol.setWineId(tmp);
            newol.setQuantity(1);
            olList.add(newol);
        }
        pagination = new Pagination(olList.size());
        if(olList.size()>=10)
            displayList= olList.subList(0, 10);
        else
            displayList= olList.subList(0, olList.size());
        return "productList";
        
    }
    
    public String showDetail(Wine wine) {
        this.wine=wine;
        return "productDetail";
    }

    public String showPage(int pageNumber) {
        pagination.setCurrentPage(pageNumber);
        int start = (pageNumber-1)*2;
        int stop= start+2;
        if (stop<=olList.size())
            displayList= olList.subList(start, stop);
        else
            displayList= olList.subList(start, olList.size());
        return "productList?faces-redirect=true";
        
    }
    
    public String goPrevious() {
        pagination.previous();
        return showPage(pagination.getCurrentPage());
        
    }
    
    public String goNext() {
        pagination.next();
        return showPage(pagination.getCurrentPage());
        
    }
    
    public String goFirst() {
        pagination.goFirst();
        return showPage(pagination.getCurrentPage());
        
    }
    
    public String goLast() {
        pagination.goLast();
        return showPage(pagination.getCurrentPage());
        
    }
    
    
}
