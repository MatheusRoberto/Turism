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
public class AssentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idassento")
    private int idassento;
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private int idcontrato;
    @Basic(optional = false)
    @Column(name = "idveiculo")
    private int idveiculo;

    public AssentoPK() {
    }

    public AssentoPK(int idassento, int idcontrato, int idveiculo) {
        this.idassento = idassento;
        this.idcontrato = idcontrato;
        this.idveiculo = idveiculo;
    }

    public int getIdassento() {
        return idassento;
    }

    public void setIdassento(int idassento) {
        this.idassento = idassento;
    }

    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public int getIdveiculo() {
        return idveiculo;
    }

    public void setIdveiculo(int idveiculo) {
        this.idveiculo = idveiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idassento;
        hash += (int) idcontrato;
        hash += (int) idveiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssentoPK)) {
            return false;
        }
        AssentoPK other = (AssentoPK) object;
        if (this.idassento != other.idassento) {
            return false;
        }
        if (this.idcontrato != other.idcontrato) {
            return false;
        }
        if (this.idveiculo != other.idveiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.AssentoPK[ idassento=" + idassento + ", idcontrato=" + idcontrato + ", idveiculo=" + idveiculo + " ]";
    }
    
}
