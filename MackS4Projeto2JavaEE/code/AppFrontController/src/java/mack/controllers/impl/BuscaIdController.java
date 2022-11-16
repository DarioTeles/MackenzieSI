package mack.controllers.impl;

import ejb.beans.UsuarioBean;
import ejb.entities.Usuario;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;

/**
 * Define a implementação da Controller reponsável pelo fluxo da aplicação de 
 * busca de um determinado usuário a partir do seu ID.
 */
public class BuscaIdController extends AbstractController {

    public void execute() {
        try {
            Logger.getLogger(BuscaIdController.class.getName()).log(Level.INFO, null, "BuscaId");
            String id = this.getRequest().getParameter("id");
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            Usuario usuario;
            usuario = (Usuario) ub.buscaUsuarioPorId(Integer.parseInt(id));
            this.setReturnPage("/mostraUsuario.jsp");
            this.getRequest().setAttribute("usuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(BuscaIdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
