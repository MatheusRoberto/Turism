/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "parcela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcela.findAll", query = "SELECT p FROM Parcela p")
    , @NamedQuery(name = "Parcela.findByIdparcela", query = "SELECT p FROM Parcela p WHERE p.parcelaPK.idparcela = :idparcela")
    , @NamedQuery(name = "Parcela.findByValor", query = "SELECT p FROM Parcela p WHERE p.valor = :valor")
    , @NamedQuery(name = "Parcela.findByDatavencimento", query = "SELECT p FROM Parcela p WHERE p.datavencimento = :datavencimento")
    , @NamedQuery(name = "Parcela.findByDatapagamento", query = "SELECT p FROM Parcela p WHERE p.datapagamento = :datapagamento")
    , @NamedQuery(name = "Parcela.findByPaga", query = "SELECT p FROM Parcela p WHERE p.paga = :paga")
    , @NamedQuery(name = "Parcela.findByIdcontrato", query = "SELECT p FROM Parcela p WHERE p.parcelaPK.idcontrato = :idcontrato")})
public class Parcela implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParcelaPK parcelaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "datavencimento")
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;
    @Column(name = "paga")
    private Boolean paga;
    @JoinColumn(name = "idcontrato", referencedColumnName = "idcontrato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contrato contrato;

    public Parcela() {
    }

    public Parcela(ParcelaPK parcelaPK) {
        this.parcelaPK = parcelaPK;
    }

    public Parcela(int idparcela, int idcontrato) {
        this.parcelaPK = new ParcelaPK(idparcela, idcontrato);
    }

    public ParcelaPK getParcelaPK() {
        return parcelaPK;
    }

    public void setParcelaPK(ParcelaPK parcelaPK) {
        this.parcelaPK = parcelaPK;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Boolean getPaga() {
        return paga;
    }

    public void setPaga(Boolean paga) {
        this.paga = paga;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parcelaPK != null ? parcelaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcela)) {
            return false;
        }
        Parcela other = (Parcela) object;
        if ((this.parcelaPK == null && other.parcelaPK != null) || (this.parcelaPK != null && !this.parcelaPK.equals(other.parcelaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Parcela[ parcelaPK=" + parcelaPK + " ]";
    }
    
}
