<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto - Pós Login</title>
    </head>
    <body>
        <%
            /**
                 * Coleta os valores armazenados no cookie.
                 */
                String user = (String) session.getAttribute("usuario");
                String userName = null;
                String sessionID = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("usuario")) {
                            userName = cookie.getValue();
                        }
                        if (cookie.getName().equals("JSESSIONID")) {
                            sessionID = cookie.getValue();
                        }
                    }
                }
        %>
        <h3>Olá <%=userName%>, login concluído. Seu ID de sessão é = <%=sessionID%> .</h3>
        <br>
        Usuário = <%=user%>
        <br>
        <a href="index.html">Fluxo de Aplicação: FronControllerServlet</a>
        <form action="MeuPrimeiroBeanServlet" method="post">
            <input type="submit" value="Fluxo de Aplicação: MeuPrimeiroBeanServlet" >
        </form>
        <br>
        <a href="checkout.jsp">Pagina de Checkout</a>
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>