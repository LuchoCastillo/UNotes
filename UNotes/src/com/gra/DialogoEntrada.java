package com.gra;

import java.io.File;

import com.cla.Constantes;
import com.cla.Editor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class DialogoEntrada extends AlertDialog.Builder {
	
	public DialogoEntrada(final Context cont, String title, final EditText view, final String contenido) {
		super(cont);
		this.setTitle(title);
		this.setView(view);
		this.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {
				// Tomo el texto ingresado
				String nom_archivo = view.getText().toString();

				// Creo un directorio de la app
				File archivo = new File(Constantes.carpeta_app);

				// Si la carpeta no existe, la crea
				if(!archivo.exists()) {
					archivo.mkdirs();
				}

				// Creo un archivo con el nombre ingresado, en la ruta de la carpeta de la app
				File file = new File(Constantes.carpeta_app, nom_archivo);

				// Si se escribe con exito
				if (Editor.escribir(file, contenido)) {
					// Informo de la creación exitosa del archivo
					Toast.makeText(cont, "Archivo guardado", Toast.LENGTH_SHORT).show();

				// Sino
				} else {
					// Informo de un error en la creación del archivo
					Toast.makeText(cont, "No se pudo guardar el archivo", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		this.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {
				// Cierro el dialogo
				dialog.cancel();
			}
		});
	}
}