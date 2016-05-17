/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yi
 */
@Entity
@Table(name = "order_line")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT o FROM OrderLine o"),
    @NamedQuery(name = "OrderLine.findById", query = "SELECT o FROM OrderLine o WHERE o.id = :id"),
    @NamedQuery(name = "OrderLine.findByQuantity", query = "SELECT o FROM OrderLine o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderLine.findByPrice", query = "SELECT o FROM OrderLine o WHERE o.price = :price")})
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CustomerOrder orderId;
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Wine wineId;

    public OrderLine() {
    }

    public OrderLine(Integer id) {
        this.id = id;
    }

    public OrderLine(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CustomerOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(CustomerOrder orderId) {
        this.orderId = orderId;
    }

    public Wine getWineId() {
        return wineId;
    }

    public void setWineId(Wine wineId) {
        this.wineId = wineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLine)) {
            return false;
        }
        OrderLine other = (OrderLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.OrderLine[ id=" + id + " ]";
    }
    
}
