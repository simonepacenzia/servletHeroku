package utils;


import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simonepacenzia
 */
public class Contact {
    private String number;
    private String name;
    
    // Costruttore senza argomenti
    public Contact() { }

    // Proprietà "nome" (da notare l'uso della maiuscola) lettura / scrittura
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        String s = "{ 'name' : '" + name + "' , 'number' : '" + number + "' }" ;
        return s;
    }
    
}
