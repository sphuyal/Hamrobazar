package com.sandesh.hamrobazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandesh.hamrobazar.Model.Products;
import com.sandesh.hamrobazar.R;
import com.sandesh.hamrobazar.URL.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context context;
    List<Products> productViewList;

    public ProductAdapter(Context context, List<Products> productViewList) {
        this.context = context;
        this.productViewList = productViewList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layouts_products,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products productsView=productViewList.get(position);
        holder.tvName.setText(productsView.getProductName());
        holder.tvPrice.setText("Rs. "+productsView.getProductPrice());
        holder.tvType.setText(productsView.getProductType());

        //for setting image in recycle view
        String image=productsView.getImage();
        String imgPath= Url.imagePath+image;

        Picasso.get().load(imgPath).into(holder.imgProfile);

    }

    @Override
    public int getItemCount() {
        return productViewList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPrice,tvType;
        ImageView imgProfile;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvProductName);
            tvPrice=itemView.findViewById(R.id.tvProductPrice);
            tvType=itemView.findViewById(R.id.tvProductType);
            imgProfile=itemView.findViewById(R.id.imgProfileImg);
        }
    }



}
