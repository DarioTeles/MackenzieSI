       IDENTIFICATION      DIVISION.
       PROGRAM-ID.    	   ALTERACAO INITIAL.
       AUTHOR.		       DARIO.
       DATE-WRITTEN.  	   29/10/2017.
       ENVIRONMENT 	       DIVISION.
       CONFIGURATION 	SECTION.
       SPECIAL-NAMES.
           DECIMAL-POINT   IS   COMMA.
           
       INPUT-OUTPUT     SECTION.
       FILE-CONTROL.
               SELECT  CAD-CLIENTE ASSIGN  TO "C:\TEMP\CADCLIENTE.DAT"
                       ORGANIZATION  INDEXED
                       RECORD  KEY  CodCliente
                       ACCESS     RANDOM
                       FILE  STATUS   COD-ERRO.
       
       DATA            DIVISION.
       FILE        SECTION.
       FD          CAD-CLIENTE
                   LABEL RECORD STANDARD.
       01          REG-CLIENTE.
                   02 CodCliente       PIC 9(3).
                   02 NomeCliente      PIC x(30).
                   02 CNPJ             PIC 9(14).
                   02 VC               PIC 9(10)V99.
                   02 CEP              PIC 9(8).
                   02 FILLER           PIC X(21).
                   
       WORKING-STORAGE SECTION.
       
       77      COD-ERRO    PIC XX      VALUES SPACES.
       77      BARRA-DUPLA PIC X(74)   VALUE ALL "|".
       77      OPCAO       PIC X       VALUE SPACE.
       
       SCREEN SECTION.
       01      TELA-COR.
                   02 BLANK SCREEN BACKGROUND-COLOR 1.
       
       PROCEDURE DIVISION.
       INICIO.
           OPEN I-O CAD-CLIENTE.
           
           PERFORM ROTINA-PROCESSAMENTO UNTIL OPCAO = "N".
               DISPLAY "Fim de processamento" AT 2450.
               CLOSE CAD-CLIENTE.
               GOBACK.
               
       ROTINA-PROCESSAMENTO.
               MOVE ZEROS TO CodCliente CNPJ VC CEP.
               MOVE SPACES TO NomeCliente.
               MOVE SPACES TO REG-CLIENTE.
               
      *    Formatação da Tela
      
               DISPLAY TELA-COR.
               DISPLAY BARRA-DUPLA            AT 0105.
               DISPLAY BARRA-DUPLA            AT 2605.
               DISPLAY "Alteracao de Dados: "  AT 0310.
               DISPLAY"Digite o codigo do reg. a ser alterado:" AT 0510. 
               DISPLAY "Codigo: "             AT 0910.
               DISPLAY "Nome: "               AT 1110.
               DISPLAY "CNPJ: "               AT 1310.
               DISPLAY "Vlr. Contrato:"       AT 1510.
               DISPLAY "CEP: "                AT 1710.
               
      *    Recebimento de Dados
       
          PERFORM WITH TEST AFTER UNTIL CodCliente>0 AND CodCliente<=999
              ACCEPT CodCliente AT 0945 WITH UPPER AUTO
              IF CodCliente<=0 OR CodCliente>1000
                  DISPLAY "Digite um n entre 001 e 999."  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.
      
      *    Rotina de Leitura
               READ  CAD-CLIENTE

               IF COD-ERRO NOT = "00"
     		        DISPLAY "Nao achou o registro." AT 2440
               END-IF.

      *    Apresentação dos Dados

               DISPLAY NomeCliente      AT 1145.
               DISPLAY CNPJ             AT 1345.
               DISPLAY VC               AT 1545.
               DISPLAY CEP              AT 1745. 

      *    Leitura de alterações
       PERFORM WITH TEST AFTER UNTIL NomeCliente NOT=SPACES 
       AND LOW-VALUE                                                         
              ACCEPT NomeCliente AT 1145 WITH UPPER AUTO
              IF NomeCliente =SPACES OR NomeCliente=LOW-VALUE
                  DISPLAY "Digite o nome.              "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
       END-PERFORM.
          
           PERFORM WITH TEST AFTER UNTIL CNPJ>0      
              ACCEPT CNPJ AT 1345 WITH UPPER AUTO
              IF CNPJ<=0
                  DISPLAY "CNPJ invalido.              "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
           END-PERFORM.

           PERFORM WITH TEST AFTER UNTIL VC>2000 AND VC<9999999999
              ACCEPT VC AT 1545 WITH UPPER AUTO
              IF VC<=2000 OR VC>=9999999999
                  IF VC<=2000
                    DISPLAY "Valor abaixo do permitido."  AT 2040
                  ELSE
                    DISPLAY "Valor acima do permitido."   AT 2040
                  END-IF
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.
       
          PERFORM WITH TEST AFTER UNTIL CEP>0      
              ACCEPT CEP AT 1745 WITH UPPER AUTO
              IF CEP<=0
                  DISPLAY "CEP invalido.               "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.

      *    Rotina de Alteração
               DISPLAY "Confirmar alteracao?(S/N): " AT 2010
                
               PERFORM WITH TEST AFTER UNTIL OPCAO = "S" OR "N"
                   ACCEPT OPCAO AT 2040 WITH UPPER AUTO
                   IF OPCAO NOT = "S" AND "N"
                       DISPLAY "Digite S ou N..."  AT 2040
                   ELSE
                       DISPLAY "             "  AT 2040
                       REWRITE REG-CLIENTE
                   END-IF
               END-PERFORM.

      *    Recebimento Opccao  para voltar ou parar (repeticao)
          
             DISPLAY "Outra Alteracao?(S/N): " AT 2310
                
             PERFORM WITH TEST AFTER UNTIL OPCAO = "S" OR "N"
                   ACCEPT OPCAO AT 2335 WITH UPPER AUTO
                   IF OPCAO NOT = "S" AND "N"
                       DISPLAY "Digite S ou N..."  AT 2340
                   ELSE
                       DISPLAY "             "  AT 2340
                   END-IF
             END-PERFORM.