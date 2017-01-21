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
public class OcupantesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idocupantes")
    private int idocupantes;
    @Basic(optional = false)
    @Column(name = "contrato_idcontrato")
    private int contratoIdcontrato;
    @Basic(optional = false)
    @Column(name = "quarto_idquarto")
    private int quartoIdquarto;

    public OcupantesPK() {
    }

    public OcupantesPK(int idocupantes, int contratoIdcontrato, int quartoIdquarto) {
        this.idocupantes = idocupantes;
        this.contratoIdcontrato = contratoIdcontrato;
        this.quartoIdquarto = quartoIdquarto;
    }

    public int getIdocupantes() {
        return idocupantes;
    }

    public void setIdocupantes(int idocupantes) {
        this.idocupantes = idocupantes;
    }

    public int getContratoIdcontrato() {
        return contratoIdcontrato;
    }

    public void setContratoIdcontrato(int contratoIdcontrato) {
        this.contratoIdcontrato = contratoIdcontrato;
    }

    public int getQuartoIdquarto() {
        return quartoIdquarto;
    }

    public void setQuartoIdquarto(int quartoIdquarto) {
        this.quartoIdquarto = quartoIdquarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idocupantes;
        hash += (int) contratoIdcontrato;
        hash += (int) quartoIdquarto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OcupantesPK)) {
            return false;
        }
        OcupantesPK other = (OcupantesPK) object;
        if (this.idocupantes != other.idocupantes) {
            return false;
        }
        if (this.contratoIdcontrato != other.contratoIdcontrato) {
            return false;
        }
        if (this.quartoIdquarto != other.quartoIdquarto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.OcupantesPK[ idocupantes=" + idocupantes + ", contratoIdcontrato=" + contratoIdcontrato + ", quartoIdquarto=" + quartoIdquarto + " ]";
    }
    
}
