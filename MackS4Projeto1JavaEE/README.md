# MackS4Projeto1JavaEE

Esse é um projeto destinado a disciplina de Linguagem de Programação 3 do curso de Sistemas de Informação realizada no primeiro semestre de 2017. Ele consiste numa aplicação baseada em componentes corporativos Java2EE e define exemplos de fluxos de aplicação que demonstram os usos de tais componentes:

# Login

Nesse fluxo foi utilizado os componentes corporativos Filter e Session, responsáveis pela lógica de autenticação e criação de sessão respectivamente.

<p align="center">
	<img src="https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/images/demonstracao1.JPG"/>
</p>

<p align="center">
	<img src="https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/images/demonstracao2.JPG"/>
</p>

# AppFrontController

Esse fluxo define o CRUD da entidade usuário e nele foi utilizado o componente comporativo JPA com Hibernate, responsável pelo MOR (Mapeamento Objeto Relacional); Padrão Arquitetural MVC, Design Pattern Factory e especificação Servlet.

<p align="center">
	<img src="https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/images/demonstracao3.JPG"/>
</p>

<p align="center">
	<img src="https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/images/classdiagram_frontcontroller.jpg"/>
</p>

<p align="center">
	<img src="https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/images/classdiagram_daomodel.jpg"/>
</p>

# MeuPrimeiroBean

Exemplo de uso do compontente corporativo EJB responsável pela implementação da lógica de negócios da aplicação.


# Conteúdo do repositório

[**/documentacao/db/**](https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/db/) - Backup da base de dados utilizado no desenvolvimento do projeto.<br>
[**/documentacao/code/**](https://github.com/DarioTeles/MackS4Projeto1JavaEE/blob/master/code/) - Códigos do projeto para serem abertos na IDE Netbeans com o serviço Derby e Wildfly (JBoss) previamente instalados.<br>


# Recursos

[**NetBeans IDE 8.2**](https://github.com/apache/netbeans) - Software de Desenvolvimento.<br>
[**Apache Derby**](https://github.com/apache/derby) - Banco de Dados.<br>
[**Wildfly Application Server**](https://github.com/wildfly/wildfly) - Servidor de Aplicação.


# Pré-requisitos

Para realizar o deploy no Wildfly é necessário que ele esteja previamente instalado e configurado.
É necessário que o servidor Derby esteja iniciado para executar a aplicação.