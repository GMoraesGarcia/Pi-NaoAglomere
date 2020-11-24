/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesquisa;

import Cad_Empresa.Cad_Empresa_dados;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class PesquisarDados {

    private String pesquisa;
    private ArrayList<Cad_Empresa_dados> estabelecimentos;

    public ArrayList<Cad_Empresa_dados> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(ArrayList<Cad_Empresa_dados> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    //Inseri uma empresa na pesquisa
    public void addEstabelecimento(Cad_Empresa_dados c) {
        estabelecimentos.add(c);
    }

}
