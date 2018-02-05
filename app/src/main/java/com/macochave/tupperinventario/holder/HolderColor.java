package com.macochave.tupperinventario.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADColor;

import java.util.ArrayList;

/**
 * Created by marco on 10/01/18.
 */

public class HolderColor extends RecyclerView.Adapter<HolderColor.ColorViewHolder>
    implements View.OnClickListener {

    private ArrayList<TADColor> colors;

    private View.OnClickListener listener;

    public HolderColor(ArrayList<TADColor> colors) {
        this.colors = colors;
    }

    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);
        view.setOnClickListener(this);

        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        TADColor color = colors.get(position);
        holder.bindColor(color);
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    static class ColorViewHolder extends RecyclerView.ViewHolder {

        private TextView item;

        ColorViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.txt_simple_item);
        }

        void bindColor(TADColor color) {
            item.setText(color.getColor());
        }
    }
}
