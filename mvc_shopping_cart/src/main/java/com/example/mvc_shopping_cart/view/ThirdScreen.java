package com.example.mvc_shopping_cart.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mvc_shopping_cart.R;
import com.example.mvc_shopping_cart.controller.Controller;

public class ThirdScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        TextView showCartContent = (TextView) findViewById(R.id.showCart);

        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        final Controller aController = (Controller) getApplicationContext();

        int cartSize = aController.getCart().getCartSize();

        String showString = "";

        /******** Show Cart Products on screen - Start ********/

        for(int i=0;i<cartSize;i++)
        {
            //Get product details
            String pName = aController.getCart().getProducts(i).getProductName();
            int pPrice = aController.getCart().getProducts(i).getProductPrice();
            String pDesc = aController.getCart().getProducts(i).getProductDesc();

            showString += "Product Name : " + pName + ", " + "Price : " + pPrice + ", " + "Description : " + pDesc + "\n";
        }

        showCartContent.setText(showString);

        /******** Show Cart Products on screen - End ********/

    }
}