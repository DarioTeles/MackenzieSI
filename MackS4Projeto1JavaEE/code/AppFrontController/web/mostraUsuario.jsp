<%@page import="mack.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Projeto - Página Mostra Usuário</title>
    </head>
    <body>
        <h1>Resultado da Consulta de Usuario por ID: </h1>
        <%
            /**
             * Coleta a instância de Usuario da requisição.
             */
            Usuario u = (Usuario) request.getAttribute("usuario");
        %>
        
        <%if (u!=null) { %>
        <table>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getNome()%></td>
                <td><%=u.getSobrenome()%></td>
            </tr>
        </table>
        <%}%>
    </body>
</html>
