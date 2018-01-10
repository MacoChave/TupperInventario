package com.macochave.tupperinventario.gestion;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.adaptador.AdaptadorFamilia;
import com.macochave.tupperinventario.datos.Contrato;
import com.macochave.tupperinventario.datos.DBManager;
import com.macochave.tupperinventario.datos.dao.DAOFamilia;
import com.macochave.tupperinventario.datos.tad.TADFamilia;
import com.macochave.tupperinventario.dialog.FamiliaDialog;

import java.util.ArrayList;

public class FamiliaActivity extends AppCompatActivity implements FamiliaDialog.FamiliaDialogListener {

    private ListView listView;
    private ArrayList<TADFamilia> familias;
    private DAOFamilia daoFamilia;
    private AdaptadorFamilia adaptadorFamilia;
    private TADFamilia familia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);
        Toolbar toolbar = findViewById(R.id.toolbar_familia);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout_familia);
        collapsingToolbarLayout.setTitle("Gestión Familia");

        FloatingActionButton fab = findViewById(R.id.fab_familia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaFamilia();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.lst_familia);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                familia = (TADFamilia) adaptadorFamilia.getItem(i);
                nuevaFamilia();
            }
        });

        daoFamilia = new DAOFamilia(getApplicationContext());

        llenarLista();
    }

    private void llenarLista() {
        familias = daoFamilia.seleccionarTodo();
        adaptadorFamilia = new AdaptadorFamilia(familias, getApplicationContext());
        listView.setAdapter(adaptadorFamilia);
    }

    private void limpiarLista() {
        familias.clear();
        adaptadorFamilia = null;
        listView.setAdapter(adaptadorFamilia);
    }

    private void nuevaFamilia() {
        FamiliaDialog familiaDialog = new FamiliaDialog();
        familiaDialog.show(getSupportFragmentManager(), "Familia");
        familiaDialog.setFamilia(familia);
    }

    @Override
    public void possitiveFamilia(TADFamilia familia) {
        if (familia != null)
        {
            limpiarLista();
            llenarLista();
        }
    }
}
