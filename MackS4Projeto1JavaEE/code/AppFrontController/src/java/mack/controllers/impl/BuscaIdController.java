package mack.controllers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;
import mack.dao.usuario.UsuarioDAO;
import mack.dao.usuario.UsuarioDAOFactory;
import mack.entities.Usuario;

/**
 * Define a implementação da Controller reponsável pelo fluxo da aplicação de 
 * busca de um determinado usuário a partir do seu ID.
 */
public class BuscaIdController extends AbstractController {

    public void execute() {
        try {
            Logger.getLogger(BuscaIdController.class.getName()).log(Level.INFO, null, "BuscaId");
            Usuario usuario;
            String id = this.getRequest().getParameter("id");
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            usuario = (Usuario) dao.buscaUsuarioPorId(Integer.parseInt(id));
            this.setReturnPage("/mostraUsuario.jsp");
            this.getRequest().setAttribute("usuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(BuscaIdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
