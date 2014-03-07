/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.sessionbeans;

import br.edu.ifpb.tcc.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Luciano
 */
@Stateless
@LocalBean
public class UsuarioSB {

    @PersistenceContext(unitName="TCC-PU")
    EntityManager entityManager;
    
    public UsuarioSB(){
        
    }
    
    public void persist(Usuario u){
        entityManager.persist(u);
    }
    public Usuario getUsuario(Long id){
        return entityManager.find(Usuario.class, id);
    }
    public void remove(Long id){
        Usuario u = entityManager.find(Usuario.class, id);
        entityManager.remove(u);
    }
    public void merge(Usuario u){
        entityManager.merge(u);
    }
    public List<Usuario> getUsuarios(){
        Query query = entityManager.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }
    public Usuario getUsuarioByMatricula(String matricula){
        Query query = entityManager.createNamedQuery("Usuario.byMatricula");
        return (Usuario) query.getSingleResult();
    }
    public List<Usuario> getUsuarioByName(String nome){
        Query query = entityManager.createNamedQuery("Usuario.byNome");
        return (List<Usuario>) query.getSingleResult();
    }
    
    public Usuario autenticarUsuario(String login, String senha){
        Query query = entityManager.createQuery("select u from Usuario u where u.login=:login and u.senha=:senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        return (Usuario)query.getSingleResult();
    }

}
