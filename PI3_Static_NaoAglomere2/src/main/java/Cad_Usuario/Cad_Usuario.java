/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Usuario;

import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public class Cad_Usuario {
    
   private int id; 
   private String nome;
   private String cpf;
   private String email;
   private LocalDate dataNascimento;
   private String telefone;
   private String senha;
   private String confirmarSenha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
    
    public  boolean validaCPF(char ArrayCPF[]) {
        String digito01 = String.valueOf(ArrayCPF[9]);
        String digito02 = String.valueOf(ArrayCPF[10]);
        int num = 10, r = 0, i = 0;

        while (num >= 2) {
            //Validação do primeiro digito
            String m = String.valueOf(ArrayCPF[i]);
            int m2 = Integer.parseInt(m);
            r += m2 * num;
            num--;
            i++;
        }
        int mult = (r * 10) % 11;
        if (mult == 10) {
            mult = 0;
        }
        if (mult == Integer.parseInt(digito01)) {
            //Validação para o segundo Digito
            num = 11;
            i = 0;
            r = 0;
            while (num >= 2) {
                String valoresSegundoDigitos = String.valueOf(ArrayCPF[i]);
                int valor2 = Integer.parseInt(valoresSegundoDigitos);
                r += valor2 * num;
                i++;
                num--;
            }
            int mult2 = (r * 10) % 11;
            if (mult2 == 10) {
                mult2 = 0;
            }
            if (mult2 == Integer.parseInt(digito02)) {
                return true;
            }
        }

        return false;

    }
   
   
    
}
