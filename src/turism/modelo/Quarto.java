/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q")
    , @NamedQuery(name = "Quarto.findByIdquarto", query = "SELECT q FROM Quarto q WHERE q.idquarto = :idquarto")
    , @NamedQuery(name = "Quarto.findByCamacasal", query = "SELECT q FROM Quarto q WHERE q.camacasal = :camacasal")
    , @NamedQuery(name = "Quarto.findByCamasolteiro", query = "SELECT q FROM Quarto q WHERE q.camasolteiro = :camasolteiro")
    , @NamedQuery(name = "Quarto.findByObservacao", query = "SELECT q FROM Quarto q WHERE q.observacao = :observacao")})
public class Quarto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idquarto")
    private Integer idquarto;
    @Column(name = "camacasal")
    private Integer camacasal;
    @Column(name = "camasolteiro")
    private Integer camasolteiro;
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "idhotel", referencedColumnName = "idhotel")
    @ManyToOne
    private Hotel idhotel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quarto")
    private List<Ocupantes> ocupantesList;

    public Quarto() {
    }

    public Quarto(Integer idquarto) {
        this.idquarto = idquarto;
    }

    public Integer getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(Integer idquarto) {
        this.idquarto = idquarto;
    }

    public Integer getCamacasal() {
        return camacasal;
    }

    public void setCamacasal(Integer camacasal) {
        this.camacasal = camacasal;
    }

    public Integer getCamasolteiro() {
        return camasolteiro;
    }

    public void setCamasolteiro(Integer camasolteiro) {
        this.camasolteiro = camasolteiro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Hotel getIdhotel() {
        return idhotel;
    }

    public void setIdhotel(Hotel idhotel) {
        this.idhotel = idhotel;
    }

    @XmlTransient
    public List<Ocupantes> getOcupantesList() {
        return ocupantesList;
    }

    public void setOcupantesList(List<Ocupantes> ocupantesList) {
        this.ocupantesList = ocupantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquarto != null ? idquarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.idquarto == null && other.idquarto != null) || (this.idquarto != null && !this.idquarto.equals(other.idquarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Quarto[ idquarto=" + idquarto + " ]";
    }
    
}
