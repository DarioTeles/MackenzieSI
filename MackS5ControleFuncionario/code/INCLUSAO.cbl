       IDENTIFICATION      DIVISION.
       PROGRAM-ID.    	   INCLUSAO INITIAL.
       AUTHOR.		       DARIO.
       DATE-WRITTEN.  	   23/10/2017.
       ENVIRONMENT 	       DIVISION.
       CONFIGURATION 	   SECTION.
       SPECIAL-NAMES.
           DECIMAL-POINT   IS   COMMA.
           
       INPUT-OUTPUT    SECTION.
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
                   02 VD	         PIC	ZZZ.ZZZ.ZZ9,99	VALUE ZEROS.
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
               DISPLAY BARRA-DUPLA            AT 2505.
               DISPLAY "Inclusao de Dados: "  AT 0310.
               DISPLAY "Codigo: "             AT 0710.
               DISPLAY "Nome: "               AT 0910.
               DISPLAY "CNPJ: "               AT 1110.
               DISPLAY "Vlr. Contrato:"       AT 1310.
               DISPLAY "CEP: "                AT 1510.
               
      *    Recebimento de Dados
       
          PERFORM WITH TEST AFTER UNTIL CodCliente>0 AND CodCliente<=999
              ACCEPT CodCliente AT 0745 WITH UPPER AUTO
              IF CodCliente<=0 OR CodCliente>1000
                  DISPLAY "Digite um n entre 001 e 999."  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.

       PERFORM WITH TEST AFTER UNTIL NomeCliente NOT=SPACES 
       AND LOW-VALUE                                                         
              ACCEPT NomeCliente AT 0945 WITH UPPER AUTO
              IF NomeCliente =SPACES OR NomeCliente=LOW-VALUE
                  DISPLAY "Digite o nome.              "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
       END-PERFORM.
          
          PERFORM WITH TEST AFTER UNTIL CNPJ>0      
              ACCEPT CNPJ AT 1145 WITH UPPER AUTO
              IF CNPJ<=0
                  DISPLAY "CNPJ invalido.              "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.
       
          PERFORM WITH TEST AFTER UNTIL VC>2000 AND VC<9999999999
              ACCEPT VD AT 1345 WITH UPPER AUTO
              
		      MOVE VD TO VC

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
              ACCEPT CEP AT 1545 WITH UPPER AUTO
              IF CEP<=0
                  DISPLAY "CEP invalido.               "  AT 2040
              ELSE
                  DISPLAY "                            "  AT 2040
              END-IF
          END-PERFORM.          
               
      *    Rotina de Gravação
               
               WRITE REG-CLIENTE.
               IF COD-ERRO NOT = "00" *> Podera ver se = “22”
        	       DISPLAY "Chave Duplicada" AT 2340
        	       STOP "   <Enter> para continuar         "
               END-IF.
      
      *    Recebimento Opccao  para voltar ou parar (repeticao)
           
               DISPLAY "Outro Registro?(S/N): " AT 2010
                
             PERFORM WITH TEST AFTER UNTIL OPCAO = "S" OR "N"
                   ACCEPT OPCAO AT 2035 WITH UPPER AUTO
                   IF OPCAO NOT = "S" AND "N"
                       DISPLAY "Digite S ou N..."  AT 2040
                   ELSE
                       DISPLAY "             "  AT 2040
                   END-IF
               END-PERFORM.