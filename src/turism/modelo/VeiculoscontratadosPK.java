/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author matheus
 */
@Embeddable
public class VeiculoscontratadosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "viagem_idviagem")
    private int viagemIdviagem;
    @Basic(optional = false)
    @Column(name = "veiculo_idveiculo")
    private int veiculoIdveiculo;

    public VeiculoscontratadosPK() {
    }

    public VeiculoscontratadosPK(int viagemIdviagem, int veiculoIdveiculo) {
        this.viagemIdviagem = viagemIdviagem;
        this.veiculoIdveiculo = veiculoIdveiculo;
    }

    public int getViagemIdviagem() {
        return viagemIdviagem;
    }

    public void setViagemIdviagem(int viagemIdviagem) {
        this.viagemIdviagem = viagemIdviagem;
    }

    public int getVeiculoIdveiculo() {
        return veiculoIdveiculo;
    }

    public void setVeiculoIdveiculo(int veiculoIdveiculo) {
        this.veiculoIdveiculo = veiculoIdveiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) viagemIdviagem;
        hash += (int) veiculoIdveiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeiculoscontratadosPK)) {
            return false;
        }
        VeiculoscontratadosPK other = (VeiculoscontratadosPK) object;
        if (this.viagemIdviagem != other.viagemIdviagem) {
            return false;
        }
        return this.veiculoIdveiculo == other.veiculoIdveiculo;
    }

    @Override
    public String toString() {
        return "turism.modelo.VeiculoscontratadosPK[ viagemIdviagem=" + viagemIdviagem + ", veiculoIdveiculo=" + veiculoIdveiculo + " ]";
    }
    
}
