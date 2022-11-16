package ejb.beans;

import ejb.entities.Usuario;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Define um Bean de Sessão sem Estado. Nele é implantado as regras de negócio 
 * referente a autenticação do usuário e ao CRUD da entidade Usuario.
 */
@Stateless
@LocalBean
public class UsuarioBean {

    @PersistenceContext(unitName = "DerbyPU")
    private EntityManager em;
    
    /**
     * Insere um novo usuário ao banco de dados.
     */
    public Usuario criaUsuario(Usuario u) {
        em.persist(u);
        em.flush();
        em.refresh(u);
        return u;
    }
    
    /**
     * Consulta no banco dados todos os usuários. 
     */
    public List<Usuario> list() {
        Query query = em.createQuery("FROM Usuario u");
        List<Usuario> list = query.getResultList();
        return list;
    }
    
    /**
     * Consulta no banco de dados um usuário a partir do seu ID. 
     */
    public Usuario buscaUsuarioPorId(final int id) {
        Usuario u = em.find(Usuario.class, id);
        return u;
    }
    
    /**
     * Consulta usuários no banco de dados a partir de um nome. 
     */
    public Collection buscaUsuarioPorNome(final String nome) {
        Query q = em.createQuery("select u from Usuario u where u.nome = :par1");
        q.setParameter("par1", nome);
        Collection result = null;
        result = q.getResultList();
        return result;
    }
    
    
    /**
     * Atualiza os dados de um usuário no banco de dados. 
     */
    public void updateUsuario(Usuario user) {
        Usuario u = em.find(Usuario.class, user.getId());
        if (u != null) {
            u.setNome(user.getNome());
            u.setSobrenome(user.getSobrenome());
            u.setLogin(user.getLogin());
            em.merge(u);
        }
    }
    
    /**
     * Exclui um usuário no banco de dados a partir do seu ID. 
     */
    public void removeUsuario(final int id) {
        Usuario u = em.find(Usuario.class, id);
        if (u != null) {
            em.remove(u);
        }
    }
    
    /**
     * Consulta a lista de usuários e valida o login e senha do usuário.
     */
    public boolean autentica(String user, String senha) {
        Query query = em.createQuery("FROM Usuario u where u.login='" + user + "'");
        List<Usuario> list = query.getResultList();
        if (list.size() != 1) {
            return false;
        }
        Usuario u = list.get(0);
        try {
            if (user.equals(u.getLogin()) && validaSenha(senha, u.getHash())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Consulta a lista de usuários e altera a senha.
     */
    public Usuario alteraSenha(String usuario, String senha, String novaSenha) {
        Query query = em.createQuery("FROM Usuario u where u.login='" + usuario + "'");
        List<Usuario> list = query.getResultList();
        if (list.size() != 1) {
            return null;
        }
        Usuario u = list.get(0);
        try {
            if (usuario.equals(u.getLogin()) && validaSenha(senha, u.getHash())) {
                u.setHash(generateStrongPasswordHash(novaSenha));
                em.persist(u);
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Validação de senha baseada no código Hash do usuário.
     */
    private static boolean validaSenha(String senhaCandidata, String hashSenha) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = hashSenha.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        PBEKeySpec spec = new PBEKeySpec(senhaCandidata.toCharArray(), salt, iterations,
                hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashCandidato = skf.generateSecret(spec).getEncoded();
        int diff = hash.length ^ hashCandidato.length;
        for (int i = 0; i < hash.length && i < hashCandidato.length; i++) {
            diff |= hash[i] ^ hashCandidato[i];
        }
        return diff == 0;
    }
    
    /**
     * Blocos de funções estáticas para gerar o código Hash do usuário.
     */
    public String getHash(String senha) {
        try {
            return UsuarioBean.generateStrongPasswordHash(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String generateStrongPasswordHash(String password) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();
        System.out.println("Salt:" + salt.length);
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
