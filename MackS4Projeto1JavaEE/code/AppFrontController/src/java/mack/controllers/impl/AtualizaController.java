package mack.controllers.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;
import mack.dao.usuario.UsuarioDAO;
import mack.dao.usuario.UsuarioDAOFactory;
import mack.entities.Usuario;

/**
 * Define a implementação da Controller reponsável pelo fluxo da aplicação para  
 * atualização de usuários.
 */
public class AtualizaController extends AbstractController 
{
    public void execute() 
    {
        try 
        {
            Logger.getLogger(AtualizaController.class.getName()).log(Level.INFO, null, "Atualiza");
            Usuario usuario;
            String id = this.getRequest().getParameter("id");
            String nome = this.getRequest().getParameter("nome");
            String sobrenome = this.getRequest().getParameter("sobrenome");
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            dao.updateUsuario(Integer.parseInt(id), nome, sobrenome);
            usuario = dao.buscaUsuarioPorId(Integer.parseInt(id));
            this.setReturnPage("/mostraUsuario.jsp");
            this.getRequest().setAttribute("usuario", usuario);
        } catch (Exception ex) 
        {
            Logger.getLogger(AtualizaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
