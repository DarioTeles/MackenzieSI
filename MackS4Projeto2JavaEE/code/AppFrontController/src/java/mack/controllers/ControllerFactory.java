package mack.controllers;

/**
 * Define a Factory reponsável por encapsular a instância dos Controllers.
 */
public class ControllerFactory 
{
    /**
     * Método estático responsável por criar uma nova instância de um 
     * determindo Controller.
     * @param actionClass identificador do Controller
     * @return nova instância de um determinado Controller
     */
    public static final Controller getControllerByClass(Class actionClass) 
    {
        try 
        {
            return (Controller) actionClass.newInstance();
        } catch (java.lang.InstantiationException e) 
        {
            e.printStackTrace();
        } catch (IllegalAccessException e) 
        {
            e.printStackTrace();
        } catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Método estático responsável por concatenar o caminho da estrutura das 
     * Controllers com o parâmetro passado pelo Servlet resultando assim no 
     * identificador do Controller. Após isso, o identificador é para para 
     * o método getControllerByClass.
     * @param className parâmetro que identifica o Controller
     * @return nova instância de um determinado Controller
     */
    public static final Controller getControllerByFullClassName(String className) 
    {
        try 
        {
            String name = "mack.controllers.impl." + className + "Controller";
            Class actionClass = Class.forName(name);
            return getControllerByClass(actionClass);
        } catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}