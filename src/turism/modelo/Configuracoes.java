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
@Table(name = "configuracoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracoes.findAll", query = "SELECT c FROM Configuracoes c")
    , @NamedQuery(name = "Configuracoes.findByIdorigem", query = "SELECT c FROM Configuracoes c WHERE c.idorigem = :idorigem")
    , @NamedQuery(name = "Configuracoes.findByDatabackup", query = "SELECT c FROM Configuracoes c WHERE c.databackup = :databackup")
    , @NamedQuery(name = "Configuracoes.findByAgendadostatusbackup", query = "SELECT c FROM Configuracoes c WHERE c.agendadostatusbackup = :agendadostatusbackup")
    , @NamedQuery(name = "Configuracoes.findByIdconfiguracao", query = "SELECT c FROM Configuracoes c WHERE c.idconfiguracao = :idconfiguracao")})
public class Configuracoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idorigem")
    private Integer idorigem;
    @Column(name = "databackup")
    @Temporal(TemporalType.DATE)
    private Date databackup;
    @Column(name = "agendadostatusbackup")
    private Boolean agendadostatusbackup;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconfiguracao")
    private Integer idconfiguracao;

    public Configuracoes() {
    }

    public Configuracoes(Integer idconfiguracao) {
        this.idconfiguracao = idconfiguracao;
    }

    public Integer getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(Integer idorigem) {
        this.idorigem = idorigem;
    }

    public Date getDatabackup() {
        return databackup;
    }

    public void setDatabackup(Date databackup) {
        this.databackup = databackup;
    }

    public Boolean getAgendadostatusbackup() {
        return agendadostatusbackup;
    }

    public void setAgendadostatusbackup(Boolean agendadostatusbackup) {
        this.agendadostatusbackup = agendadostatusbackup;
    }

    public Integer getIdconfiguracao() {
        return idconfiguracao;
    }

    public void setIdconfiguracao(Integer idconfiguracao) {
        this.idconfiguracao = idconfiguracao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconfiguracao != null ? idconfiguracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracoes)) {
            return false;
        }
        Configuracoes other = (Configuracoes) object;
        if ((this.idconfiguracao == null && other.idconfiguracao != null) || (this.idconfiguracao != null && !this.idconfiguracao.equals(other.idconfiguracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Configuracoes[ idconfiguracao=" + idconfiguracao + " ]";
    }
    
}
