package com.example.mvp_with_dagger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvp_with_dagger.model.Product;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Product> products;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView, productNameTextView, descriptionTextView, salePriceTextView;

        public MyViewHolder(View view) {
            super(view);
            idTextView = (TextView) view.findViewById(R.id.idTextView);
            productNameTextView = (TextView) view.findViewById(R.id.productNameTextView);
            descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
            salePriceTextView = (TextView) view.findViewById(R.id.salePriceTextView);
        }
    }

    public RecyclerViewAdapter(List<Product> products) {
        this.products = products;
    }

    public void showAddProduct(Product product) {
        products.add(product);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.idTextView.setText("" + product.getId());
        holder.productNameTextView.setText(product.getProductName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.salePriceTextView.setText("" + product.getSalePrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}