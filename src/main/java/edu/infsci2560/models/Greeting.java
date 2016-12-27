/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.infsci2560.models;

public class Greeting {    
    private final int id;
    private final String name;
    
    public Greeting() {
        this.id = 0;
        this.name = null;
    }

    public Greeting(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
