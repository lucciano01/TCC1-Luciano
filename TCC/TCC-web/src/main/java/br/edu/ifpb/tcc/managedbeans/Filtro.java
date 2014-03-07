/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tcc.managedbeans;

import br.edu.ifpb.tcc.entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luciano
 */
public class Filtro implements PhaseListener {
    
    
    @Override
    public void beforePhase(PhaseEvent event) {

    }


    @Override
    public void afterPhase(PhaseEvent event) {
        
        FacesContext context = event.getFacesContext();
        
        String pageId = context.getViewRoot().getViewId();
        
        boolean isLoginPage = (pageId.lastIndexOf("index.xhtml") > -1);
        
        Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
       
        if (usuarioLogado == null && !isLoginPage) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Filtro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
