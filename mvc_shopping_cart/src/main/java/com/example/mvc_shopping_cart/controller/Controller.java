package com.example.mvc_shopping_cart.controller;

import android.app.Application;
import com.example.mvc_shopping_cart.model.ModelCart;
import com.example.mvc_shopping_cart.model.ModelProducts;

import java.util.ArrayList;

public class Controller extends Application {

    private  ArrayList<ModelProducts> myProducts = new ArrayList<ModelProducts>();
    private ModelCart myCart = new ModelCart();

    public ModelProducts getProducts(int position) {
        return myProducts.get(position);
    }

    public void setProducts(ModelProducts products) {
        myProducts.add(products);
    }

    public ModelCart getCart() {
        return myCart;
    }

    public int getProductsArrayListSize() {
        return myProducts.size();
    }

}