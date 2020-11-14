package Login;

import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Matheus
 */
public class LoginDados implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String hashSenha;

    private String tipo_cadastro;

    LoginDados() {
        email = null;
        hashSenha = null;
        tipo_cadastro = null;
    }

    LoginDados(String email, String hashSenha, String tipo_cadastro) {
        this.email = email;
        this.hashSenha = hashSenha;
        this.tipo_cadastro = tipo_cadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public String getTipo_cadastro() {
        return tipo_cadastro;
    }

    public void setTipo_cadastro(String tipo_cadastro) {
        this.tipo_cadastro = tipo_cadastro;
    }

    public final void setSenha(String senhaAberta) {
        this.hashSenha = BCrypt.hashpw(senhaAberta, BCrypt.gensalt());
    }

    public boolean validarSenha(String senhaAberta) {
        return BCrypt.checkpw(senhaAberta, hashSenha);
    }

}
