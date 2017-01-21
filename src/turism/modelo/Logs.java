/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")
    , @NamedQuery(name = "Logs.findByIdlogs", query = "SELECT l FROM Logs l WHERE l.idlogs = :idlogs")
    , @NamedQuery(name = "Logs.findByAcao", query = "SELECT l FROM Logs l WHERE l.acao = :acao")
    , @NamedQuery(name = "Logs.findByData", query = "SELECT l FROM Logs l WHERE l.data = :data")
    , @NamedQuery(name = "Logs.findByHora", query = "SELECT l FROM Logs l WHERE l.hora = :hora")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogs")
    private Integer idlogs;
    @Column(name = "acao")
    private String acao;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

    public Logs() {
    }

    public Logs(Integer idlogs) {
        this.idlogs = idlogs;
    }

    public Integer getIdlogs() {
        return idlogs;
    }

    public void setIdlogs(Integer idlogs) {
        this.idlogs = idlogs;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
        hash += (idlogs != null ? idlogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idlogs == null && other.idlogs != null) || (this.idlogs != null && !this.idlogs.equals(other.idlogs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Logs[ idlogs=" + idlogs + " ]";
    }
    
}
