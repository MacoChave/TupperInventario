package com.macochave.tupperinventario.gestion;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.holder.HolderProducto;
import com.macochave.tupperinventario.datos.dao.DAOProducto;
import com.macochave.tupperinventario.datos.tad.TADProducto;
import com.macochave.tupperinventario.dialog.ProductoDialog;

import java.util.ArrayList;

public class ProductoActivity extends AppCompatActivity
    implements ProductoDialog.ProductoDialogListener {

    private RecyclerView listView;
    private ArrayList<TADProducto> productos;
    private DAOProducto daoProducto;
    private HolderProducto holderProducto;
    private TADProducto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Toolbar toolbar = findViewById(R.id.toolbar_producto);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout_producto);
        toolbarLayout.setTitle("Gestión de producto");

        FloatingActionButton fab = findViewById(R.id.fab_producto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto = new TADProducto();
                nuevoProducto();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.lst_producto);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        listView.setItemAnimator(new DefaultItemAnimator());

        daoProducto = new DAOProducto(getApplicationContext());

        llenarLista();
    }

    private void llenarLista() {
        productos = daoProducto.seleccionarTodo();
        holderProducto = new HolderProducto(productos);
        listView.setAdapter(holderProducto);

        holderProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto = productos.get(listView.getChildAdapterPosition(view));
                nuevoProducto();
            }
        });
    }

    private void limpiarLista() {
        productos.clear();
        holderProducto = null;
        listView.setAdapter(null);
    }

    private void nuevoProducto() {
        ProductoDialog dialog = new ProductoDialog();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        dialog.setProducto(producto);
        dialog.show(transaction, ProductoDialog.TAG);
    }

    @Override
    public void possitiveProducto(TADProducto producto) {
        if (producto != null) {
            limpiarLista();
            llenarLista();
        }
    }
}
