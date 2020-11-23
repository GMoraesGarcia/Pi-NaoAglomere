/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3_static_naoaglomere2;

import Cad_Empresa.Cad_Empresadados;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class teste {
    public static void main(String[] args) {
        ArrayList<Time> horas;
        
        
         Cad_Empresadados dados = new Cad_Empresadados();
           float qtdAgendamentos =  dados.agendPorDia("7:00", "18:30", "1:00");
          horas = dados.horariosAgend("7:00", "18:30", qtdAgendamentos);
          
          for(int i =0;i<horas.size();i++){
              System.out.println(horas.get(i));
          }
    }
    
}
