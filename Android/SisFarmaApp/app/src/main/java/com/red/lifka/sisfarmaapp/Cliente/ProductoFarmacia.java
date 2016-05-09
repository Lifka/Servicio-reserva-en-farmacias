package com.red.lifka.sisfarmaapp.Cliente;


public class ProductoFarmacia {
    private int stock;
    private int id;

    public ProductoFarmacia(int id){
        this.id = id;
        this.stock = 0;
    }

   public ProductoFarmacia(int id, int stock){
        this.id = id;
        this.stock = stock;
    }

    public void add(int i){
        stock += i;
    }

    public void add(){
        stock += 1;
    }

    public void subtract(int i){
        stock -= i;
    }

    public void subtract(){
        stock -= 1;
    }

    public int getStock(){
        return stock;
    }

    public int getProductID(){
        return id;
    }
}
