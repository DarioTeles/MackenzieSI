package com.outlook.darioteles;

import java.util.ArrayList;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * Nome: Dário Teles de Oliveira    TIA: 41582391
 * 
 * Define um locadora.
 */
public class Locadora 
{
    private String nome;
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private ArrayList<Alocacao> alocacoes = new ArrayList<Alocacao>();
    
    //Método contrutor sem parâmetros
    public Locadora(){}
    
    /**
     * Constrói uma locadora.
     * @param nome 
     */
    public Locadora(String nome)
    {
        this.nome = nome;
    }
    
    /**
     * Método que faz a leitura de produtos a partir do arquivo.
     * @param arquivoProdutos
     * @throws IOException 
     */
    public void carregarProdutos(String arquivoProdutos) throws IOException
    {
        FileReader arquivoProdutosFile = new FileReader(arquivoProdutos);
        BufferedReader entrada = new BufferedReader(arquivoProdutosFile); 
        String linha;
        
        do 
        {
            linha = entrada.readLine();
            if (linha != null)
            {
                String vetString[] = linha.split(":");
                
                this.produtos.add(new Produto (Integer.parseInt(vetString[0]), vetString[1], vetString[2], Integer.parseInt(vetString[3])));
                
            }
        } while(linha != null);
        arquivoProdutosFile.close();
    }
    
    /**
     * Método que faz a leitura de clientes a partir do arquivo.
     * @param arquivoClientes
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void carregarClientes(String arquivoClientes) throws FileNotFoundException, IOException
    {
        FileReader arquivoClientesFile = new FileReader(arquivoClientes);
        BufferedReader entrada = new BufferedReader(arquivoClientesFile); 
        String linha;
        
        do 
        {
            linha = entrada.readLine();
            if (linha != null)
            {
                String vetString[] = linha.split(":");
                this.clientes.add(new Cliente (Integer.parseInt(vetString[0]), vetString[1], Integer.parseInt(vetString[2]), vetString[3]));   
            }            
        } while(linha != null);
        arquivoClientesFile.close();
    }
    
    /**
     * Retorna a lista de produtos referente a locadora.
     * @return lista de produtos
     */
    public ArrayList<Produto> getProdutos()
    {
        return produtos;
    }
    
    /**
     * Retorna a lista de clientes referente a locadora.
     * @return lista de clientes
     */
    public ArrayList<Cliente> getClientes()
    {
        return clientes;
    }
    
    /**
     * Retorna a lista de alocações referente a locadora.
     * @return lsita de alocações
     */
    public ArrayList<Alocacao> getAlocacoes() 
    {
        return alocacoes;
    }
    
    /**
     * Método para alocar produto.
     */
    public void alocar()
    {
        Scanner entrada = new Scanner (System.in);
        
        System.out.println("Alocar Produto:");
        System.out.println();
        System.out.println("Cod" + "    " + "Nome" + "      " + "Tipo" + "    " + "Qtde Disponível");
        for (int i=0; i<produtos.size(); i++)
        {
            System.out.println(produtos.get(i).getCodigo() + "      " + produtos.get(i).getNome() + "    " + produtos.get(i).getTipo() + "      " + produtos.get(i).getQtdeDisp());
        }
        System.out.println();
                    
        Produto produtoRef = null;
        Cliente clienteRef = null;
        boolean val1 = false;
        boolean val2 = false;
                    
        do 
        {
            System.out.println("Por favor digite o código do produto: ");
            int codigo = entrada.nextInt();

            for (int i=0; i<produtos.size(); i++)
            {
                int indice = produtos.get(i).getCodigo();

                if(codigo == indice)
                {
                    if(produtos.get(i).getQtdeDisp() > 0)
                    {
                        produtoRef = produtos.get(i);
                        val1 = true;
                    }else
                    {
                        System.out.println("Produto indisponível.");
                    }
                }
            }
        } while(val1==false);
                    
        do 
        {
            System.out.println("Por favor digite o ID do Cliente: ");
            int idCliente = entrada.nextInt();
            
            for (int i=0; i<clientes.size(); i++)
            {
                int indice = clientes.get(i).getId();
                
                if(idCliente == indice)
                {
                    clienteRef = clientes.get(i);
                    val2 = true;
                }
            }
        } while(val2==false);
                    
        Date dataInicial = new Date();
        Date dataFinal = new Date();
        dataFinal.setDate(dataFinal.getDate() + 30);
        
        String confirmacao;
                    
        do
        {
            System.out.println("Informações da Alocação");
            System.out.println("Código do Produto:     " + produtoRef.getCodigo());
            System.out.println("Nome do Produto:       " + produtoRef.getNome());
            System.out.println("Tipo do Produto:       " + produtoRef.getTipo());
            System.out.println("Id do Cliente:         " + clienteRef.getId());
            System.out.println("Nome do Cliente:       " + clienteRef.getNome());
            System.out.println("Data de Alocação:      " + dataInicial);
            System.out.println("Data de Entrega:       " + dataFinal);
            System.out.println();
                       
            System.out.println("Você gostaria de confirmar a alocação?(s/n)");
            confirmacao = entrada.next();
        } while(!(confirmacao.equals("s")) && !(confirmacao.equals("n")));
                    
        if (confirmacao.equals("s"))
        {
            alocacoes.add(new Alocacao(clienteRef, produtoRef, dataInicial, dataFinal));
            produtoRef.setQtdeDisp(produtoRef.getQtdeDisp() - 1);
        } else 
        {
            System.out.println("Alocação não confirmada.");
        }
    }
    
    /**
     * Método para cadastrar produto.
     * @throws IOException 
     */
    public void cadastrarProduto() throws IOException{
        System.out.println("Cadastro de Produto.");
        Scanner entrada = new Scanner(System.in);
        File logProduto = new File(appParameters.FILE_PRODUTO);
        BufferedWriter writer = new BufferedWriter(new FileWriter(logProduto));
        String produto = entrada.nextLine();
        writer.write(produto);
        writer.newLine();
        writer.close();
    }   
}
