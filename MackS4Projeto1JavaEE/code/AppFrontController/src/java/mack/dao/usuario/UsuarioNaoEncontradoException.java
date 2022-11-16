package mack.dao.usuario;

import org.apache.commons.lang.exception.*;

/**
 * Define a exceção caso não encontre usuários.
 * 
 * O que é uma Exception?
 * Uma exceção em Java é um objeto da classe java.lang.Exception, ou de uma de 
 * suas subclasses. Como todo objeto, a exceção é capaz de armazenar dados nas 
 * suas variáveis de instância. Quando um erro ou situação anormal é encontrado 
 * durante a execução de um método, um objeto exceção é construído, e diversos 
 * dados sobre essa ocorrência são registrados nos campos desse objeto.
 * Nesse momento, o método onde ocorreu a exceção aborta, e o controle da 
 * execução retorna ao método que o chamou.
 */
public class UsuarioNaoEncontradoException extends NestableException {

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(Throwable t) {
        super(t);
    }

    public UsuarioNaoEncontradoException(String msg, Throwable t) {
        super(msg, t);
    }
}
