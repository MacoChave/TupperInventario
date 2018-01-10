package com.macochave.tupperinventario.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.dao.DAOCategoria;
import com.macochave.tupperinventario.datos.tad.TADCategoria;

/**
 * Created by marco on 5/12/17.
 */

public class CategoriaDialog extends DialogFragment {

    private TADCategoria categoria;
    
    CategoriaDialogListener listener;

    public interface CategoriaDialogListener {
        void possitiveCategoria(TADCategoria categoria);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.nueva_categoria);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_categoria, null);

        final EditText edtCategoria = view.findViewById(R.id.edtDialog_Categoria);

        builder.setView(view);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (validarTexto(edtCategoria.getText().toString()))
                    Toast.makeText(getContext(), "No admito entradas vacías :)", Toast.LENGTH_SHORT).show();
                else
                    agregar(edtCategoria.getText().toString());
            }
        });
        builder.setView(view);
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CategoriaDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    private void agregar(String s) {
        DAOCategoria daoCategoria = new DAOCategoria(getContext());
        TADCategoria categoria = new TADCategoria(0, s);
        categoria.setId(daoCategoria.obtenerID(categoria));

        if (categoria.getId() > 0)
            daoCategoria.actualizar(categoria);
        else
            daoCategoria.agregar(categoria);

        listener.possitiveCategoria(categoria);
    }

    private boolean validarTexto(String s) {
        return s.matches("( )+") || s.isEmpty();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CategoriaDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
            + " debería implementar CategoriaDialogListener");
        }
    }

    public void setCategoria(TADCategoria categoria)
    {

    }
}
