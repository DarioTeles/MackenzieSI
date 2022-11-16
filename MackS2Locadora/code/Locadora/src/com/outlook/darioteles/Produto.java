
package com.outlook.darioteles;

import java.util.ArrayList;
import java.io.*;

/**
 * Nome: Dário Teles de Oliveira    TIA: 41582391
 * 
 * Define um produto.
 */
public class Produto 
{
    private int codigo;
    private String nome;
    private String tipo;
    private int qtdeDisp;
    
    //Contrutor sem parâmetros
    public Produto(){}
    
    /**
     * Contrói um produto.
     * @param codigo
     * @param nome
     * @param tipo
     * @param qtdeDisp
     * @throws IOException 
     */
    public Produto(int codigo, String nome, String tipo, int qtdeDisp)throws IOException
    {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.qtdeDisp = qtdeDisp;
    }
    
    /**
     * Retorna o código do produto.
     * @return codigo
     */
    public int getCodigo() 
    {
        return codigo;
    }
    
    /**
     * Retorna o nome do produto.
     * @return nome
     */
    public String getNome() 
    {
        return nome;
    }
    
    /**
     * Retorna o tipo do produto.
     * @return tipo
     */
    public String getTipo() 
    {
        return tipo;
    }
    
    /**
     * Retorna a quatidade de itens diponíveis do produto.
     * @return quantidade disponíveis
     */
    public int getQtdeDisp() 
    {
        return qtdeDisp;
    }
    
    /**
     * Altera a quantidade de itens disponíveis do produto.
     * @param qtdeDisp 
     */
    public void setQtdeDisp(int qtdeDisp) 
    {
        this.qtdeDisp = qtdeDisp;
    }
}
