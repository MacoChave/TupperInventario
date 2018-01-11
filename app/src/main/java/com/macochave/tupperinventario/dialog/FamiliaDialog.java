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
import com.macochave.tupperinventario.datos.dao.DAOFamilia;
import com.macochave.tupperinventario.datos.tad.TADFamilia;

/**
 * Created by marco on 3/12/17.
 */

public class FamiliaDialog extends DialogFragment {

    FamiliaDialogListener listener;
    private TADFamilia familia;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.nueva_familia);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_familia, null);

        final EditText edtFamilia = view.findViewById(R.id.edtDialog_Familia);
        if (familia != null)
            edtFamilia.setText(familia.getFamilia());
        else
            familia = new TADFamilia();

        builder.setView(view);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (validarTexto(edtFamilia.getText().toString()))
                    Toast.makeText(getContext(), "No admito entradas vacías :)", Toast.LENGTH_SHORT).show();
                else
                    agregar(edtFamilia.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FamiliaDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    private boolean validarTexto(String s) {
        return s.matches("( )+") || s.isEmpty();
    }

    private void agregar(String s) {
        DAOFamilia daoFamilia = new DAOFamilia(getContext());
        familia.setFamilia(s);

        if (familia.getId() > 0)
            daoFamilia.actualizar(familia);
        else
            daoFamilia.agregar(familia);

        listener.possitiveFamilia(familia);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (FamiliaDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " debería implementar FamiliaDialogListener");
        }

    }

    public void setFamilia(TADFamilia familia) {
        this.familia = familia;
    }

    public interface FamiliaDialogListener {
        void possitiveFamilia(TADFamilia familia);
    }
}
