/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Luciano
 */
@Entity
//@NamedQueries({
//@NamedQuery(name = "Disciplina.getByName" , query = "select d from disciplina d where d.descricao=:name"),
//@NamedQuery(name = "Disciplinas.getByArea" , query = "select d from disciplina d where d.area=:area"),
//@NamedQuery(name = "Disicplinas.getBySubArea" , query = "select d from disciplina d where d.subarea=:subarea")
//})
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Area{
        INDUSTRIA, INFORMATICA, CONSTRUCAO_CIVIL
    }
    
    public enum SubArea{
        SUPERIOR, MEDIO_INTEGRADO, MEDIO_SUBSEQUENTE
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Area area;
    @Enumerated(EnumType.STRING)
    private SubArea subArea;
    private String descricao;
    private Integer cargaHoraria;
    @ManyToOne
    private Curso curso;
    
   
    
    public Disciplina(){
        
    }

    public Long getId() {
        return id;
    }

      
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpb.tcc.entidades.Disciplina[ id=" + id + " ]";
    }
    
}
