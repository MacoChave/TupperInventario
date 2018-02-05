package com.macochave.tupperinventario.holder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADProducto;

import java.util.ArrayList;

/**
 * Created by marco on 11/01/18.
 */

public class HolderProducto extends RecyclerView.Adapter<HolderProducto.ProductoViewHolder>
    implements View.OnClickListener {

    private ArrayList<TADProducto> productos;

    private View.OnClickListener listener;

    public HolderProducto(ArrayList<TADProducto> productos) {
        this.productos = productos;
    }

    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doble_list_item, parent, false);
        view.setOnClickListener(this);

        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        TADProducto producto = productos.get(position);
        holder.bindProducto(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView item;
        private ImageView image;

        public ProductoViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.txt_doble_item);
            image = itemView.findViewById(R.id.img_doble_item);
        }

        public void bindProducto(TADProducto producto) {
            item.setText(producto.getProducto());
            image.setImageURI(Uri.parse(producto.getPath_imagen()));
        }
    }
}
