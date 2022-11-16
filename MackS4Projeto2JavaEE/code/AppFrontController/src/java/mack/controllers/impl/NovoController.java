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
 * a inserção de um novo usuário.
 */
public class NovoController extends AbstractController 
{
    public void execute() 
    {
        try 
        {
            Logger.getLogger(NovoController.class.getName()).log(Level.INFO, null, "Novo");
            Usuario usuario;
            String nome = this.getRequest().getParameter("nome");
            String sobrenome = this.getRequest().getParameter("sobrenome");
            String login = this.getRequest().getParameter("login");
            String senha = this.getRequest().getParameter("senha");
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setLogin(login);
            usuario.setHash(ub.getHash(senha));
            usuario = ub.criaUsuario(usuario);
            this.setReturnPage("/mostraUsuario.jsp");
            this.getRequest().setAttribute("usuario", usuario);
        } catch (Exception ex) {
            Logger.getLogger(NovoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
