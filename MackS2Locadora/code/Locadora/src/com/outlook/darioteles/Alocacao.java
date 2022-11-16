
package com.outlook.darioteles;

import java.util.ArrayList;
import java.io.*;
import java.util.Date;

/**
 * Nome: Dário Teles de Oliveira    TIA: 41582391
 * 
 * Define uma alocação.
 */
public class Alocacao 
{
    private Cliente cliente;
    private Produto produto;
    private Date dataAlocacao;
    private Date dataEntrega;
    private String estado;
    
    //Método construtor sem parametros
    public Alocacao(){}
    
    //Método construtor com parâmetros alterando o estado para "Em alocação"
    public Alocacao(Cliente cliente, Produto produto, Date dataAlocacao, Date dataEntrega)
    {
        this.cliente = cliente;
        this.produto = produto;
        this.dataAlocacao = dataAlocacao;
        this.dataEntrega = dataEntrega;
        this.estado  = "Em alocação";
    }
    
    //Método constutor com parâmetros 
    public Alocacao(Cliente cliente, Produto produto, Date dataAlocacao, Date dataEntrega, String estado) 
    {
        this.cliente = cliente;
        this.produto = produto;
        this.dataAlocacao = dataAlocacao;
        this.dataEntrega = dataEntrega;
        this.estado = estado;
    }
    
    /**
     * Altera o estado da alocação
     * @param estado 
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }
    
    /**
     * Altera a data de entrega da alocação
     * @param dataEntrega 
     */
    public void setDataEntrega(Date dataEntrega) 
    {
        this.dataEntrega = dataEntrega;
    }
    
    /**
     * Retorna o cliente relacionado a alocação.
     * @return cliente
     */
    public Cliente getCliente() 
    {
        return cliente;
    }
    
    /**
     * Retorna o produto relacionado a alocação.
     * @return produto
     */
    public Produto getProduto() 
    {
        return produto;
    }
    
    /**
     * Retorna a data de alocação.
     * @return dataAlocacao
     */
    public Date getDataAlocacao() 
    {
        return dataAlocacao;
    }
    
    /**
     * Retorna a data para a entrega do produto.
     * @return dataEntrega
     */
    public Date getDataEntrega() 
    {
        return dataEntrega;
    }
    
    /**
     * Retorna o estado da alocação.
     * @return estado
     */
    public String getEstado() 
    {
        return estado;
    }
}
