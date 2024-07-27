package com.android.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.product.seller.ProductDetails;
import com.example.agrishop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ProductDetailsHolder>{


    private List<ProductDetails> productDetailsList;
    private Context context;

    public ProductDetailsAdapter(List<ProductDetails> productDetailsList, Context context) {
        this.productDetailsList = productDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        return new ProductDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsHolder holder, int position) {
        holder.productName.setText(productDetailsList.get(position).getProductName());
        holder.productCategory.setText(productDetailsList.get(position).getCategory());
        holder.productPrice.setText(productDetailsList.get(position).getPrice());
        holder.productQuantity.setText(productDetailsList.get(position).getQuantity());
        holder.productDescription.setText(productDetailsList.get(position).getDescription());
        Picasso.get().load(productDetailsList.get(position).getPic()).into(holder.productPic);
    }

    @Override
    public int getItemCount() {
        return productDetailsList.size();
    }

    public class ProductDetailsHolder extends RecyclerView.ViewHolder{

        private TextView productName, productCategory, productPrice, productQuantity, productDescription;
        private ImageView productPic;
        public ProductDetailsHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productCategory = itemView.findViewById(R.id.productCategory);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPic = itemView.findViewById(R.id.img);
        }
    }
}
