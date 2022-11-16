package mack.controllers.impl;

import ejb.beans.UsuarioBean;
import ejb.entities.Usuario;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;

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
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            Usuario usuario;
            String id = this.getRequest().getParameter("id");
            String nome = this.getRequest().getParameter("nome");
            String sobrenome = this.getRequest().getParameter("sobrenome");
            String login = this.getRequest().getParameter("login");
            usuario = new Usuario();
            usuario.setId(Integer.parseInt(id));
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setLogin(login);
            ub.updateUsuario((Usuario) usuario);
            this.setReturnPage("/mostraUsuario.jsp");
            this.getRequest().setAttribute("usuario", usuario);
        } catch (Exception ex) 
        {
            Logger.getLogger(AtualizaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
