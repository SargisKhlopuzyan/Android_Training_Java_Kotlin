package com.example.mvp_with_dagger;

import com.example.mvp_with_dagger.model.Product;

import java.util.List;

public class TempRepository implements ProductListContract.Repository{

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }
}
