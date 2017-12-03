package com.macochave.tupperinventario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtExistencia, edtPrecio, edtCapacidad;
    ImageView imgImagen;
    Spinner spnFamilia, spnCategoria, spnProducto, spnDimensional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        spnFamilia = findViewById(R.id.agregar_spnFamilia);
        spnCategoria = findViewById(R.id.agregar_spnCategoria);
        spnProducto = findViewById(R.id.agregar_spnProducto);
        spnDimensional = findViewById(R.id.agregar_spnDimensional);

        setItemsSpinner();

        spnFamilia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnDimensional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AgregarActivity.this, (CharSequence) adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtExistencia = findViewById(R.id.agregar_edtExistencia);
        edtCapacidad = findViewById(R.id.agregar_edtCapacidad);
        edtPrecio = findViewById(R.id.agregar_edtPrecio);

        Button familia = findViewById(R.id.agregar_btnFamilia);
        Button categoria = findViewById(R.id.agregar_btnCategoria);
        Button producto = findViewById(R.id.agregar_btnProducto);
        ImageButton imagen = findViewById(R.id.agregar_btnImagen);
        ImageButton sumar = findViewById(R.id.agregar_btnAumentarExistencia);
        ImageButton restar = findViewById(R.id.agregar_btnRestarExistencia);

        familia.setOnClickListener(this);
        categoria.setOnClickListener(this);
        producto.setOnClickListener(this);
        imagen.setOnClickListener(this);
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
    }

    private void setItemsSpinner() {
        /* SPINNER FAMILIA */
        /* SPINNER CATEGORIA */
        /* SPINNER PRODUCTO */
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregar_btnFamilia:
                break;
            case R.id.agregar_btnCategoria:
                break;
            case R.id.agregar_btnProducto:
                break;
            case R.id.agregar_btnAumentarExistencia:
                break;
            case R.id.agregar_btnRestarExistencia:
                break;
            case R.id.agregar_btnImagen:
                break;
            case R.id.btnAgregar:
                break;
            default:
                break;
        }
    }

}
