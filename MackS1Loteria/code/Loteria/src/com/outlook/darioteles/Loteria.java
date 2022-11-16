package com.outlook.darioteles;

import java.util.*;

public class Loteria 
{ 
  
  public static void main(String[] args) 
  { 
    Scanner entrada = new Scanner(System.in);  
    
    int qtdeNumeros, validador;
    validador = 1;
    
    //Leitura e validacao da quantidade de numeros de aposta
    do 
    {
      System.out.print("Digite a quantidade de numeros da aposta: ");
      qtdeNumeros = entrada.nextInt();
    } while(qtdeNumeros<6 || qtdeNumeros>15);
    
    //Imprime valor da aposta e dados de probabilidade
    switch (qtdeNumeros) 
    {
      case 6:
        System.out.print("Valor da aposta: R$ 3,50" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 50.063.860" + "\n");
        System.out.print("Quina: 154.518" + "\n");
        System.out.print("Quadra: 2.332" + "\n");
        break;
     case 7:
        System.out.print("Valor da aposta: R$ 24,50" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 7.151.980" + "\n");
        System.out.print("Quina: 44.981" + "\n");
        System.out.print("Quadra: 1.038" + "\n");
        break;
     case 8:
        System.out.print("Valor da aposta: R$ 98,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 1.787.995" + "\n");
        System.out.print("Quina: 17.192" + "\n");
        System.out.print("Quadra: 539" + "\n");
        break;
     case 9:
        System.out.print("Valor da aposta: R$ 294,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 595.998" + "\n");
        System.out.print("Quina: 7.791" + "\n");
        System.out.print("Quadra: 312" + "\n");
        break;
     case 10:
        System.out.print("Valor da aposta: R$ 735,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 238.399" + "\n");
        System.out.print("Quina: 3.973" + "\n");
        System.out.print("Quadra: 195" + "\n");
        break;
     case 11:
        System.out.print("Valor da aposta: R$ 1.617,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 108.363" + "\n");
        System.out.print("Quina: 2.211" + "\n");
        System.out.print("Quadra: 129" + "\n");
        break;
     case 12:
        System.out.print("Valor da aposta: R$ 3.284,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 54.182" + "\n");
        System.out.print("Quina: 1.317" + "\n");
        System.out.print("Quadra: 90" + "\n");
        break;
     case 13:
        System.out.print("Valor da aposta: R$ 6.006,00" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 29.175" + "\n");
        System.out.print("Quina: 828" + "\n");
        System.out.print("Quadra: 65" + "\n");
        break;
      case 14:
        System.out.print("Valor da aposta: R$ 10.510,50" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 16.671" + "\n");
        System.out.print("Quina: 544" + "\n");
        System.out.print("Quadra: 48" + "\n");
        break;
      case 15:
        System.out.print("Valor da aposta: R$ 17.517,50" + "\n");   
        System.out.print("Probabilidade de acerto(1 em):" + "\n");
        System.out.print("Sena: 10.003" + "\n");
        System.out.print("Quina: 370" + "\n");
        System.out.print("Quadra: 37" + "\n");
        break;
    }
    
    // Declaracao e alocacao de espaco para vetor
    int[] numero = new int[qtdeNumeros]; 
    
    // Imprime a data e hora da emissao do processo
    System.out.print(new Date());
    System.out.print ("\n");
    
    //Instancia um objeto da classe Random usando o construtor basico 
    Random gerador = new Random(); 
  
    //Imprime sequencia de n numeros inteiros aleatorios entre 1 e 60 
    for (int i = 0; i < qtdeNumeros; i++) 
    {
      do
      {   
        numero[i] = (gerador.nextInt(60))+ 1;
        validador = 1;
        for (int j = 0; j < i; j++) 
        {  
          if (numero[i] == numero[j])
            validador = 0;
        }    
      }while (validador == 0);

      System.out.print(numero[i] + " ");
    } 
  } 
}