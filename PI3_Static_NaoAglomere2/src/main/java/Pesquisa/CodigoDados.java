/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesquisa;

import Cad_Empresa.Cad_Empresa_dados;
import Cad_Usuario.Cad_Usuario;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class CodigoDados {
    private String codigo;
    
    private int idEmpresa;
    
    private Date data_geracao; 
    
    private  LocalTime horario_Geracao;
    
    private int idUsuario;
    
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return idEmpresa;
    }

    public void setId(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getData_geracao() {
        return data_geracao;
    }

    public void setData_geracao(Date data_geracao) {
        this.data_geracao = data_geracao;
    }

    public LocalTime getHorario_Geracao() {
        return horario_Geracao;
    }

    public void setHorario_Geracao(LocalTime horario_Geracao) {
        this.horario_Geracao = horario_Geracao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public boolean getTempoLimite(LocalTime hr1){
        LocalTime hr2 = LocalTime.now();
        Duration duracao = Duration.between(hr1, hr2);
        long horas = duracao.toHours();
        
       // System.out.println(horas);
       
        if(horas>= 1){
            return true;
        
        }
      
            return false;
    }
    
    
    
}
