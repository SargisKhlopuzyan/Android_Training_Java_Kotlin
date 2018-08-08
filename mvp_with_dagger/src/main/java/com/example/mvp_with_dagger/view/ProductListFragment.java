package com.example.mvp_with_dagger.view;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mvp_with_dagger.ProductListContract;
import com.example.mvp_with_dagger.R;
import com.example.mvp_with_dagger.RecyclerViewAdapter;
import com.example.mvp_with_dagger.model.Product;
import com.example.mvp_with_dagger.presenter.ProductListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment implements ProductListContract.View {

    private RecyclerView recyclerView;
    private Button showProducts;
    private Button addProduct;
    private Button editProduct;
    private Button deleteProduct;

    private ProductListContract.Actions mPresenter;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        findViews(rootView);
        setListeners();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Product>());
        recyclerView.setAdapter(recyclerViewAdapter);

        //Instantiate the Presenter
        mPresenter = new ProductListPresenter(this);

        //Initialize Adapter and Recyclyer View here
        //With an empty list of products
        return rootView;
    }

    private void findViews(View mRootView) {
        recyclerView = (RecyclerView)mRootView.findViewById(R.id.recyclerView);
        showProducts = (Button) mRootView.findViewById(R.id.showProducts);
        addProduct = (Button) mRootView.findViewById(R.id.addProduct);
        editProduct = (Button) mRootView.findViewById(R.id.editProduct);
        deleteProduct = (Button) mRootView.findViewById(R.id.deleteProduct);
    }

    private void setListeners() {
        showProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProducts(null);
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductForm();
            }
        });

        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProductForm(null);
            }
        });

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteProductPrompt(null);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //Ask the Presenter to load the list of products
        mPresenter.loadProducts();
    }

    @Override
    public void showProducts(List<Product> products) {
        //The Presenter returns the list of products here
        //call the replace data method in the Adapter

        Log.e("LOG_TAG", "showProducts");
    }

    @Override
    public void showAddProductForm() {
        //startActivity(new Intent(Add Product Activity or Fragment))

        RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        Log.e("LOG_TAG", "showAddProductForm");
        if (recyclerViewAdapter != null) {
            Product product = new Product();
            recyclerViewAdapter.showAddProduct(product);
            Log.e("LOG_TAG", "!= null");
        } else {
            Log.e("LOG_TAG", "null");
        }

    }

    @Override
    public void showEditProductForm(Product product) {
        //startActivity(new Intent(Edit Product Activity or Fragment))
        //Pass in the product to be edited
        Log.e("LOG_TAG", "showEditProductForm");
    }

    @Override
    public void showDeleteProductPrompt(Product product) {
        Log.e("LOG_TAG", "showDeleteProductPrompt");
    }

    @Override
    public void showGoogleSearch(Product product) {

    }


    @Override
    public void showEmptyText() {
        //Hide RecyclerView
        //Show a TextView showing not items in the list
    }

    @Override
    public void hideEmptyText() {
        //Hide the TextView showing no items in the list
        //Show RecyclerView
    }

    @Override
    public void showMessage(String message) {
        //Show a Toast or Snackbar with the message from the
        //Presenter such as Item added, item deleted, etc
    }

}
