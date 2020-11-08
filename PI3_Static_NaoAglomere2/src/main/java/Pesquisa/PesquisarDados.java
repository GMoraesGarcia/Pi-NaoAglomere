/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesquisa;

import Cad_Empresa.Cad_EmpresaDados;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class PesquisarDados {
    private String pesquisa;
    private ArrayList<Cad_EmpresaDados> estabelecimentos;

    public ArrayList<Cad_EmpresaDados> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(ArrayList<Cad_EmpresaDados> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }
    

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
     //Inseri uma empresa na pesquisa
    public void addEstabelecimento(Cad_EmpresaDados c) {
           estabelecimentos.add(c);
    }
    
}
