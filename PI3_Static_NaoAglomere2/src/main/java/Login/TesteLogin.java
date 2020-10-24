package Login;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author leona
 */
public class TesteLogin {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean emailValido = false;
        boolean senhaValida = false;
        String tipo_cadastro = null;

        System.out.println("Digite o email:");
        String email = sc.next();

        System.out.println("Digite a senha:");
        String senha = sc.next();

        LoginDao dao = new LoginDao();

        try {

            LoginDados dados = dao.findByEmail(email);

            if (dados.getEmail() == null) {
                System.out.println("Email não encontrado");
                return;
            }

            if (dados.getEmail().equals(email)) {
                emailValido = true;
            }
            if (dados.getSenha().equals(senha)) {
                senhaValida = true;
            }
            if (dados.getTipo_cadastro().equals("empresa")) {
                tipo_cadastro = dados.getTipo_cadastro();
            }
            if (dados.getTipo_cadastro().equals("usuario")) {
                tipo_cadastro = dados.getTipo_cadastro();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Tipo de cadastro: " + tipo_cadastro);

        if (emailValido && senhaValida && tipo_cadastro.equals("usuario")) {
            System.out.println("Bem vindo " + email);
        }
        if (emailValido && senhaValida && tipo_cadastro.equals("empresa")) {
            System.out.println("Bem vindo " + email);
        }

    }
}
