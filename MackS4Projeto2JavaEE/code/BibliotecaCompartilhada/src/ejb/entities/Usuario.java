package ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Descreve um usuário.
 */
@XmlRootElement(name = "usuario") // Anotação XML: define o elemento e o nome
@XmlAccessorType(XmlAccessType.FIELD) //Anotação XML: define o tipo de acesso
@Entity //Anotação JPA: define uma entidade
@Table(name = "tb_usuario") //Anotação JPA: define o nome da tabela
public class Usuario {

    @XmlAttribute //Anotação XML: define o atributo ID.
    @Id //Anotação JPA: indica qual atributo será mapeado para a chave primária
    @Column(name = "usuario_id") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    @SequenceGenerator(name = "usuarioGenerator",sequenceName = "usuario_id_sequence", allocationSize = 1) //Anotação JPA: define a sequencia do valor gerado para a chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGenerator") //Anotação JPA: define como o valor da chave primária será gerada
    private int id;
    
    @XmlElement //Anotação XML: define o elemento nome.
    @Column(name = "nome") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String nome;
    
    @XmlElement //Anotação XML: define o elemento sobrenome.
    @Column(name = "sobrenome") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String sobrenome;
    
    @XmlElement //Anotação XML: define o elemento login.
    @Column(name = "login") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String login;
    
    @XmlElement //Anotação XML: define o elemento hash.
    @Column(name = "hash") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String hash;
    
    /**
     * Contrói um usuário sem valores de atributos.
     * Importante: para que o JPA possa utilizar a classe como entidade, 
     * devemos definir o construtor padrão (sem parâmetros).
     */
    public Usuario() {
    }
    
    /**
     * Contrói um usuário.
     * @param id int
     * @param nome String
     * @param sobrenome String
     */
    public Usuario(int id, String nome, String sobrenome, String login, String hash) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.hash = hash;
    }
    
    /**
     * Retorna o nome do usuário. 
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Altera o nome do usuário. 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna o sobrenome do usuário. 
     */
    public String getSobrenome() {
        return sobrenome;
    }
    
    /**
     * Altera o sobrenome do usuário. 
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    /**
     * Altera o ID do usuário. 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Retorna o ID do usuário. 
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna o Login do usuário. 
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * Altera o Login do usuário. 
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /**
     * Retorna o código Hash do usuário. 
     */
    public String getHash() {
        return hash;
    }

    /**
     * Altera o código Hash do usuário. 
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    /**
     * Transforma os atributos em uma String para visualização do estado da instância.
     * @return string contendo o estado do objeto.
     */
    public String toString() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("id = ");
        sbResult.append(id);
        sbResult.append(", nome = ");
        sbResult.append(nome);
        sbResult.append(", sobrenome = ");
        sbResult.append(sobrenome);
        return sbResult.toString();
    }
}
