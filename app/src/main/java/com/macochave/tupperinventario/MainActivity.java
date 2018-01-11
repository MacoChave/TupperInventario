package com.macochave.tupperinventario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.macochave.tupperinventario.datos.tad.TADCategoria;
import com.macochave.tupperinventario.datos.tad.TADColor;
import com.macochave.tupperinventario.datos.tad.TADFamilia;
import com.macochave.tupperinventario.datos.tad.TADReporte;
import com.macochave.tupperinventario.dialog.CategoriaDialog;
import com.macochave.tupperinventario.dialog.ColorDialog;
import com.macochave.tupperinventario.dialog.FamiliaDialog;
import com.macochave.tupperinventario.dialog.NuevoRegistro;
import com.macochave.tupperinventario.gestion.CategoriaActivity;
import com.macochave.tupperinventario.gestion.FamiliaActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void nuevoRegistro() {
        NuevoRegistro dialog = new NuevoRegistro();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        dialog.show(transaction, NuevoRegistro.TAG);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tienda:
                Toast.makeText(this, "Próximamente una tiendita :)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_actualizar:
                Toast.makeText(this, "Aún no puedo verificar actualizaciones :(", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_ayuda:
                Toast.makeText(this, "Ayuda en construcción :D", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_acerca:
                Toast.makeText(this, "TupperInventario - " + BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_reporte:
                break;
            case R.id.nav_familia:
                intent = new Intent(MainActivity.this, FamiliaActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_categoria:
                intent = new Intent(MainActivity.this, CategoriaActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_producto:
                break;
            case R.id.nav_color:
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
