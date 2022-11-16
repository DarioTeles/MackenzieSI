
package com.outlook.darioteles;

import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

/**
 * Nome: Dário Teles de Oliveira    TIA: 41582391
 * 
 * Define a classe que interage com o usuário.
 */
public class LocadoraApp 
{
    public static void main (String[] args) throws IOException
    {
        int op, d, e;
         
        Locadora locadora1 = new Locadora();
        locadora1.carregarProdutos(appParameters.FILE_PRODUTO);
        locadora1.carregarClientes(appParameters.FILE_CLIENTE);
        Scanner entrada = new Scanner (System.in);
         
        System.out.println("Bem-vindo ao Sistema de Alocação.");
        do
        {    
            System.out.println("");
            System.out.println("O que deseja fazer?");
            System.out.println("");
            System.out.println("1. Cadastrar Cliente;");
            System.out.println("2. Alocar Produto;");
            System.out.println("3. Finalizar Alocação;");
            System.out.println("4. Renovar Alocação;");
            System.out.println("5. Visualizar Alocação;");
            System.out.println("6. Gerenciar Produtos;");
            System.out.println("0. Sair.");
            System.out.println("");
            System.out.println("Opção:");
            
            op = entrada.nextInt();
            System.out.println();
         
            switch (op) 
            {
                case 1:
                    System.out.println("Cadastrar Cliente:");
                    System.out.println();
                    System.out.println("Por favor digite o id: ");
                    int id = entrada.nextInt();
                    System.out.println("Por favor digite o nome: ");
                    String nome = entrada.next();
                    System.out.println("Por favor digite o CPF: ");
                    int cpf = entrada.nextInt();
                    System.out.println("Por favor digite o número do telefone: ");
                    String telefone = entrada.next();
                    //adicionado verificação se o cliente já existe na hora do cadastro.
                    boolean cad = true;
                    for (Cliente cliente : locadora1.getClientes()) 
                    {
                        if(cliente.getCpf() == cpf)
                        {
                            cad = false;
                            System.out.println("Cliente já cadastrado.");
                            break;
                        }
                    }
                    if(cad)
                    {
                        locadora1.getClientes().add(new Cliente(id, nome, cpf, telefone));
                    }
                    
                break;
                case 2:
                    locadora1.alocar();
                break;
                case 3:
                    //implementado a parte de finalizar uma Alocacao;.
                    System.out.println("Finalizar Alocação.");
                    List<Alocacao> alocacoes = locadora1.getAlocacoes();
                    int count = 0;
                    for (Alocacao alocacao : alocacoes) 
                    {
                        count++;
                        System.out.println(count+" - "+alocacao.getCliente().getNome()+ "  "+alocacao.getProduto().getNome()+"  "+ alocacao.getDataAlocacao()+ "  " + alocacao.getDataEntrega()+"  "+alocacao.getEstado());
                    }
                    System.out.println("Digite o número da alocacao que deseja finalizar: ");
                    int aloc = entrada.nextInt();
                    Alocacao fim = alocacoes.get(aloc-1);
                    if(fim.getEstado().equals("Finalizado."))
                    {
                        System.out.println("Alocacao já finalizada.");
                    }else{
                        fim.setEstado("Finalizado.");
                    }
                    
                break;
                case 4:
                    System.out.println("Renovar Alocação:");
                    
                    List<Alocacao> alocacoes2 = locadora1.getAlocacoes();
                    int count2 = 0;
                    for (Alocacao alocacoe : alocacoes2) 
                    {
                        count2++;
                        System.out.println(count2+" - "+alocacoe.getCliente().getNome()+ "  "+alocacoe.getProduto().getNome()+"  "+ alocacoe.getDataAlocacao()+ "  " + alocacoe.getDataEntrega()+"  "+alocacoe.getEstado());
                    }
                    System.out.println("Digite o numero da alocacao que deseja finalizar: ");
                    int aloc2 = entrada.nextInt();
                    Alocacao fim2 = alocacoes2.get(aloc2-1);
                    
                    if (fim2.getEstado().equals("Em alocação"))
                    {
                        System.out.println("Alocação renovada por mais 10 dias.");
                        System.out.println("Data de entrega antiga: " + alocacoes2.get(aloc2-1).getDataEntrega());
                        alocacoes2.get(aloc2-1).getDataEntrega().setDate(alocacoes2.get(aloc2-1).getDataEntrega().getDate() + 10);
                        System.out.println("Data de entrega nova: " + alocacoes2.get(aloc2-1).getDataEntrega());
                    } else
                    {
                        System.out.print("Alocação não está ativa.");
                    }
                    
                break;
                case 5:
                    System.out.println("Visualizar Alocações:");
                    System.out.println();
                    for (int i=0; i<locadora1.getAlocacoes().size(); i++)
                    {
                        
                        System.out.println("Cliente: " + locadora1.getAlocacoes().get(i).getCliente().getNome());
                        System.out.println("Produto: " + locadora1.getAlocacoes().get(i).getProduto().getNome());
                        System.out.println("Data de Alocação: " + locadora1.getAlocacoes().get(i).getDataAlocacao());
                        System.out.println("Data de Entrega: " + locadora1.getAlocacoes().get(i).getDataEntrega());
                        System.out.println("Estado da Alocação: " + locadora1.getAlocacoes().get(i).getEstado());
                        System.out.println();
                        System.out.println();
                    }   
                    
                break;
                case 6:
                    System.out.println("Gerenciar Produtos.");
                    System.out.println("Em manutenção.");
                    //Implementado o cadastro de produtos.
                    //locadora1.cadastrarProduto();
                    //Podemos implementar as outras partes, mas acho que o cadastro era o mais essencial
                break;
                case 0:
                    System.out.println("Fechando programa.");
                    System.exit(0);
                break;
            }
            
        } while (op != 0);
    }
}
