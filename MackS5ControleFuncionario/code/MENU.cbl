       IDENTIFICATION       DIVISION.
       PROGRAM-ID.         	MENU.
       AUTHOR.		        DARIO.
       DATE-WRITTEN.  	    23/10/2017.
       ENVIRONMENT         	DIVISION.
       CONFIGURATION  	    SECTION.
       SPECIAL-NAMES.
	       DECIMAL-POINT    IS    COMMA.
       
       DATA    DIVISION.
       WORKING-STORAGE     SECTION.
       77   BARRA-DUPLA PIC X(74)   VALUE ALL "|".
       77	OPC		PIC	9(1)	VALUE   ZEROS.
       77	BRANCO	PIC      X(20)  VALUE  SPACES.
       01	DATA-SISTEMA.
            02        ANO-SIST     PIC   9999.
            02        MES-SIST     PIC   99.
            02        DIA-SIST       PIC   99.
       01       DATA-DIA.
            02        DIA          PIC  99/.   	
            02        MES         PIC  99/.   
            02        ANO          PIC  9999.

       

       PROCEDURE   DIVISION.
       INICIO.
           ACCEPT    DATA-SISTEMA   FROM   DATE   YYYYMMDD.
		   MOVE       ANO-SIST  TO   ANO.
		   MOVE       MES-SIST  TO   MES
           MOVE       DIA-SIST   TO   DIA
		   

	       PERFORM ROT-PROCESSA UNTIL OPC = 9.
	       DISPLAY "Programa encerrado." AT 2460.
	       STOP "  ".
	       STOP RUN.

       ROT-PROCESSA.
	       DISPLAY ERASE.
           DISPLAY BARRA-DUPLA AT 0110
           
           DISPLAY BARRA-DUPLA AT 3010
           DISPLAY "PROJETO DE TECNOLOGIA DE SISTEMAS LEGADOS" AT 0320.
	       DISPLAY "MENU"               AT 0530                       
	       DISPLAY  "DATA"  AT  0560
		   DISPLAY   DATA-DIA    AT  0566
           DISPLAY "(1)  Inclusao"      AT 1010.
	       DISPLAY "(2)  Leitura"       AT 1210.
	       DISPLAY "(3)  Atualizacao"   AT 1410.
	       DISPLAY "(4)  Exclusao"      AT 1610.
	       DISPLAY "(9)  Encerrar"      AT 1810. 
	       DISPLAY "Escolha a opcao:  " AT 2220.                      
                                                                        
	       PERFORM WITH TEST AFTER UNTIL ((OPC>=1)AND(OPC<=5))OR(OPC=9)
		     ACCEPT OPC AT 2240 WITH AUTO
		         IF ((OPC>=1) AND (OPC<=5)) OR (OPC=9)  
			        DISPLAY BRANCO AT 2250
		         ELSE
		  	        DISPLAY "Digite um n. entre 1 e 5 ou 9" AT 2250
		         END-IF
	         END-PERFORM.
		      
	       EVALUATE OPC
	          WHEN 1
	  		      CALL "INCLUSAO" 
	  	      WHEN 2
      		      CALL "LEITURA"
              WHEN 3
      		      CALL "ALTERACAO"
		      WHEN 4
      		      CALL "DELECAO"
	       END-EVALUATE.
       
       FIM-PROCESSA. EXIT.
