/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agendamento;

import Cad_Usuario.Cad_Usuario;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public class Agendamento {
    private int id_usuario;
    
    private int id_empresa;
    
    private String email;
    
    private String telefone;
    
    private LocalDate data;
    
    private String hora;
    
    

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int nome) {
        this.id_usuario = nome;
    }

    public int getIdEmpresa() {
        return id_empresa;
    }

    public void setIdEmpresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
  
}
