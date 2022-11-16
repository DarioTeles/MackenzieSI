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
 * busca (listagem) de usuários a partir de um nome.
 */
public class BuscaController extends AbstractController 
{
    public void execute() 
    {
        try 
        {
            List usuarios = new ArrayList<Usuario>();
            String nome = this.getRequest().getParameter("nome");
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            usuarios = (List) dao.buscaUsuarioPorNome(nome);
            this.setReturnPage("/listaUsuarios.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
        } catch (Exception ex) 
        {
            Logger.getLogger(BuscaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
