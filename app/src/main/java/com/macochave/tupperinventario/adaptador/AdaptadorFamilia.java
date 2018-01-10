package com.macochave.tupperinventario.adaptador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADFamilia;

import java.util.ArrayList;

/**
 * Created by marco on 9/01/18.
 */

public class AdaptadorFamilia extends BaseAdapter {

    private ArrayList<TADFamilia> familias;
    private Context context;

    public AdaptadorFamilia(ArrayList<TADFamilia> familias, Context context) {
        this.familias = familias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return familias.size();
    }

    @Override
    public Object getItem(int i) {
        return familias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return familias.get(i).getId();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View v = inflater.inflate(R.layout.item_familia, viewGroup, false);

        TextView familia = v.findViewById(R.id.txt_familia);
        familia.setText(familias.get(i).getFamilia());

        return v;
    }
}
