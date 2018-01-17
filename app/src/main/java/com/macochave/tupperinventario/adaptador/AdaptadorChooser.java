package com.macochave.tupperinventario.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macochave.tupperinventario.R;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by marco on 12/01/18.
 */

public class AdaptadorChooser extends RecyclerView.Adapter<AdaptadorChooser.ChooserViewHolder>
    implements View.OnClickListener {

    private ArrayList<URI> uris;

    private View.OnClickListener listener;

    public AdaptadorChooser(ArrayList<URI> uris) {
        this.uris = uris;
    }

    public ChooserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_grid_item, parent, false);
        view.setOnClickListener(this);

        return new ChooserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChooserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    static class ChooserViewHolder extends RecyclerView.ViewHolder {

        public ChooserViewHolder(View itemView) {
            super(itemView);
        }
    }
}
