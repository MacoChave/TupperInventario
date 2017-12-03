package com.macochave.tupperinventario;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private LinearLayout layoutFamilia, layoutCategoria, layoutColor, layoutProducto, layoutRegistro;
    private boolean abierto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        FloatingActionButton fabFamilia = findViewById(R.id.fabFamilia);
        FloatingActionButton fabCategoria = findViewById(R.id.fabCategoria);
        FloatingActionButton fabColor = findViewById(R.id.fabColor);
        FloatingActionButton fabProducto = findViewById(R.id.fabProducto);
        FloatingActionButton fabAgregar = findViewById(R.id.fabAgregar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (abierto)
                {
                    animacionAbrir();
                    abierto = !abierto;
                }
                else
                {
                    animacionCerrar();
                    abierto = !abierto;
                }
            }
        });
        fabFamilia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionCerrar();
                accion(0);
                abierto = !abierto;
            }
        });
        fabCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionCerrar();
                accion(1);
                abierto = !abierto;
            }
        });
        fabColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionCerrar();
                accion(2);
                abierto = !abierto;
            }
        });
        fabProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionCerrar();
                accion(3);
                abierto = !abierto;
            }
        });
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionCerrar();
                accion(4);
                abierto = !abierto;
            }
        });

        layoutFamilia = findViewById(R.id.layoutFamilia);
        layoutCategoria = findViewById(R.id.layoutCategoria);
        layoutColor = findViewById(R.id.layoutColor);
        layoutProducto = findViewById(R.id.layoutProducto);
        layoutRegistro = findViewById(R.id.layoutRegistro);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void accion(int i) {
        final AlertDialog.Builder builder;
        final AlertDialog dialog;
        View viewDialog;
        switch (i)
        {
            case 0: // FAMILIA
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Aquí se ingresarán las familias");
                builder.setTitle("Agregar familias");

                dialog = builder.create();
                dialog.show();
                break;
            case 1: // CATEGORIA
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Aquí se ingresarán las categorias");
                builder.setTitle("Agregar Categoria");

                dialog = builder.create();
                dialog.show();
                break;
            case 2: // COLOR
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Aquí se ingresarán los colores");
                builder.setTitle("Agregar Color");

                dialog = builder.create();
                dialog.show();
                break;
            case 3: // PRODUCTO
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Aquí se ingresarán los productos");
                builder.setTitle("Agregar Producto");

                dialog = builder.create();
                dialog.show();
                break;
            case 4: // REGISTRO
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Aquí se ingresarán los registros");
                builder.setTitle("Agregar Registro");

                dialog = builder.create();
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void animacionAbrir() {
        Animation rotar = AnimationUtils.loadAnimation(this, R.anim.rotar_antihorario);
        Animation abrir = AnimationUtils.loadAnimation(this, R.anim.fab_abrir);

        fab.startAnimation(rotar);
        layoutFamilia.startAnimation(abrir);
        layoutCategoria.startAnimation(abrir);
        layoutColor.startAnimation(abrir);
        layoutProducto.startAnimation(abrir);
        layoutRegistro.startAnimation(abrir);
    }

    private void animacionCerrar() {
        Animation rotar = AnimationUtils.loadAnimation(this, R.anim.rotar_horario);
        Animation cerrar = AnimationUtils.loadAnimation(this, R.anim.fab_cerrar);

        fab.startAnimation(rotar);
        layoutFamilia.startAnimation(cerrar);
        layoutCategoria.startAnimation(cerrar);
        layoutColor.startAnimation(cerrar);
        layoutProducto.startAnimation(cerrar);
        layoutRegistro.startAnimation(cerrar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            if (abierto)
            {
                animacionCerrar();
                abierto = !abierto;
            }
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
            case R.id.action_ayuda:
                break;
            case R.id.action_actualizar:
                break;
            case R.id.action_info:
                Toast.makeText(this,"TupperInventario - " + BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_reporte:
                break;
            case R.id.nav_familia:
                break;
            case R.id.nav_categoria:
                break;
            case R.id.nav_producto:
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
