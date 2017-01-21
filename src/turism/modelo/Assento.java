/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "assento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assento.findAll", query = "SELECT a FROM Assento a")
    , @NamedQuery(name = "Assento.findByIdassento", query = "SELECT a FROM Assento a WHERE a.assentoPK.idassento = :idassento")
    , @NamedQuery(name = "Assento.findByIdcontrato", query = "SELECT a FROM Assento a WHERE a.assentoPK.idcontrato = :idcontrato")
    , @NamedQuery(name = "Assento.findByIdveiculo", query = "SELECT a FROM Assento a WHERE a.assentoPK.idveiculo = :idveiculo")
    , @NamedQuery(name = "Assento.findByNome", query = "SELECT a FROM Assento a WHERE a.nome = :nome")
    , @NamedQuery(name = "Assento.findByRg", query = "SELECT a FROM Assento a WHERE a.rg = :rg")})
public class Assento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AssentoPK assentoPK;
    @Column(name = "nome")
    private String nome;
    @Column(name = "rg")
    private String rg;

    public Assento() {
    }

    public Assento(AssentoPK assentoPK) {
        this.assentoPK = assentoPK;
    }

    public Assento(int idassento, int idcontrato, int idveiculo) {
        this.assentoPK = new AssentoPK(idassento, idcontrato, idveiculo);
    }

    public AssentoPK getAssentoPK() {
        return assentoPK;
    }

    public void setAssentoPK(AssentoPK assentoPK) {
        this.assentoPK = assentoPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assentoPK != null ? assentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assento)) {
            return false;
        }
        Assento other = (Assento) object;
        if ((this.assentoPK == null && other.assentoPK != null) || (this.assentoPK != null && !this.assentoPK.equals(other.assentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Assento[ assentoPK=" + assentoPK + " ]";
    }
    
}
