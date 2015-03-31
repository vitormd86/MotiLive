package com.example.henrique.list.JSON;

/**
 * Created by htamashiro on 3/29/15.
 *
 * Aqui  estamos utilizando strategy pattern para desacoplar e evitar duplicação de código
 */

//talvez tenha q definir uma tela abstrata para poder fazer herança nas telas
public class TelaWebService {

    //composição de objetos,  colocando a interface Telas dentro do objeto TelaWebService

    public Telas numeroTela;
    
// aqui eh objeto q chamaremos independente da tela
    public String tentaExecutarWebService()

    {
        return numeroTela.escolheTela();
    }
    //  esta eh a função q usaremos para mudar a tela atual
    public void setActualPage(Telas newTela){
        numeroTela = newTela;
    }
}