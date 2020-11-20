package Cad_Empresa;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class Cad_EmpresaDados {
    
    private int empresa_id;

    private String Nome_Empresa;

    private String CNPJ;

    private String email;

    private String Descricao;

    private String Telefone;

    private String Senha;

    private int qtd_max;

    private String Rua;

    private String Bairro;

    private int Numero_Rua;

    private String Regras;

    private String Agendamento;

    private String HoraAb;

    private String Foto;

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    
    public int getId() {
        return empresa_id;
    }

    public void setId(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getHoraAb() {
        return HoraAb;
    }

    public void setHoraAb(String HoraAb) {
        this.HoraAb = HoraAb;
    }

    public String getAgendamento() {
        return Agendamento;
    }

    public void setAgendamento(String Agendamento) {
        this.Agendamento = Agendamento;
    }

    public String getNome_Empresa() {
        return Nome_Empresa;
    }

    public void setNome_Empresa(String Nome_Empresa) {
        this.Nome_Empresa = Nome_Empresa;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getQtd_max() {
        return qtd_max;
    }

    public void setQtd_max(int qtd_max) {
        this.qtd_max = qtd_max;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public int getNumero_Rua() {
        return Numero_Rua;
    }

    public void setNumero_Rua(int Numero_Rua) {
        this.Numero_Rua = Numero_Rua;
    }

    public String getRegras() {
        return Regras;
    }

    public void setRegras(String Regras) {
        this.Regras = Regras;
    }

    @Override
    public String toString() {
        return "Cad_EmpresaDados{" + "Nome_Empresa=" + Nome_Empresa + ", CNPJ=" + CNPJ + ", email=" + email + ", Descricao=" + Descricao + ", Telefone=" + Telefone + ", Senha=" + Senha + ", qtd_max=" + qtd_max + ", Rua=" + Rua + ", Bairro=" + Bairro + ", Numero_Rua=" + Numero_Rua + ", Regras=" + Regras + ", Agendamento=" + Agendamento + '}';
    }

    public int getNumberAgendamento() {
        Cad_EmpresaDados ep = new Cad_EmpresaDados();

        if (ep.getAgendamento().equalsIgnoreCase("sim")) {
            return 1;
        }

        return 0;

    }
    
    public float agendPorDia(String horarioAbertura, String horarioFechamento, String duracaoAtendimento){
        System.out.println(horarioAbertura + " " + horarioFechamento + " " + duracaoAtendimento);
        float hrAbertura = Float.parseFloat(horarioAbertura);
        System.out.println(hrAbertura);
        float hrFechamneto = Float.parseFloat(horarioFechamento);
        float duracao = Float.parseFloat(duracaoAtendimento);
        
        float qtdAgend = hrFechamneto - hrAbertura;
        float x = qtdAgend / duracao;
        
        return x;
    
    }
   public ArrayList<Time>horariosAgend (String horarioAbertura,String horarioFechamento,float x){
      ArrayList<Time> horarios = new ArrayList<>();
      
        int i =0;
        while(i<x){
            if(i == 0){
                horarios.add(Time.valueOf(horarioAbertura));
            }  
            else{
                float novoHorario = Integer.parseInt(horarioAbertura +i);
                
                horarios.add(Time.valueOf(String.valueOf(novoHorario)));
            }
     
        }
        return horarios;
    }
    

}
