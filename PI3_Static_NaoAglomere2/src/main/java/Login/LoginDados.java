package Login;

/**
 *
 * @author leona
 */
public class LoginDados {
    
    private String email;
    
    private String senha;
    
    private String tipo_cadastro;
    
    LoginDados(){
        email = null;
        senha = null;
        tipo_cadastro = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_cadastro() {
        return tipo_cadastro;
    }

    public void setTipo_cadastro(String tipo_cadastro) {
        this.tipo_cadastro = tipo_cadastro;
    }
    
    
}
