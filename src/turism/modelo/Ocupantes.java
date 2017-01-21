/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
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
@Table(name = "ocupantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupantes.findAll", query = "SELECT o FROM Ocupantes o")
    , @NamedQuery(name = "Ocupantes.findByIdocupantes", query = "SELECT o FROM Ocupantes o WHERE o.ocupantesPK.idocupantes = :idocupantes")
    , @NamedQuery(name = "Ocupantes.findByContratoIdcontrato", query = "SELECT o FROM Ocupantes o WHERE o.ocupantesPK.contratoIdcontrato = :contratoIdcontrato")
    , @NamedQuery(name = "Ocupantes.findByQuartoIdquarto", query = "SELECT o FROM Ocupantes o WHERE o.ocupantesPK.quartoIdquarto = :quartoIdquarto")
    , @NamedQuery(name = "Ocupantes.findByNome", query = "SELECT o FROM Ocupantes o WHERE o.nome = :nome")})
public class Ocupantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OcupantesPK ocupantesPK;
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "quarto_idquarto", referencedColumnName = "idquarto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Quarto quarto;

    public Ocupantes() {
    }

    public Ocupantes(OcupantesPK ocupantesPK) {
        this.ocupantesPK = ocupantesPK;
    }

    public Ocupantes(int idocupantes, int contratoIdcontrato, int quartoIdquarto) {
        this.ocupantesPK = new OcupantesPK(idocupantes, contratoIdcontrato, quartoIdquarto);
    }

    public OcupantesPK getOcupantesPK() {
        return ocupantesPK;
    }

    public void setOcupantesPK(OcupantesPK ocupantesPK) {
        this.ocupantesPK = ocupantesPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocupantesPK != null ? ocupantesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupantes)) {
            return false;
        }
        Ocupantes other = (Ocupantes) object;
        if ((this.ocupantesPK == null && other.ocupantesPK != null) || (this.ocupantesPK != null && !this.ocupantesPK.equals(other.ocupantesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Ocupantes[ ocupantesPK=" + ocupantesPK + " ]";
    }
    
}
