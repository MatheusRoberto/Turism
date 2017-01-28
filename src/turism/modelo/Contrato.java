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
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdcontrato", query = "SELECT c FROM Contrato c WHERE c.idcontrato = :idcontrato")
    , @NamedQuery(name = "Contrato.findByDataemissao", query = "SELECT c FROM Contrato c WHERE c.dataemissao = :dataemissao")
    , @NamedQuery(name = "Contrato.findByQuantidade", query = "SELECT c FROM Contrato c WHERE c.quantidade = :quantidade")
    , @NamedQuery(name = "Contrato.findByPaga", query = "SELECT c FROM Contrato c WHERE c.paga = :paga")})
public class Contrato implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desconto")
    private Double desconto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Column(name = "dataemissao")
    @Temporal(TemporalType.DATE)
    private Date dataemissao;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "paga")
    private Boolean paga;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private List<Ocupantes> ocupantesList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;
    @JoinColumn(name = "idforma", referencedColumnName = "idformapagamento")
    @ManyToOne
    private Formapagamento idforma;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @JoinColumn(name = "idviagem", referencedColumnName = "idviagem")
    @ManyToOne
    private Viagem idviagem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private List<Parcela> parcelaList;

    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getPaga() {
        return paga;
    }

    public void setPaga(Boolean paga) {
        this.paga = paga;
    }

    @XmlTransient
    public List<Ocupantes> getOcupantesList() {
        return ocupantesList;
    }

    public void setOcupantesList(List<Ocupantes> ocupantesList) {
        this.ocupantesList = ocupantesList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Formapagamento getIdforma() {
        return idforma;
    }

    public void setIdforma(Formapagamento idforma) {
        this.idforma = idforma;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Viagem getIdviagem() {
        return idviagem;
    }

    public void setIdviagem(Viagem idviagem) {
        this.idviagem = idviagem;
    }

    @XmlTransient
    public List<Parcela> getParcelaList() {
        return parcelaList;
    }

    public void setParcelaList(List<Parcela> parcelaList) {
        this.parcelaList = parcelaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Contrato[ idcontrato=" + idcontrato + " ]";
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
}
