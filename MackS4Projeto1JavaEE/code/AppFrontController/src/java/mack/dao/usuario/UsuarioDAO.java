package mack.dao.usuario;

import mack.entities.Usuario;
import java.util.Collection;

/**
 * Define a interface para a implementação de um UsuarioDAO.
 * 
 * O que é DAO?
 * Data Access Object é um padrão utilizado para abstrair e encapsular todos os 
 * acessos ao data source. O DAO gerencia a conexão com o data source para obter
 * e armazenar informações.
 */
public interface UsuarioDAO 
{  
    public Usuario criaUsuario(String nome, String sobrenome);
    public Collection buscaTodosUsuarios();
    public Usuario buscaUsuarioPorId(int id) throws UsuarioNaoEncontradoException;
    public Collection buscaUsuarioPorNome(String nome);
    public void updateUsuario(int id, String nome, String sobrenome) throws UsuarioNaoEncontradoException;
    public void removeUsuario(int id) throws UsuarioNaoEncontradoException;
    public void close();
    public boolean isClosed();
}
