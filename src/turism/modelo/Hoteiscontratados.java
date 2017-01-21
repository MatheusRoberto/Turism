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
@Table(name = "hoteiscontratados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoteiscontratados.findAll", query = "SELECT h FROM Hoteiscontratados h")
    , @NamedQuery(name = "Hoteiscontratados.findByHotelidHotel", query = "SELECT h FROM Hoteiscontratados h WHERE h.hoteiscontratadosPK.hotelidHotel = :hotelidHotel")
    , @NamedQuery(name = "Hoteiscontratados.findByViagemIdviagem", query = "SELECT h FROM Hoteiscontratados h WHERE h.hoteiscontratadosPK.viagemIdviagem = :viagemIdviagem")
    , @NamedQuery(name = "Hoteiscontratados.findByValor", query = "SELECT h FROM Hoteiscontratados h WHERE h.valor = :valor")})
public class Hoteiscontratados implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HoteiscontratadosPK hoteiscontratadosPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "hotel_idHotel", referencedColumnName = "idhotel", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hotel hotel;
    @JoinColumn(name = "viagem_idviagem", referencedColumnName = "idviagem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Viagem viagem;

    public Hoteiscontratados() {
    }

    public Hoteiscontratados(HoteiscontratadosPK hoteiscontratadosPK) {
        this.hoteiscontratadosPK = hoteiscontratadosPK;
    }

    public Hoteiscontratados(HoteiscontratadosPK hoteiscontratadosPK, double valor) {
        this.hoteiscontratadosPK = hoteiscontratadosPK;
        this.valor = valor;
    }

    public Hoteiscontratados(int hotelidHotel, int viagemIdviagem) {
        this.hoteiscontratadosPK = new HoteiscontratadosPK(hotelidHotel, viagemIdviagem);
    }

    public HoteiscontratadosPK getHoteiscontratadosPK() {
        return hoteiscontratadosPK;
    }

    public void setHoteiscontratadosPK(HoteiscontratadosPK hoteiscontratadosPK) {
        this.hoteiscontratadosPK = hoteiscontratadosPK;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
        hash += (hoteiscontratadosPK != null ? hoteiscontratadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoteiscontratados)) {
            return false;
        }
        Hoteiscontratados other = (Hoteiscontratados) object;
        if ((this.hoteiscontratadosPK == null && other.hoteiscontratadosPK != null) || (this.hoteiscontratadosPK != null && !this.hoteiscontratadosPK.equals(other.hoteiscontratadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Hoteiscontratados[ hoteiscontratadosPK=" + hoteiscontratadosPK + " ]";
    }
    
}
