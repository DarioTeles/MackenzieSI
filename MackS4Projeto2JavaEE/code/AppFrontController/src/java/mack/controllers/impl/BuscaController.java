package mack.controllers.impl;

import ejb.beans.UsuarioBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import ejb.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;

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
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            Usuario usuario;
            usuarios = (List) ub.buscaUsuarioPorNome(nome);
            this.setReturnPage("/listaUsuarios.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
        } catch (Exception ex) 
        {
            Logger.getLogger(BuscaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
