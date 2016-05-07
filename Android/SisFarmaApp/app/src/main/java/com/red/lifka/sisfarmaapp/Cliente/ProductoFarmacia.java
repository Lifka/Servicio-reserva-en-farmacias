package com.red.lifka.sisfarmaapp.Cliente;


public class ProductoFarmacia {
    private int stock;
    private Producto producto;

    public ProductoFarmacia(Producto pro){
        producto = pro;
        this.stock = 0;
    }

   public ProductoFarmacia(Producto pro, int stock){
        producto = pro;
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

    public Producto getProduct(){
        return producto;
    }
}
