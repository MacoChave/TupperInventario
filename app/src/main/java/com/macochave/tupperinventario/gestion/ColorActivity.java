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
import com.macochave.tupperinventario.holder.HolderColor;
import com.macochave.tupperinventario.datos.dao.DAOColor;
import com.macochave.tupperinventario.datos.tad.TADColor;
import com.macochave.tupperinventario.dialog.ColorDialog;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity
    implements ColorDialog.ColorDialogListener {

    private RecyclerView listView;
    private ArrayList<TADColor> colors;
    private DAOColor daoColor;
    private HolderColor holderColor;
    private TADColor color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        Toolbar toolbar = findViewById(R.id.toolbar_color);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout_color);
        toolbarLayout.setTitle("Gestión de Color");

        FloatingActionButton fab = findViewById(R.id.fab_color);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = new TADColor();
                nuevoColor();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.lst_color);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        listView.setItemAnimator(new DefaultItemAnimator());

        daoColor = new DAOColor(getApplicationContext());

        llenarLista();
    }

    private void llenarLista() {
        colors = daoColor.seleccionarTodo();
        holderColor = new HolderColor(colors);
        listView.setAdapter(holderColor);

        holderColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = colors.get(listView.getChildAdapterPosition(view));
                nuevoColor();
            }
        });
    }

    private void limpiarLista() {
        colors.clear();
        holderColor = null;
        listView.setAdapter(null);
    }

    private void nuevoColor() {
        ColorDialog colorDialog = new ColorDialog();
        colorDialog.show(getSupportFragmentManager(), "Color");
        colorDialog.setColor(color);
    }

    @Override
    public void possitiveColor(TADColor color) {
        if (color != null) {
            limpiarLista();
            llenarLista();

            Toast.makeText(this, "Acción realizada con éxito :)", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Hubo algún problema :/", Toast.LENGTH_SHORT).show();
    }
}
