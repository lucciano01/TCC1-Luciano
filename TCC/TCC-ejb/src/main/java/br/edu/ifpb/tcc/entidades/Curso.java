/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Luciano
 */
@Entity
//@NamedQueries({
//@NamedQuery(name = "Curso.findALL" , query = "select c from curso"),
//@NamedQuery(name = "Curso.byName" , query = "select c from curso c where c.descricao=:name"),
//@NamedQuery(name = "Cursos.byArea" , query = "select c from curso c where c.area=:area"),
//@NamedQuery(name = "Cursos.bySubArea" , query = "select c from curso c where c.subarea=:subarea"),
//@NamedQuery(name = "Disciplinas.ByCurso" , query = "select d.descricao from (disciplina d join curso c on d.disciplinas_id=c.id)where c.descricao=:curso")
//})
public class Curso implements Serializable {
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
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Area area;
    @Enumerated(EnumType.STRING)
    private SubArea subArea;
    @OneToMany(cascade= CascadeType.MERGE)
    private List<Disciplina> disciplinas;
    @ManyToMany(cascade= CascadeType.MERGE, mappedBy = "cursos")
    private List<Professor> professores;
    
    public Curso(){
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

   
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
    
    
    

    public Long getId() {
        return id;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpb.tcc.entidades.Curso[ id=" + id + " ]";
    }
    
}
