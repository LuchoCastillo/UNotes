package com.gra;

import java.io.File;

import com.cla.Constantes;
import com.cla.Conversor;
import com.cla.Editor;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Clase principal, contiene la actividad principal con todos su elementos.
 * 
 * @author luciano
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Método que define que va a pasar cuando se pulsa un item del menú.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		// Cuando se pulsa Vista previa
		case R.id.itVista:
			itVistaOnClick();
			return true;

		// Cuando se pulsa Guardar
		case R.id.itAbrir:
			itAbrirOnClick();
			return true;
			
		// Cuando se pulsa Guardar
		case R.id.itGuardar:
			itGuardarOnClick();
			return true;
			
		// Cuando se pulsa Exportar a Pdf
		case R.id.itPdf:
			itPdfOnClick();
			return true;
		
		// Cuando se pulsa Exportar a Html
		case R.id.itHtml:
			itHtmlOnClick();
			return true;
		
		// Cuando se pulsa Exportar a Latex
		case R.id.itLatex:
			itLatexOnClick();
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Método que abre una nueva actividad y muestra el html correspondiente al código markdown.
	 */
	public void itVistaOnClick() {
		// VARIABLES
        String html = null;
        EditText edtContenido = (EditText) findViewById(R.id.edtContenidoId);
        String markdown = edtContenido.getText().toString();
        Intent vista = new Intent(this, VistaActivity.class);

        try {        	
            // Transformo el codigo markdown en html
        	html = Conversor.aHtml(markdown);
        	// Muestro el texto en html
        	vista.putExtra("html", html);
        	vista.putExtra("md", markdown);
        	startActivity(vista);

        } catch (Exception ex) {
        	Log.i("Boton", ex.toString());
        }
	}
	
	/**
	 * Método que guarda un archivo en la memoria externa.
	 */
	public void itGuardarOnClick() {
		// VARIABLES
		final EditText input = new EditText(this);
		final String contenido = ((EditText) findViewById(R.id.edtContenidoId)).getText().toString();
		DialogoEntrada builder = new DialogoEntrada(this, "Ingrese el nombre:", input, contenido);

		// Muestro el dialogo
		builder.show();
		
	}
	
	/**
	 * Método que abre un archivo de la memoria externa.
	 */
	public void itAbrirOnClick() {
		// VARIABLES
		final EditText input = new EditText(this);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Ingrese el nombre:");
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {
				// Tomo el texto ingresado
				String nom_archivo = input.getText().toString();

				// Creo un archivo con el nombre ingresado, en la ruta de la carpeta de la app
				File file = new File(Constantes.carpeta_app, nom_archivo);
				
				if (file.exists()) {
					// Leo el archivo
					String contenido = Editor.leer(file);
					
					EditText edtContenido = (EditText) findViewById(R.id.edtContenidoId);
					
					// Muestro el contenido
					edtContenido.setText(contenido);
				}
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {
				// Cierro el dialogo
				dialog.cancel();
			}
		});
		
		// Muestro el dialogo
		builder.show();
	}

	/**
	 * Método que genera un pdf en base al código markdown.
	 */
	public void itPdfOnClick() {
		Toast toast = Toast.makeText(this, "Pdf", Toast.LENGTH_LONG);
		toast.show();
	}
	
	/**
	 * Método que genera un html en base al código markdown.
	 */
	public void itHtmlOnClick() {
		// VARIABLES
		final EditText input = new EditText(this);
		String markdown = ((EditText) findViewById(R.id.edtContenidoId)).getText().toString();
		final String html;
		
		try {        	
            // Transformo el codigo markdown en html
        	html = Conversor.aHtml(markdown);
        	
        	DialogoEntrada builder = new DialogoEntrada(this, "Ingrese el nombre:", input, html);

    		// Muestro el dialogo
    		builder.show();

        } catch (Exception ex) {
        	Log.i("Boton", ex.toString());
        }
	}
	
	/**
	 * Método que genera un latex en base al código markdown.
	 */
	public void itLatexOnClick() {
		// VARIABLES
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText view = new EditText(this);
		view.setHint("archivo.html/archivo.tex");
		builder.setTitle("Ingrese los nombres:");
		builder.setView(view);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {	
				try {
					String[] vec_rutas = view.getText().toString().replace(" ", "").split("/");
					String html = vec_rutas[0];
					String latex = vec_rutas[1];
					
					// Creo un archivo con el nombre ingresado, en la ruta de la carpeta de la app
					File file_html = new File(Constantes.carpeta_app, html);
					
					Editor.escribir(file_html, Conversor.aHtml(((EditText) findViewById(R.id.edtContenidoId)).getText().toString()));
					Conversor.aLatex(file_html.getAbsolutePath(), Constantes.carpeta_app.concat(latex));
				} catch (Exception ex) {
				}
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			// Defino que va a pasar al pulsar el boton
			public void onClick(DialogInterface dialog, int which) {
				// Cierro el dialogo
				dialog.cancel();
			}
		});
		
		Toast.makeText(this, "Debe crear un Html primero", Toast.LENGTH_LONG).show();
		
		// Muestro el dialogo
		builder.show();
	}
	
	/**
	 * Método que inicia una sincronización con Dropbox
	 */
	public void itDropboxOnClick() {
		String APP_KEY = "c1illq8avtm6347";
		String APP_SECRET = "qzp0upzhbm0pvc6";
		AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
		
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
		
		DropboxAPI<AndroidAuthSession> mDBApi = new DropboxAPI<AndroidAuthSession>(session);
		
		mDBApi.getSession().startAuthentication(this);
	}
}
