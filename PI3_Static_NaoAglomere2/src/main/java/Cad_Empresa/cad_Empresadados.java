package Cad_Empresa;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class cad_Empresadados {

    private int empresa_id;

    private String nome_empresa;

    private String cnpj;

    private String email;

    private String descricao;

    private String telefone;

    private String senha;

    private int qtd_max;

    private String rua;

    private String bairro;

    private int numero_rua;

    private String regras;

    private String agendamento;

    private String horaAb;

    private String foto;

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQtd_max() {
        return qtd_max;
    }

    public void setQtd_max(int qtd_max) {
        this.qtd_max = qtd_max;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero_rua() {
        return numero_rua;
    }

    public void setNumero_rua(int numero_rua) {
        this.numero_rua = numero_rua;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    public String getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(String agendamento) {
        this.agendamento = agendamento;
    }

    public String getHoraAb() {
        return horaAb;
    }

    public void setHoraAb(String horaAb) {
        this.horaAb = horaAb;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Cad_EmpresaDados{" + "Nome_Empresa=" + nome_empresa + ", CNPJ=" + cnpj + ", email=" + email + ", Descricao=" + descricao + ", Telefone=" + telefone + ", Senha=" + senha + ", qtd_max=" + qtd_max + ", Rua=" + rua + ", Bairro=" + bairro + ", Numero_Rua=" + numero_rua + ", Regras=" + regras + ", Agendamento=" + agendamento + '}';
    }

    public int getNumberAgendamento() {
        cad_Empresadados ep = new cad_Empresadados();

        if (ep.getAgendamento().equalsIgnoreCase("sim")) {
            return 1;
        }

        return -1;

    }

    public float agendPorDia(String horarioAbertura, String horarioFechamento, String duracaoAtendimento) {
        System.out.println(horarioAbertura + " " + horarioFechamento + " " + duracaoAtendimento);
        float hrAbertura = Float.parseFloat(horarioAbertura);
        System.out.println(hrAbertura);
        float hrFechamneto = Float.parseFloat(horarioFechamento);
        float duracao = Float.parseFloat(duracaoAtendimento);

        float qtdAgend = hrFechamneto - hrAbertura;
        float x = qtdAgend / duracao;

        return x;

    }

    public ArrayList<Time> horariosAgend(String horarioAbertura, String horarioFechamento, float x) {
        ArrayList<Time> horarios = new ArrayList<>();

        int i = 0;
        while (i < x) {
            if (i == 0) {
                horarios.add(Time.valueOf(horarioAbertura));
            } else {
                float novoHorario = Integer.parseInt(horarioAbertura + i);

                horarios.add(Time.valueOf(String.valueOf(novoHorario)));
            }

        }
        return horarios;
    }

}
