package com.example.dagger_2;

import com.example.dagger_2.model.LineItem;
import com.example.dagger_2.model.Product;

import javax.inject.Inject;

public class ProductListener {

    // We are creating a class member variable for the shopping cart that we will be injecting to this class

    @Inject
    ShoppingCart mCart;

    public ProductListener() {
        //Here is where the actual injection takes place
        ShopApplication.getInstance().getApplicationComponent().inject(this);
    }

    //Here is an example of how we are using the injected shopping cart
    public void onItemQuantityChanged(LineItem item, int qty) {
        mCart.updateItemQty(item, qty);
    }

    //Another example of using the shopping cart
    public void onAddToCartButtonClicked(Product product) {
        //perform add to checkout button here
        LineItem item = new LineItem(product, 1);
        mCart.addItemToCart(item);
    }

    public void onClearButtonClicked() {
        mCart.clearShoppingCart();
    }

    public void onDeleteItemButtonClicked(LineItem item) {
        mCart.removeItemFromCart(item);
    }

}
