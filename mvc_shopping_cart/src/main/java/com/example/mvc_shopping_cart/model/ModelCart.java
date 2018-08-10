package com.example.mvc_shopping_cart.model;

import java.util.ArrayList;

public class ModelCart {

    private  ArrayList<ModelProducts> cartProducts = new ArrayList<ModelProducts>();

    public ModelProducts getProducts(int position) {
        return cartProducts.get(position);
    }

    public void setProducts(ModelProducts products) {
        cartProducts.add(products);
    }

    public int getCartSize() {
        return cartProducts.size();
    }

    public boolean checkProductInCart(ModelProducts product) {
        return cartProducts.contains(product);
    }

}
