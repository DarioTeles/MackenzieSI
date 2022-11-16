package clientewebservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Define um consumidor de WebService Rest.
 */
public class ClienteWebService {

    public static void main(String[] args) {
        consultaPorIdXML(1);
        insereUsuarioXML("DARIO", "TELES", "DARIO");
        consultaPorIdJson(1);
    }
    
    /**
     * Consulta um usuário por ID a partir de um WebService retornando um XML.
     * @param id identificador do usuário
     */
    public static void consultaPorIdXML(int id) {
        try {
            String baseURL = "http://localhost:8080/AppFrontController/GerenciamentoUsuarios/usuarios/";
            URL url = new URL(baseURL + "usuario/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Falha : Erro HTTP: " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inseri um usuário a partir de um WebService utilizando XML.
     * @param nome
     * @param sobrenome
     * @param login 
     */
    public static void insereUsuarioXML(String nome, String sobrenome, String login) {
        try {
            String baseURL = "http://localhost:8080/AppFrontController/GerenciamentoUsuarios/usuarios/";
            URL url = new URL(baseURL + "novousuario");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/xml");
            String input = "<usuario><nome>" + nome + "</nome><sobrenome>" + sobrenome +"</sobrenome><login>" + login + "</login><hash>1000:5b42403738353265393232:ab89b0c65354d57bdcf9659a53d410fa0a8b7d0f9dee270f1e9f51d87d1a9d06bfb5d1e114566b34c6e658f6d4d7d816b90a2841a5e93e7604ee5d52963a9135</hash></usuario>";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Falha : Erro HTTP: " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Resposta do servidor .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Consulta um usuário por ID a partir de um WebService retornando um JSON.
     * @param id identificador do usuário
     */
    public static void consultaPorIdJson(int id) {
        try {
            String baseURL = "http://localhost:8080/AppFrontController/GerenciamentoUsuarios/usuarios/";
            URL url = new URL(baseURL + "usuario/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Falha : Erro HTTP: " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}