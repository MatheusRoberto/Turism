/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "depedente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depedente.findAll", query = "SELECT d FROM Depedente d")
    , @NamedQuery(name = "Depedente.findByIddepedente", query = "SELECT d FROM Depedente d WHERE d.iddepedente = :iddepedente")
    , @NamedQuery(name = "Depedente.findByNome", query = "SELECT d FROM Depedente d WHERE d.nome = :nome")
    , @NamedQuery(name = "Depedente.findByRg", query = "SELECT d FROM Depedente d WHERE d.rg = :rg")})
public class Depedente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddepedente")
    private Integer iddepedente;
    @Column(name = "nome")
    private String nome;
    @Column(name = "rg")
    private String rg;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    public Depedente() {
    }

    public Depedente(Integer iddepedente) {
        this.iddepedente = iddepedente;
    }

    public Integer getIddepedente() {
        return iddepedente;
    }

    public void setIddepedente(Integer iddepedente) {
        this.iddepedente = iddepedente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepedente != null ? iddepedente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depedente)) {
            return false;
        }
        Depedente other = (Depedente) object;
        if ((this.iddepedente == null && other.iddepedente != null) || (this.iddepedente != null && !this.iddepedente.equals(other.iddepedente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Depedente[ iddepedente=" + iddepedente + " ]";
    }
    
}
