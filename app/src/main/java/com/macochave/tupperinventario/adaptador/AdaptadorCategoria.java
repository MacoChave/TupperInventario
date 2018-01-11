package com.macochave.tupperinventario.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADCategoria;

import java.util.ArrayList;


/**
 * Created by marco on 10/01/18.
 */

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriaViewHolder>
    implements View.OnClickListener {

    private ArrayList<TADCategoria> categorias;

    private View.OnClickListener listener;

    public AdaptadorCategoria(ArrayList<TADCategoria> categorias) {
        this.categorias = categorias;
    }

    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);

        view.setOnClickListener(this);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        TADCategoria categoria = categorias.get(position);
        holder.bindCategoria(categoria);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {

        private TextView item;

        CategoriaViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.txt_simple_item);
        }

        void bindCategoria(TADCategoria categoria) {
            item.setText(categoria.getCategoria());
        }
    }
}
