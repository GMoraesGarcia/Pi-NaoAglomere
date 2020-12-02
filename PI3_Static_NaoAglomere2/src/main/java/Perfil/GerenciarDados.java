/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Perfil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class GerenciarDados {

    private ArrayList<GerenciarDados> agendamentos;

    private String numAgendamento;

    private String nomeUser;

    private String nomeEmpresa;

    private String data;

    private String horario;

    public ArrayList<GerenciarDados> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(ArrayList<GerenciarDados> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public String getNumAgendamento() {
        return numAgendamento;
    }

    public void setNumAgendamento(String numAgendamento) {
        this.numAgendamento = numAgendamento;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "GerenciarDados{" + "agendamentos=" + agendamentos + ", nomeUser=" + nomeUser + ", nomeEmpresa=" + nomeEmpresa + ", data=" + data + ", horario=" + horario + '}';
    }

    public String getDataFormatada(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate data1 = LocalDate.parse(data);
        String formatado = data1.format(formatter);

        return formatado;
    }

}
