package mack.dao.usuario;

import mack.entities.Usuario;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.*;

/**
 * Define a implementação de um UsuarioDAO baseado em JPA.
 */
public class UsuarioDAOJPAImpl implements UsuarioDAO 
{
    static final private Log log = LogFactory.getLog(UsuarioDAOJPAImpl.class);
    private boolean bIsClosed = false;
    
    /**
     * Constrói uma instância para a implementação de um DAO baseado em JPA.
     */
    public UsuarioDAOJPAImpl() 
    {
    }
    
    /**
     * Insere um novo usuário ao banco de dados.
     */
    @Override
    public Usuario criaUsuario(final String nome, final String sobrenome) 
    {
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setSobrenome(sobrenome);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.clear();
        em.close();
        emf.close();
        return u;
    }
    
    /**
     * Consulta no banco dados todos os usuários. 
     */
    @Override
    public Collection buscaTodosUsuarios() 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u");
        Collection result = null;
        result = q.getResultList();
        em.clear();
        em.close();
        emf.close();
        return result;
    }
    
    /**
     * Consulta no banco de dados um usuário a partir do seu ID. 
     */
    @Override
    public Usuario buscaUsuarioPorId(final int id)
            throws UsuarioNaoEncontradoException 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Usuario u = em.find(Usuario.class, id);
        if (u==null) throw new UsuarioNaoEncontradoException("usuario não encontrado");
        em.clear();
        em.close();
        emf.close();
        return u;
    }
    
    /**
     * Consulta usuários no banco de dados a partir de um nome. 
     */
    @Override
    public Collection buscaUsuarioPorNome(final String nome) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.nome = :par1");
        q.setParameter("par1", nome);
        Collection result = null;
        result = q.getResultList();
        em.clear();
        em.close();
        emf.close();
        return result;
    }
    
    /**
     * Atualiza os dados de um usuário no banco de dados. 
     */
    @Override
    public void updateUsuario(final int id, final String nome,
            final String sobrenome) throws UsuarioNaoEncontradoException 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Usuario u = em.find(Usuario.class, id);
        if (u==null) throw new UsuarioNaoEncontradoException("usuario não encontrado");
        u.setNome(nome);
        u.setSobrenome(sobrenome);
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.clear();
        em.close();
        emf.close();
    }
    
    /**
     * Exclui um usuário no banco de dados a partir do seu ID. 
     */
    @Override
    public void removeUsuario(final int id)
            throws UsuarioNaoEncontradoException 
    {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Usuario u = em.find(Usuario.class, id);
        if (u==null) throw new UsuarioNaoEncontradoException("usuario não encontrado");
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
        em.clear();
        em.close();
        emf.close();
    }
    
    /**
     * Fecha a conexão com banco de dados.
     */
    @Override
    public void close() 
    {
        log.info("close() called");
        bIsClosed = true;
    }
    
    /**
     * Verifica a conexão com o banco de dados.
     */
    @Override
    public boolean isClosed() 
    {
        return bIsClosed;
    }
}
