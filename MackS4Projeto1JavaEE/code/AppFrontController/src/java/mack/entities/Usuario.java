package mack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Descreve um usuário.
 */
@Entity //Anotação JPA: define uma entidade
@Table(name = "tb_usuario") //Anotação JPA: define o nome da tabela 
public class Usuario {
    
    @Id //Anotação JPA: indica qual atributo será mapeado para a chave primária
    @Column(name = "usuario_id") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    @SequenceGenerator(name = "usuarioGenerator", sequenceName = "usuario_id_sequence", allocationSize = 1) //Anotação JPA: define a sequencia do valor gerado para a chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGenerator") //Anotação JPA: define como o valor da chave primária será gerada
    private int id;
    
    @Column(name="nome") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String nome;
    
    @Column(name="sobrenome") //Anotação JPA: define o nome da coluna (atributo) no banco de dados
    private String sobrenome;
    
    /**
     * Contrói um usuário sem valores de atributos.
     * Importante: para que o JPA possa utilizar a classe como entidade, 
     * devemos definir o construtor padrão (sem parâmetros).
     */
    public Usuario(){
    }
    
    /**
     * Contrói um usuário.
     * @param id int
     * @param nome String
     * @param sobrenome String
     */
    public Usuario(int id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
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
     * Retorna o ID do usuário. 
     */
    public int getId() {
        return this.id;
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
