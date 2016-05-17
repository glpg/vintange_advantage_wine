/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper;

import javax.faces.bean.ManagedBean;



/**
 *
 * @author Yi
 */

@ManagedBean
public class Pagination {
    private Integer currentPage;
    private Integer[] visiblePages;
    private int count;
    private int totalPages;
    private int max;
    
    public Pagination(int count){
        int temp = 10;
        this.count=count;
        if(count%temp==0) 
            totalPages=count/temp;
        else
            totalPages = count/temp+1;
        currentPage=1;
        if (totalPages>=3){
            visiblePages=new Integer[]{1,2,3};
            max=3;}
        else{
            visiblePages=new Integer[totalPages];
            max=totalPages;
            for(int i=0; i<totalPages; i++)
                
                visiblePages[i]=i+1;
                
        }
        
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Integer[] getVisiblePages() {
        return visiblePages;
    }

    public void setVisiblePages(Integer[] visiblePages) {
        this.visiblePages = visiblePages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    
    
    public void next(){
        if((currentPage+1)<=totalPages) currentPage++;
        
        
        if ((visiblePages[max-1]+1)<=totalPages) {
            for(int i=0; i<max; i++)
                visiblePages[i]++;
            
        }
    }   
   
    public void previous(){
        if((currentPage-1)>=1) currentPage--;
        
        if ((visiblePages[0]-1)>=1) {
            for(int i=0; i<max; i++)
                visiblePages[i]--;
                
            
        }
    }   
    
    public void goFirst(){
        currentPage=1;
        
        for(int i=0; i<max; i++)
            visiblePages[i]=i+1;
        
        
    }
    
    
    public void goLast(){
        currentPage=totalPages;
        
        
        for(int i=0; i<max; i++)
            visiblePages[max-i-1]=totalPages-i;
    }
    
    public boolean reachLast(){
        if(max>0) {
            if(visiblePages[max-1]==totalPages) return true;
            else return false;
            
        }
        else return true;
        
    }
}
