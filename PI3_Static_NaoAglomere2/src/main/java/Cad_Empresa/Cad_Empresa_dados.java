package Cad_Empresa;

import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class Cad_Empresa_dados {

    public Cad_Empresa_dados(int empresa_id, String nome_empresa, String cnpj, String email, String descricao, String telefone,
            String senha, int qtd_max, String rua, String bairro, int numero_rua, String regras, String agendamento) {
        this.empresa_id = empresa_id;
        this.nome_empresa = nome_empresa;
        this.cnpj = cnpj;
        this.email = email;
        this.descricao = descricao;
        this.telefone = telefone;
        this.senha = senha;
        this.qtd_max = qtd_max;
        this.rua = rua;
        this.bairro = bairro;
        this.numero_rua = numero_rua;
        this.regras = regras;
        this.agendamento = agendamento;
    }

    public Cad_Empresa_dados() {

    }

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

    private ArrayList<String> horariosDisponiveis = new ArrayList<>();
    ;

    private String foto;

    private int qtdAgendamentos;

    public int getQtdAgendamentos() {
        return qtdAgendamentos;
    }

    public void setQtdAgendamentos(int qtdAgendamentos) {
        this.qtdAgendamentos = qtdAgendamentos;
    }

    public int getEmpresa_Id() {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ArrayList<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(ArrayList<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public void addHorario(String horario) {
        this.horariosDisponiveis.add(horario);
    }

    @Override
    public String toString() {
        return "Cad_EmpresaDados{" + "Nome_Empresa=" + nome_empresa + ", CNPJ=" + cnpj + ", email=" + email + ", Descricao=" + descricao + ", Telefone=" + telefone + ", Senha=" + senha + ", qtd_max=" + qtd_max + ", Rua=" + rua + ", Bairro=" + bairro + ", Numero_Rua=" + numero_rua + ", Regras=" + regras + ", Agendamento=" + agendamento + '}';
    }

    public int getNumberAgendamento() {
        Cad_Empresa_dados ep = new Cad_Empresa_dados();

        if (ep.getAgendamento().equalsIgnoreCase("sim")) {
            return 1;
        }

        return -1;

    }

}
