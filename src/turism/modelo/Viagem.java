/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "viagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viagem.findAll", query = "SELECT v FROM Viagem v")
    , @NamedQuery(name = "Viagem.findByIdviagem", query = "SELECT v FROM Viagem v WHERE v.idviagem = :idviagem")
    , @NamedQuery(name = "Viagem.findByDataida", query = "SELECT v FROM Viagem v WHERE v.dataida = :dataida")
    , @NamedQuery(name = "Viagem.findByDatavolta", query = "SELECT v FROM Viagem v WHERE v.datavolta = :datavolta")
    , @NamedQuery(name = "Viagem.findByHorariosaida", query = "SELECT v FROM Viagem v WHERE v.horariosaida = :horariosaida")
    , @NamedQuery(name = "Viagem.findByDescricao", query = "SELECT v FROM Viagem v WHERE v.descricao = :descricao")
    , @NamedQuery(name = "Viagem.findByVagasextras", query = "SELECT v FROM Viagem v WHERE v.vagasextras = :vagasextras")})
public class Viagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idviagem")
    private Integer idviagem;
    @Column(name = "dataida")
    @Temporal(TemporalType.DATE)
    private Date dataida;
    @Column(name = "datavolta")
    @Temporal(TemporalType.DATE)
    private Date datavolta;
    @Column(name = "horariosaida")
    @Temporal(TemporalType.TIME)
    private Date horariosaida;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "vagasextras")
    private Integer vagasextras;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viagem")
    private List<Veiculoscontratados> veiculoscontratadosList;
    @OneToMany(mappedBy = "idviagem")
    private List<Contrato> contratoList;
    @JoinColumn(name = "idorigem", referencedColumnName = "idcidade")
    @ManyToOne
    private Cidade idorigem;
    @JoinColumn(name = "iddestino", referencedColumnName = "idcidade")
    @ManyToOne
    private Cidade iddestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viagem")
    private List<Hoteiscontratados> hoteiscontratadosList;

    public Viagem() {
    }

    public Viagem(Integer idviagem) {
        this.idviagem = idviagem;
    }

    public Integer getIdviagem() {
        return idviagem;
    }

    public void setIdviagem(Integer idviagem) {
        this.idviagem = idviagem;
    }

    public Date getDataida() {
        return dataida;
    }

    public void setDataida(Date dataida) {
        this.dataida = dataida;
    }

    public Date getDatavolta() {
        return datavolta;
    }

    public void setDatavolta(Date datavolta) {
        this.datavolta = datavolta;
    }

    public Date getHorariosaida() {
        return horariosaida;
    }

    public void setHorariosaida(Date horariosaida) {
        this.horariosaida = horariosaida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getVagasextras() {
        return vagasextras;
    }

    public void setVagasextras(Integer vagasextras) {
        this.vagasextras = vagasextras;
    }

    @XmlTransient
    public List<Veiculoscontratados> getVeiculoscontratadosList() {
        return veiculoscontratadosList;
    }

    public void setVeiculoscontratadosList(List<Veiculoscontratados> veiculoscontratadosList) {
        this.veiculoscontratadosList = veiculoscontratadosList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public Cidade getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(Cidade idorigem) {
        this.idorigem = idorigem;
    }

    public Cidade getIddestino() {
        return iddestino;
    }

    public void setIddestino(Cidade iddestino) {
        this.iddestino = iddestino;
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
        hash += (idviagem != null ? idviagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viagem)) {
            return false;
        }
        Viagem other = (Viagem) object;
        if ((this.idviagem == null && other.idviagem != null) || (this.idviagem != null && !this.idviagem.equals(other.idviagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Viagem[ idviagem=" + idviagem + " ]";
    }
    
}
