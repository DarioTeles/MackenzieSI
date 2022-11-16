
package com.outlook.darioteles;

import java.util.ArrayList;
import java.io.*;

/**
 * Nome: Dário Teles de Oliveira    TIA: 41582391
 * 
 * Define um cliente.
 */
public class Cliente 
{
    private int id;
    private String nome;
    private int cpf;
    private String telefone;
    
    //Método construtor sem parâmetros
    public Cliente(){}
    
    /**
     * Contrói um cliente.
     * @param id
     * @param nome
     * @param cpf
     * @param telefone 
     */
    public Cliente(int id, String nome, int cpf, String telefone)
    {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    /**
     * Retorna o ID do cliente.
     * @return id
     */
    public int getId() 
    {
        return id;
    }
    
    /**
     * Retorna o nome do cliente.
     * @return nome
     */
    public String getNome() 
    {
        return nome;
    }
    
    /**
     * Retorna o CPF do cliente.
     * @return cpf
     */
    public int getCpf() 
    {
        return cpf;
    }
    
    /**
     * Retorna o telefone do cliente.
     * @return telefone
     */
    public String getTelefone() 
    {
        return telefone;
    }   
    
}
