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
import com.macochave.tupperinventario.datos.dao.DAOColor;
import com.macochave.tupperinventario.datos.tad.TADColor;

/**
 * Created by marco on 5/12/17.
 */

public class ColorDialog extends DialogFragment {

    private TADColor color;

    ColorDialogListener listener;

    public interface ColorDialogListener {
        void possitiveColor(TADColor color);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.nuevo_color);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_color, null);

        final EditText edtColor = view.findViewById(R.id.edtDialog_Color);

        builder.setView(view);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (validarTexto(edtColor.getText().toString()))
                    Toast.makeText(getContext(), "No admito entradas vacías :)", Toast.LENGTH_SHORT).show();
                else
                    agregar(edtColor.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    private void agregar(String s) {
        DAOColor daoColor = new DAOColor(getContext());
        TADColor color = new TADColor(0, s);
        color.setId(daoColor.obtenerID(color));

        if (color.getId() > 0)
            daoColor.actualizar(color);
        else
            daoColor.agregar(color);

        listener.possitiveColor(color);
    }

    private boolean validarTexto(String s) {
        return s.isEmpty() || s.matches("( )+");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ColorDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
            + " debería implementar ColorDialogListener");
        }
    }

    public void setColor(TADColor color)
    {

    }
}
