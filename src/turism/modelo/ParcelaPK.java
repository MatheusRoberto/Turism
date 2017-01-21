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
public class ParcelaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idparcela")
    private int idparcela;
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private int idcontrato;

    public ParcelaPK() {
    }

    public ParcelaPK(int idparcela, int idcontrato) {
        this.idparcela = idparcela;
        this.idcontrato = idcontrato;
    }

    public int getIdparcela() {
        return idparcela;
    }

    public void setIdparcela(int idparcela) {
        this.idparcela = idparcela;
    }

    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idparcela;
        hash += (int) idcontrato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParcelaPK)) {
            return false;
        }
        ParcelaPK other = (ParcelaPK) object;
        if (this.idparcela != other.idparcela) {
            return false;
        }
        if (this.idcontrato != other.idcontrato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.ParcelaPK[ idparcela=" + idparcela + ", idcontrato=" + idcontrato + " ]";
    }
    
}
