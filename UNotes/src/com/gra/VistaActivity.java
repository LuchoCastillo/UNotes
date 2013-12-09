package com.gra;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.annotation.TargetApi;
import android.os.Build;

/**
 * Clase que contiene los elementos gráficos de la actividad Vista Previa.
 * 
 * @author luciano
 */
public class VistaActivity extends Activity {
	
	// VARIABLES DE CLASE
	private String markdown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista);
		// Show the Up button in the action bar.
		setupActionBar();

		Bundle bundle = getIntent().getExtras();
		markdown = bundle.getString("md");
		cargar(bundle.getString("html"));
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista, menu);
		return true;
	}
	
	/**
	 * Método que define que va a pasar cuando se pulsa un item del menú.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		// Cuando se pulsa Vista previa
		case R.id.itGuardarVista:
			itGuardarOnClick();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Método que muestra el código html.
	 * 
	 * @param html código html
	 */
	public void cargar(String html) {
		WebView wvVista = (WebView) findViewById(R.id.wvVistaId);
		wvVista.loadData(html, "text/html", "UTF-8");
	}

	/**
	 * Método que guarda un archivo en la memoria externa.
	 */
	public void itGuardarOnClick() {
		// VARIABLES
		final EditText input = new EditText(this);
		DialogoEntrada builder = new DialogoEntrada(this, "Ingrese el nombre:", input, markdown);

		// Muestro el dialogo
		builder.show();
		
	}
}
