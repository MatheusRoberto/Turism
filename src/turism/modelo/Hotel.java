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
@Table(name = "hotel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h")
    , @NamedQuery(name = "Hotel.findByIdhotel", query = "SELECT h FROM Hotel h WHERE h.idhotel = :idhotel")
    , @NamedQuery(name = "Hotel.findByNome", query = "SELECT h FROM Hotel h WHERE h.nome = :nome")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhotel")
    private Integer idhotel;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "idhotel")
    private List<Quarto> quartoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<Hoteiscontratados> hoteiscontratadosList;

    public Hotel() {
    }

    public Hotel(Integer idhotel) {
        this.idhotel = idhotel;
    }

    public Integer getIdhotel() {
        return idhotel;
    }

    public void setIdhotel(Integer idhotel) {
        this.idhotel = idhotel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Quarto> getQuartoList() {
        return quartoList;
    }

    public void setQuartoList(List<Quarto> quartoList) {
        this.quartoList = quartoList;
    }

    @XmlTransient
    public List<Hoteiscontratados> getHoteiscontratadosList() {
        return hoteiscontratadosList;
    }

    public void setHoteiscontratadosList(List<Hoteiscontratados> hoteiscontratadosList) {
        this.hoteiscontratadosList = hoteiscontratadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhotel != null ? idhotel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.idhotel == null && other.idhotel != null) || (this.idhotel != null && !this.idhotel.equals(other.idhotel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Hotel[ idhotel=" + idhotel + " ]";
    }
    
}
