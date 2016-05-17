/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yi
 */
@Entity
@Table(name = "wine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wine.findAll", query = "SELECT w FROM Wine w"),
    @NamedQuery(name = "Wine.findById", query = "SELECT w FROM Wine w WHERE w.id = :id"),
    @NamedQuery(name = "Wine.findByName", query = "SELECT w FROM Wine w WHERE w.name = :name"),
    @NamedQuery(name = "Wine.findByDescription", query = "SELECT w FROM Wine w WHERE w.description = :description"),
    @NamedQuery(name = "Wine.findByRegion", query = "SELECT w FROM Wine w WHERE w.region = :region"),
    @NamedQuery(name = "Wine.findByRating", query = "SELECT w FROM Wine w WHERE w.rating = :rating"),
    @NamedQuery(name = "Wine.findByPrice", query = "SELECT w FROM Wine w WHERE w.price = :price"),
    @NamedQuery(name = "Wine.findByYear", query = "SELECT w FROM Wine w WHERE w.year = :year"),
    @NamedQuery(name = "Wine.findByVarietal", query = "SELECT w FROM Wine w WHERE w.varietal = :varietal")})
public class Wine implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wineId")
    private Collection<OrderLine> orderLineCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "varietal")
    private String varietal;

    public Wine() {
    }

    public Wine(Integer id) {
        this.id = id;
    }

    public Wine(Integer id, String name, String description, String region, int rating, String year, String varietal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.region = region;
        this.rating = rating;
        this.year = year;
        this.varietal = varietal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
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
        if (!(object instanceof Wine)) {
            return false;
        }
        Wine other = (Wine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Wine[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<OrderLine> getOrderLineCollection() {
        return orderLineCollection;
    }

    public void setOrderLineCollection(Collection<OrderLine> orderLineCollection) {
        this.orderLineCollection = orderLineCollection;
    }
    
}
