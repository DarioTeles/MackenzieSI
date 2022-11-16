package mack.dao.usuario;

/**
 * Define a Factory reponsável por encapsular a instância das classes que 
 * implementam a interface UsuarioDADO.
 */
public final class UsuarioDAOFactory 
{
    private UsuarioDAOFactory() 
    {
    }
    
    public static UsuarioDAO getUsuarioDAO() 
    {
        return new UsuarioDAOJPAImpl();
    }
}
