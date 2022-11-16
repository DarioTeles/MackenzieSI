package mack.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * Define a interface para a implementação de um Controller.
 */
public interface Controller 
{
    public void execute();
    public void init(HttpServletRequest request);
    public String getReturnPage();
}
