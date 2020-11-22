/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesquisa;

import Cad_Empresa.cad_Empresadados;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class PesquisarDados {

    private String pesquisa;
    private ArrayList<cad_Empresadados> estabelecimentos;

    public ArrayList<cad_Empresadados> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(ArrayList<cad_Empresadados> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    //Inseri uma empresa na pesquisa
    public void addEstabelecimento(cad_Empresadados c) {
        estabelecimentos.add(c);
    }

}
