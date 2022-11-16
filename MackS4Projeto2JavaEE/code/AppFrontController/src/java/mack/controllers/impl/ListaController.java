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
 * busca (listagem) de usuários.
 */
public class ListaController extends AbstractController 
{
    public void execute() 
    {
        try 
        {
            Logger.getLogger(ListaController.class.getName()).log(Level.INFO, null, "Lista");
            Context ctx = new InitialContext();
            UsuarioBean ub = (UsuarioBean) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            List usuarios = new ArrayList<Usuario>();
            usuarios = (List) ub.list();
            this.setReturnPage("/listaUsuarios.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
        } catch (Exception ex) 
        {
            Logger.getLogger(ListaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
