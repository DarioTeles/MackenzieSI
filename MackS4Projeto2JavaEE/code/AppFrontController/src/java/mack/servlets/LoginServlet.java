package mack.servlets;

import ejb.beans.UsuarioBean;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Descreve um Servlet para o fluxo da aplicação Login.
 */
public class LoginServlet extends HttpServlet {
    
    @EJB
    UsuarioBean ub;
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String usuarioRequest = request.getParameter("login");
        String senhaRequest = request.getParameter("senha");

        if (ub.autentica(usuarioRequest, senhaRequest)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioRequest);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("usuario", usuarioRequest);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("sucessoLogin.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Usuario ou senha incorretos.</font>");
            rd.include(request, response);
        }
    }
}
