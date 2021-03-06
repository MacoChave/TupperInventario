package com.macochave.tupperinventario.gestion;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.holder.HolderFamilia;
import com.macochave.tupperinventario.datos.dao.DAOFamilia;
import com.macochave.tupperinventario.datos.tad.TADFamilia;
import com.macochave.tupperinventario.dialog.FamiliaDialog;

import java.util.ArrayList;

public class FamiliaActivity extends AppCompatActivity
        implements FamiliaDialog.FamiliaDialogListener {

    private RecyclerView listView;
    private ArrayList<TADFamilia> familias;
    private DAOFamilia daoFamilia;
    private HolderFamilia holderFamilia;
    private TADFamilia familia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);
        Toolbar toolbar = findViewById(R.id.toolbar_familia);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout_familia);
        toolbarLayout.setTitle("Gestión de Familia");

        FloatingActionButton fab = findViewById(R.id.fab_familia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                familia = new TADFamilia();
                nuevaFamilia();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.lst_familia);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listView.setItemAnimator(new DefaultItemAnimator());

        daoFamilia = new DAOFamilia(getApplicationContext());

        llenarLista();
    }

    private void llenarLista() {
        familias = daoFamilia.seleccionarTodo();
        holderFamilia = new HolderFamilia(familias);
        listView.setAdapter(holderFamilia);

        holderFamilia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                familia = familias.get(listView.getChildAdapterPosition(view));
                nuevaFamilia();
            }
        });
    }

    private void limpiarLista() {
        familias.clear();
        holderFamilia = null;
        listView.setAdapter(null);
    }

    private void nuevaFamilia() {
        FamiliaDialog familiaDialog = new FamiliaDialog();
        familiaDialog.show(getSupportFragmentManager(), "Familia");
        familiaDialog.setFamilia(familia);
    }

    @Override
    public void possitiveFamilia(TADFamilia familia) {
        if (familia != null) {
            limpiarLista();
            llenarLista();

            Toast.makeText(this, "Acción realizada con éxito :)", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Hubo algún problema :/", Toast.LENGTH_SHORT).show();
    }

}
