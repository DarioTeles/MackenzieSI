package mack.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * Define uma classe abstrata com métodos a serem herdados pelos Controllers.
 * 
 * O que é um Controller? 
 * Um Controller (Controlador) é responsável por ligar as demais camadas MVC, 
 * ou seja, o Controller liga a View ao Modelo e direciona o fluxo da aplicação. 
 * Isto permite escolher qual View será exibida ao usuário em resposta à entrada
 * e a lógica de negócio que será processada em cima dela. Assim, o Controller 
 * recebe uma mensagem da View, que por sua vez encaminha ao Model. 
 * Este prepara uma resposta e envia de volta ao Controller que escolhe a View 
 * adequada e a envia ao usuário.
 */
public abstract class AbstractController implements Controller 
{

    private HttpServletRequest request;
    protected String returnPage;

    public void init(HttpServletRequest request) 
    {
        this.setRequest(request);
    }

    public void setReturnPage(String page) 
    {
        returnPage = page;
    }

    public String getReturnPage() 
    {
        return returnPage;
    }

    public HttpServletRequest getRequest() 
    {
        return request;
    }

    public void setRequest(HttpServletRequest request) 
    {
        this.request = request;
    }
}
