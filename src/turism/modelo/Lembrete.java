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
@Table(name = "lembrete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lembrete.findAll", query = "SELECT l FROM Lembrete l")
    , @NamedQuery(name = "Lembrete.findByIdlembrete", query = "SELECT l FROM Lembrete l WHERE l.idlembrete = :idlembrete")
    , @NamedQuery(name = "Lembrete.findByDescricao", query = "SELECT l FROM Lembrete l WHERE l.descricao = :descricao")})
public class Lembrete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlembrete")
    private Integer idlembrete;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

    public Lembrete() {
    }

    public Lembrete(Integer idlembrete) {
        this.idlembrete = idlembrete;
    }

    public Integer getIdlembrete() {
        return idlembrete;
    }

    public void setIdlembrete(Integer idlembrete) {
        this.idlembrete = idlembrete;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlembrete != null ? idlembrete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lembrete)) {
            return false;
        }
        Lembrete other = (Lembrete) object;
        if ((this.idlembrete == null && other.idlembrete != null) || (this.idlembrete != null && !this.idlembrete.equals(other.idlembrete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Lembrete[ idlembrete=" + idlembrete + " ]";
    }
    
}
