package com.cla;

import android.os.Environment;

/**
 * Clase que contiene variables constantes utilizadas en otras partes del programa.
 * 
 * @author luciano
 */
public class Constantes {
	
	// VARIABLES DE CLASE
	public final static String carpeta_app = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/UNotes");
	
}
