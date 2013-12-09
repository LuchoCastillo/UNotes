package com.cla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Clase encargada de las funciones propias de un editor de texto sencillo.
 *
 * @author luciano
 */
public class Editor {
    
    /*
     * Constructor de la clase
     */
    public Editor() {
    }
    
    /**
     * Método que lee un archivo de la memoria externa y devuelve su contenido.
     *
     * @param archivo archivo a leer
     * @return contenido del archivo
     */
    public static String leer(File archivo) {
    	// VARIABLES
        String contenido = "";
        String linea;
        BufferedReader lector;
        
        try {
			lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
			linea = lector.readLine();
			while (linea != null) {
        		contenido += linea + "\n";
        		linea = lector.readLine();
			}
			
			lector.close();
			
			// Devuelve el contenido
			return contenido;
			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Método que escribe un archivo en la memoria externa, recibiendo su futuro contenido.
     * 
     * @param archivo archivo a escribir
     * @param contenido futuro contenido del archivo
     * @return valor booleano que indica si la operación se realizó con éxito
     */
    public static boolean escribir(File archivo, String contenido) {
    	// VARIABLES
    	OutputStreamWriter fout;
    	
    	try {
    		fout = new OutputStreamWriter(new FileOutputStream(archivo));
    		fout.write(contenido);
    		fout.flush();
    		fout.close();
    		
    		// Devuelve un valor positivo
    		return true;
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		// Devuelve un valor negativo
    		return false;
    	}
    }
}
