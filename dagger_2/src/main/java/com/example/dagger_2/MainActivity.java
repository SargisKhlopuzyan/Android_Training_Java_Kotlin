package com.example.dagger_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dagger_2.model.LineItem;
import com.example.dagger_2.model.Product;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductListener productListener = new ProductListener();

        Product product =  new Product();
        product.setDescription("Description");
        product.setProductName("ProductName");
        product.setId(1);
        product.setSalePrice(11111);

        productListener.onAddToCartButtonClicked(product);

        List<LineItem> list = productListener.mCart.getShoppingCart();

        String string = "[ ";
        for (LineItem item : list) {
            string += "{ ";
            string += list.get(0).getProductName() + ", ";
            string += list.get(0).getDescription() + ", ";
            string += list.get(0).getQuantity() + ", ";
            string += list.get(0).getSalePrice() + ", ";
            string += list.get(0).getSumPrice();
            string += " }";
        }
        string += " ]";
        Log.e("LOG_TAG", "list: " + string);
    }
}
