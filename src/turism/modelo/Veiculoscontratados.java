/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "veiculoscontratados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculoscontratados.findAll", query = "SELECT v FROM Veiculoscontratados v")
    , @NamedQuery(name = "Veiculoscontratados.findByViagemIdviagem", query = "SELECT v FROM Veiculoscontratados v WHERE v.veiculoscontratadosPK.viagemIdviagem = :viagemIdviagem")
    , @NamedQuery(name = "Veiculoscontratados.findByVeiculoIdveiculo", query = "SELECT v FROM Veiculoscontratados v WHERE v.veiculoscontratadosPK.veiculoIdveiculo = :veiculoIdveiculo")
    , @NamedQuery(name = "Veiculoscontratados.findByValor", query = "SELECT v FROM Veiculoscontratados v WHERE v.valor = :valor")})
public class Veiculoscontratados implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeiculoscontratadosPK veiculoscontratadosPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "veiculo_idveiculo", referencedColumnName = "idveiculo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Veiculo veiculo;
    @JoinColumn(name = "viagem_idviagem", referencedColumnName = "idviagem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Viagem viagem;

    public Veiculoscontratados() {
    }

    public Veiculoscontratados(VeiculoscontratadosPK veiculoscontratadosPK) {
        this.veiculoscontratadosPK = veiculoscontratadosPK;
    }

    public Veiculoscontratados(VeiculoscontratadosPK veiculoscontratadosPK, double valor) {
        this.veiculoscontratadosPK = veiculoscontratadosPK;
        this.valor = valor;
    }

    public Veiculoscontratados(int viagemIdviagem, int veiculoIdveiculo) {
        this.veiculoscontratadosPK = new VeiculoscontratadosPK(viagemIdviagem, veiculoIdveiculo);
    }

    public VeiculoscontratadosPK getVeiculoscontratadosPK() {
        return veiculoscontratadosPK;
    }

    public void setVeiculoscontratadosPK(VeiculoscontratadosPK veiculoscontratadosPK) {
        this.veiculoscontratadosPK = veiculoscontratadosPK;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veiculoscontratadosPK != null ? veiculoscontratadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculoscontratados)) {
            return false;
        }
        Veiculoscontratados other = (Veiculoscontratados) object;
        if ((this.veiculoscontratadosPK == null && other.veiculoscontratadosPK != null) || (this.veiculoscontratadosPK != null && !this.veiculoscontratadosPK.equals(other.veiculoscontratadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Veiculoscontratados[ veiculoscontratadosPK=" + veiculoscontratadosPK + " ]";
    }
    
}
