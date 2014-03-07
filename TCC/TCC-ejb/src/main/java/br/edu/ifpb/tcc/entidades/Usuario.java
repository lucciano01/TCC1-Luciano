/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 *
 * @author Luciano
 */
@Entity
@NamedQueries({
@NamedQuery(name="Usuario.findAll", query="select u from Usuario u"),
@NamedQuery(name="Usuario.byMatricula", query="select u from Usuario u where u.matricula=:matricula"),
@NamedQuery(name="Usuario.byNome", query="select u from Usuario u where u.nome=:nome"),
@NamedQuery(name="Autenticacao.Usuario", query="select u from Usuario u where u.login=:login and u.senha=:senha")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
     public enum Perfil{
        ADMINISTRADOR, PROFESSOR, COORDENADOR
    }
     
     public enum Vinculo{
         AA , BB , CC
     }
     
     public enum Regime{
         DD, EE, FF
     }
             
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique=true)
    private String matricula;
    @Enumerated(EnumType.STRING)
    private Vinculo vinculo;
    @Enumerated(EnumType.STRING)
    private Regime regime;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    @Column(unique=true)
    private String login;
    private String senha;
   
    @ManyToMany
    @JoinColumn
    private List<Papel> papeis;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<Telefone> telefones;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<Email> emails;
    
    public Usuario(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

        public Vinculo getVinculo() {
            return vinculo;
        }
    
        public void setVinculo(Vinculo vinculo) {
            this.vinculo = vinculo;
        }
    
        public Regime getRegime() {
            return regime;
        }
    
        public void setRegime(Regime regime) {
            this.regime = regime;
        }
   

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpb.tcc.entidades.Usuario[ id=" + id + " ]";
    }
    
}
