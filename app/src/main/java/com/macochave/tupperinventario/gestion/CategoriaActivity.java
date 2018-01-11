package com.macochave.tupperinventario.gestion;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.adaptador.AdaptadorCategoria;
import com.macochave.tupperinventario.datos.dao.DAOCategoria;
import com.macochave.tupperinventario.datos.tad.TADCategoria;
import com.macochave.tupperinventario.dialog.CategoriaDialog;

import java.util.ArrayList;

public class CategoriaActivity extends AppCompatActivity
    implements CategoriaDialog.CategoriaDialogListener{

    private RecyclerView listView;
    private ArrayList<TADCategoria> categorias;
    private DAOCategoria daoCategoria;
    private AdaptadorCategoria adaptadorCategoria;
    private TADCategoria categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar_categoria);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout_categoria);
        toolbarLayout.setTitle("Gestión Categoria");

        FloatingActionButton fab = findViewById(R.id.fab_categoria);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria = new TADCategoria();
                nuevaCategoria();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.lst_categoria);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        listView.setItemAnimator(new DefaultItemAnimator());

        daoCategoria = new DAOCategoria(getApplicationContext());

        llenarLista();
    }

    private void llenarLista() {
        categorias = daoCategoria.seleccionarTodo();
        adaptadorCategoria = new AdaptadorCategoria(categorias);
        listView.setAdapter(adaptadorCategoria);

        adaptadorCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria = categorias.get(listView.getChildAdapterPosition(view));
                nuevaCategoria();
            }
        });

    }

    private void vaciarLista() {
        categorias.clear();
        adaptadorCategoria = null;
        listView.setAdapter(null);
    }

    private void nuevaCategoria() {
        CategoriaDialog categoriaDialog = new CategoriaDialog();
        categoriaDialog.show(getSupportFragmentManager(), "Categoria");
        categoriaDialog.setCategoria(categoria);
    }

    @Override
    public void possitiveCategoria(TADCategoria categoria) {
        vaciarLista();
        llenarLista();
    }
}
