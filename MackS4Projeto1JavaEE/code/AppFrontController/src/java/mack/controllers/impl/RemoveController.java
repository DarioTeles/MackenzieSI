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
 * Define a implementação da Controller reponsável pelo fluxo da aplicação para 
 * a exclusão de um determindo usuário a partir do seu ID.
 */
public class RemoveController extends AbstractController 
{
    public void execute() 
    {
        try 
        {
            Logger.getLogger(RemoveController.class.getName()).log(Level.INFO, null, "Lista");
            String id = this.getRequest().getParameter("id");
            List usuarios = new ArrayList<Usuario>();
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            dao.removeUsuario(Integer.parseInt(id));
            usuarios = (List) dao.buscaTodosUsuarios();
            this.setReturnPage("/listaUsuarios.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
        } catch (Exception ex) 
        {
            Logger.getLogger(RemoveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
