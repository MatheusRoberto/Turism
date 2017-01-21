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
public class HoteiscontratadosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "hotel_idHotel")
    private int hotelidHotel;
    @Basic(optional = false)
    @Column(name = "viagem_idviagem")
    private int viagemIdviagem;

    public HoteiscontratadosPK() {
    }

    public HoteiscontratadosPK(int hotelidHotel, int viagemIdviagem) {
        this.hotelidHotel = hotelidHotel;
        this.viagemIdviagem = viagemIdviagem;
    }

    public int getHotelidHotel() {
        return hotelidHotel;
    }

    public void setHotelidHotel(int hotelidHotel) {
        this.hotelidHotel = hotelidHotel;
    }

    public int getViagemIdviagem() {
        return viagemIdviagem;
    }

    public void setViagemIdviagem(int viagemIdviagem) {
        this.viagemIdviagem = viagemIdviagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) hotelidHotel;
        hash += (int) viagemIdviagem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoteiscontratadosPK)) {
            return false;
        }
        HoteiscontratadosPK other = (HoteiscontratadosPK) object;
        if (this.hotelidHotel != other.hotelidHotel) {
            return false;
        }
        if (this.viagemIdviagem != other.viagemIdviagem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.HoteiscontratadosPK[ hotelidHotel=" + hotelidHotel + ", viagemIdviagem=" + viagemIdviagem + " ]";
    }
    
}
