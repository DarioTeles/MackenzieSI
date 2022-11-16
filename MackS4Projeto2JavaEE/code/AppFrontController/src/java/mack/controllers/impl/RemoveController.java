package mack.controllers.impl;

import ejb.beans.UsuarioBean;
import ejb.entities.Usuario;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;

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
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean)
            ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            ub.removeUsuario(Integer.parseInt(id));
            usuarios = (List) ub.list();
            this.setReturnPage("/listaUsuarios.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
        } catch (Exception ex) 
        {
            Logger.getLogger(RemoveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}