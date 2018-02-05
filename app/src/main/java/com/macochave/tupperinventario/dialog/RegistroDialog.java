package com.macochave.tupperinventario.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.dao.DAOCategoria;
import com.macochave.tupperinventario.datos.dao.DAOColor;
import com.macochave.tupperinventario.datos.dao.DAOFamilia;
import com.macochave.tupperinventario.datos.dao.DAOProducto;
import com.macochave.tupperinventario.datos.tad.TADCategoria;
import com.macochave.tupperinventario.datos.tad.TADColor;
import com.macochave.tupperinventario.datos.tad.TADFamilia;
import com.macochave.tupperinventario.datos.tad.TADInventario;
import com.macochave.tupperinventario.datos.tad.TADProducto;
import com.macochave.tupperinventario.datos.tad.TADReporte;

public class RegistroDialog extends DialogFragment implements
        CategoriaDialog.CategoriaDialogListener,
        ColorDialog.ColorDialogListener,
        FamiliaDialog.FamiliaDialogListener,
        ProductoDialog.ProductoDialogListener {

    public static final String TAG = "RegistroDialog";

    private RegistroDialogListener listener;

    private TADFamilia familia;
    private TADCategoria categoria;
    private TADColor color;
    private TADProducto producto;
    private TADInventario inventario;
    private TADReporte reporte;

    private EditText text_cantidad;
    private EditText text_precio;
    private EditText text_capacidad;

    private Spinner spinner_familia;
    private Spinner spinner_categoria;
    private Spinner spinner_color;
    private Spinner spinner_producto;
    private Spinner spinner_dimension;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_registro_dialog, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_dialog_registro);
        toolbar.setTitle(R.string.registro);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        Spinner spinner_familia = view.findViewById(R.id.spn_registro_familia);
        Spinner spinner_categoria = view.findViewById(R.id.spn_registro_capacidad);
        Spinner spinner_color = view.findViewById(R.id.spn_registro_color);
        Spinner spinner_producto = view.findViewById(R.id.spn_registro_produto);
        Spinner spinner_dimension;

        Button button_familia = view.findViewById(R.id.btn_registro_familia);
        Button button_categoria = view.findViewById(R.id.btn_registro_categoria);
        Button button_color = view.findViewById(R.id.btn_registro_color);
        Button button_producto = view.findViewById(R.id.btn_registro_producto);

        ImageButton button_aumentar = view.findViewById(R.id.btn_registro_aumentar);
        button_aumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(text_cantidad.getText().toString());
                ++cantidad;
                text_cantidad.setText(cantidad);
            }
        });
        ImageButton button_reducir = view.findViewById(R.id.btn_registro_reducir);
        button_reducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(text_cantidad.getText().toString());
                if (cantidad > 0) {
                    --cantidad;
                    text_cantidad.setText(cantidad);
                }
            }
        });

        EditText text_cantidad = view.findViewById(R.id.edt_registro_cantidad);
        EditText text_precio = view.findViewById(R.id.edt_registro_precio);
        EditText text_capacidad = view.findViewById(R.id.edt_registro_capacidad);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_aceptar) {
            agregar();
            dismiss();
            return true;
        } else if (id == android.R.id.home) {
            dismiss();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RegistroDialogListener)
            listener = (RegistroDialogListener) context;
        else
            throw new RuntimeException(context.toString()
                + " debe implementar RegistroDialogListener");
    }

    private void agregar() {

    }

    private void llenarSpinnerFamilia() {
        DAOFamilia daoFamilia = new DAOFamilia(getContext());
    }

    private void llenarSpinnerCategoria() {
        DAOCategoria daoCategoria = new DAOCategoria(getContext());
    }

    private void llenarSpinnerColor() {
        DAOColor daoColor = new DAOColor(getContext());
    }

    private void llenarSpinnerProducto() {
        DAOProducto daoProducto = new DAOProducto(getContext());
    }

    private void llenarSpinnerCapacidad() {

    }

    public void setRegistro(TADReporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public void possitiveColor(TADColor color) {
        if (color != null)
            inventario.setId_color(color.getId());
    }

    @Override
    public void possitiveFamilia(TADFamilia familia) {
        if (familia != null)
            inventario.setId_familia(familia.getId());
    }

    @Override
    public void possitiveCategoria(TADCategoria categoria) {
        if (categoria != null)
            inventario.setId_categoria(categoria.getId());
    }

    @Override
    public void possitiveProducto(TADProducto producto) {
        if (producto != null)
            inventario.setId_producto(producto.getId());
    }

    public interface RegistroDialogListener {
        void possitiveNuevoRegistro(TADReporte reporte);
    }
}
