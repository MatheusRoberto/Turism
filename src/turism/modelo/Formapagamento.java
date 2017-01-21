/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "formapagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formapagamento.findAll", query = "SELECT f FROM Formapagamento f")
    , @NamedQuery(name = "Formapagamento.findByIdformapagamento", query = "SELECT f FROM Formapagamento f WHERE f.idformapagamento = :idformapagamento")
    , @NamedQuery(name = "Formapagamento.findByNome", query = "SELECT f FROM Formapagamento f WHERE f.nome = :nome")})
public class Formapagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformapagamento")
    private Integer idformapagamento;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "idforma")
    private List<Contrato> contratoList;

    public Formapagamento() {
    }

    public Formapagamento(Integer idformapagamento) {
        this.idformapagamento = idformapagamento;
    }

    public Integer getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(Integer idformapagamento) {
        this.idformapagamento = idformapagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformapagamento != null ? idformapagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formapagamento)) {
            return false;
        }
        Formapagamento other = (Formapagamento) object;
        if ((this.idformapagamento == null && other.idformapagamento != null) || (this.idformapagamento != null && !this.idformapagamento.equals(other.idformapagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Formapagamento[ idformapagamento=" + idformapagamento + " ]";
    }
    
}
