package com.macochave.tupperinventario.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.macochave.tupperinventario.R;
import com.macochave.tupperinventario.datos.tad.TADProducto;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class ProductoDialog extends DialogFragment {

    private static String APP_DIRECTORY = "TupperInventario/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    public static final String TAG = "ProductoDialog";
    private static final int PICK_IMAGE_REQUEST = 1;

    private ProductoDialogListener listener;

    private TADProducto producto;
    private Uri imageUri;
    private String path_camera;

    private EditText nombreProducto;
    private ImageView imagenProducto;

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

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_producto_dialog, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_dialog_producto);
        toolbar.setTitle(R.string.gestion_producto);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        nombreProducto = view.findViewById(R.id.edt_dialog_producto);
        imagenProducto = view.findViewById(R.id.img_dialog_producto);
        ImageButton buscarImagen = view.findViewById(R.id.btn_dialog_producto);

        buscarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSelector();
            }
        });

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
            Toast.makeText(getContext(), "Aceptar", Toast.LENGTH_SHORT).show();
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

        if (context instanceof ProductoDialogListener) {
            listener = (ProductoDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debe implementar ProductoDialogListener");
        }
    }

    public void setProducto(TADProducto producto) {
        this.producto = producto;
    }

    private void abrirSelector() {
        if (!verificarPermisos())
            return;

        final CharSequence[] items = {"Tomar foto", "Elegir galeria"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.seleccion_opcion);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        abrirCamara();
                        break;
                    case 1:
                        abrirGaleria();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.show();
    }

    private boolean verificarPermisos() {

        int permission_write = ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        int permission_camera = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);

        if (permission_write == PackageManager.PERMISSION_GRANTED
                && permission_camera == PackageManager.PERMISSION_GRANTED)
            return true;

        // Should we show an explanation?
        if (getActivity().shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || getActivity().shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    MY_PERMISSIONS
            );
        } else {
            // No explanation needed, we can request the permission.
            requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    MY_PERMISSIONS
            );
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                Snackbar.make(getView(), "Permisos aceptados", Snackbar.LENGTH_SHORT);
        }
        else
            Snackbar.make(getView(), "Debería tener permisos para acceder a galería", Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("file_path", path_camera);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        path_camera = savedInstanceState != null ? savedInstanceState.getString("file_path") : null;
    }

    private void abrirGaleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_CODE:
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    imagenProducto.setImageBitmap(bitmap);
                    break;
                case SELECT_PICTURE:
                    if (data != null && data.getData() != null) {
                        imageUri = data.getData();
                        imagenProducto.setImageURI(imageUri);
                        Toast.makeText(getContext(), "Path: " + imageUri.getPath(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), getFileExtension(imageUri), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Log.d("CHOOSE FILE", "onActivityResult: No hay data");
                    break;
                default:
                    break;
            }
        }
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver resolver;
        resolver = getActivity().getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();

        return map.getExtensionFromMimeType(resolver.getType(imageUri));
    }

    public interface ProductoDialogListener {
        void possitiveProducto(TADProducto producto);
    }
}
