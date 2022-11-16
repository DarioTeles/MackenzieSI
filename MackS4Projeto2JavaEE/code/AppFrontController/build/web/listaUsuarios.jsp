<%@page import="ejb.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Projeto - Página Lista de Usuários</title>
    </head>
    <body>
        <h1>Lista de Usuários</h1>
        <%
            /**
             * Coleta as instâncias da requisição e adiciona a Lista usuarios. 
             */
            List<Usuario> usuarios
                    = (List<Usuario>) request.getAttribute("usuarios");
        %>
        <%if (usuarios.size() > 0) { %>
        <table>
            <% for (Usuario u : usuarios) {%>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getNome()%></td>
                <td><%=u.getSobrenome()%></td>
                <td><%=u.getLogin()%></td>
                <td><%=u.getHash()%></td>
            </tr>
            <%}%>
        </table>
        <%}%>
    </body>
</html>