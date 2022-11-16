package ejb.services;

import ejb.beans.UsuarioBean;
import ejb.entities.Usuario;
import java.io.StringReader;
import java.net.URI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Define um Bean de Sessão sem Estado. Nele é implantado as regras de negócio 
 * referente ao Web Service Rest para buscar um usuário por ID ou inserir um 
 * novo usuário.
 */
@Stateless
@LocalBean
@Path("/usuarios/")
public class UsuarioRestService {

    @EJB
    UsuarioBean ub;
    
    /**
     * Consulta um determinado usuário pelo seu ID. 
     */
    @GET
    @Path("/usuario/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Usuario buscaUsuarioPorId(@PathParam("id") final int id) {
        System.out.println("Buscando usuario: " + id);
        Usuario u = ub.buscaUsuarioPorId(id);
        if (u == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return u;
    }
    
    /**
     * Insere um novo usuário. 
     */
    @Path("/novousuario")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response insereUsuario(String usuarioXml) {
        int id = 0;
        try {
            JAXBContext jc = JAXBContext.newInstance(Usuario.class);
            Unmarshaller u = jc.createUnmarshaller();
            StringReader reader = new StringReader(usuarioXml);
            Usuario usuario = (Usuario) u.unmarshal(reader);
            Usuario user = ub.criaUsuario(usuario);
            id = user.getId();
        } catch (JAXBException ex) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return Response.created(URI.create("/usuario/" + id)).build();
    }
}