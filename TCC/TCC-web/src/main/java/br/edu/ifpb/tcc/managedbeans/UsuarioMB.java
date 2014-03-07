/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.managedbeans;

import br.edu.ifpb.tcc.entidades.Email;
import br.edu.ifpb.tcc.entidades.Telefone;
import br.edu.ifpb.tcc.entidades.Usuario;
import br.edu.ifpb.tcc.entidades.Usuario.Perfil;
import br.edu.ifpb.tcc.entidades.Usuario.Regime;
import br.edu.ifpb.tcc.entidades.Usuario.Vinculo;
import br.edu.ifpb.tcc.sessionbeans.UsuarioSB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Luciano
 */
@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Telefone> telefones = new ArrayList<Telefone>();
    private Telefone telefone1 = new Telefone();
    private Telefone telefone2 = new Telefone();
    private List<Email> emails = new ArrayList<Email>();
    private Email email1 = new Email();
    private Email email2 = new Email();
    private String em1;
    private String em2;
    private String matricula;
    private Perfil perfil;
    private Regime regime;
    private Vinculo vinculo;
    private String tel1;
    private String tel2;
    private DataModel<Usuario> modelUsuarios = null ;

    
    @EJB
    private UsuarioSB usuarioSB;

    public UsuarioMB() {
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getEm1() {
        return em1;
    }

    public void setEm1(String em1) {
        this.em1 = em1;
    }

    public String getEm2() {
        return em2;
    }

    public void setEm2(String em2) {
        this.em2 = em2;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public DataModel<Usuario> getModelUsuarios() {
        if (modelUsuarios == null){
            List<Usuario> usuariosTemp = usuarioSB.getUsuarios();
            this.modelUsuarios = new ListDataModel<Usuario>(usuariosTemp);
        }
        
       return this.modelUsuarios;
    }

    public void setModelUsuarios(DataModel<Usuario> modelUsuarios) {
           this.modelUsuarios = modelUsuarios;
    }

    public void prepareEdicao() {
        usuario =  (Usuario)modelUsuarios.getRowData();
        
    }
    public void prepareSalvar(ActionEvent event){
        usuario = new Usuario();
    }

    public String autenticarUsuario() {
        String page = "index.xhtml";
        Usuario u = (usuarioSB.autenticarUsuario(usuario.getLogin(), usuario.getSenha()));
        if(u.getNome().toString().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acesso Negado!", "Usuário e/ou senha inválido(s)"));  
        }else{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("usuarioLogado", u);
            StringBuilder builder = new StringBuilder(u.getPerfil().toString().toLowerCase());
            page = builder.append("?faces-redirect=true").toString();
        }
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", u);
        return page;

    }

    public String encerrarSessao() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("usuarioLogado");
        request.getSession().invalidate();
        usuario = new Usuario();
        return "index.xhtml";
    }

    public void salvar() {
        telefone1.setNumero(tel1);
        telefone2.setNumero(tel2);
        telefones.add(telefone1);
        telefones.add(telefone2);
        usuario.setTelefones(telefones);
        email1.setEmail(em1);
        email2.setEmail(em2);
        emails.add(email1);
        emails.add(email2);
        usuario.setEmails(emails);
        usuarioSB.persist(usuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Operação Realizada com sucesso","Usuário cadastrado!"));  
    }

    public void resetUsuario() {
        usuario = null;
    }

    public void resetDataModelUsuario() {
        modelUsuarios = null;
    }

    public void update() {
        usuarioSB.merge(usuario);
        resetUsuario();
        resetDataModelUsuario();
    }

    public void remove() {
        usuarioSB.remove(usuario.getId());
        resetUsuario();
        resetDataModelUsuario();
    }

    public Usuario getUsuarioByMatricula() {
        return usuarioSB.getUsuarioByMatricula(matricula);
    }

}
