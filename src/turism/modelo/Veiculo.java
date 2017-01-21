/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v")
    , @NamedQuery(name = "Veiculo.findByIdveiculo", query = "SELECT v FROM Veiculo v WHERE v.idveiculo = :idveiculo")
    , @NamedQuery(name = "Veiculo.findByNome", query = "SELECT v FROM Veiculo v WHERE v.nome = :nome")
    , @NamedQuery(name = "Veiculo.findByTipo", query = "SELECT v FROM Veiculo v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "Veiculo.findByLotacao", query = "SELECT v FROM Veiculo v WHERE v.lotacao = :lotacao")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idveiculo")
    private Integer idveiculo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "lotacao")
    private Integer lotacao;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa idempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculo")
    private List<Veiculoscontratados> veiculoscontratadosList;

    public Veiculo() {
    }

    public Veiculo(Integer idveiculo) {
        this.idveiculo = idveiculo;
    }

    public Integer getIdveiculo() {
        return idveiculo;
    }

    public void setIdveiculo(Integer idveiculo) {
        this.idveiculo = idveiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    @XmlTransient
    public List<Veiculoscontratados> getVeiculoscontratadosList() {
        return veiculoscontratadosList;
    }

    public void setVeiculoscontratadosList(List<Veiculoscontratados> veiculoscontratadosList) {
        this.veiculoscontratadosList = veiculoscontratadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idveiculo != null ? idveiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.idveiculo == null && other.idveiculo != null) || (this.idveiculo != null && !this.idveiculo.equals(other.idveiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "turism.modelo.Veiculo[ idveiculo=" + idveiculo + " ]";
    }
    
}
