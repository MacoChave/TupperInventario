package com.macochave.tupperinventario.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADFamilia;

import java.util.ArrayList;

/**
 * Created by marco on 9/01/18.
 */

public class HolderFamilia extends RecyclerView.Adapter<HolderFamilia.FamiliaViewHolder>
        implements View.OnClickListener {

    private ArrayList<TADFamilia> familias;

    private View.OnClickListener listener;

    public HolderFamilia(ArrayList<TADFamilia> familias) {
        this.familias = familias;
    }

    @Override
    public FamiliaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);

        view.setOnClickListener(this);

        return new FamiliaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FamiliaViewHolder holder, int position) {
        TADFamilia familia = familias.get(position);
        holder.bindFamilia(familia);
    }

    @Override
    public int getItemCount() {
        return familias.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    static class FamiliaViewHolder extends RecyclerView.ViewHolder {

        private TextView item;

        FamiliaViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.txt_simple_item);
        }

        void bindFamilia(TADFamilia familia) {
            item.setText(familia.getFamilia());
        }

    }

    /*
    private Context context;

    public HolderFamilia(ArrayList<TADFamilia> familias, Context context) {
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
        View v = inflater.inflate(R.layout.simple_list_item, viewGroup, false);

        TextView familia = v.findViewById(R.id.txt_familia);
        familia.setText(familias.get(i).getFamilia());

        return v;
    }
*/
}
