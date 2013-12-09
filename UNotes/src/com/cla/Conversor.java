package com.cla;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.OutputStream;

import org.markdown4j.Markdown4jProcessor;
//import org.xhtmlrenderer.pdf.ITextRenderer;

//import com.lowagie.text.DocumentException;

import cz.kebrt.html2latex.Main;

/**
 * Clase encargada de las conversiones de código markdown a otros formatos.
 * 
 * @author luciano
 */
public class Conversor {
	
	private static Markdown4jProcessor md4j = new Markdown4jProcessor();
	
	/**
     * Método que toma un texto en markdown y lo transforma a su equivalente
     * html.
     *
     * @param markdown código en markdown
     * @return código en html
     */
    public static String aHtml(String markdown) {
        // VARIABLES
        String html = null;

        try {
            // Transformo el codigo markdown en html
            html = md4j.process(markdown);

        } catch (Exception ex) {

        }

        // Devuelve el codigo en html
        return html;
    }
//    
//    /**
//     * Método que toma una ruta y un texto en html y genera un pdf con formato
//     * en dicha ruta.
//     *
//     * @param ruta
//     * @param html
//     * @return
//     */
//    public boolean aPdf(String ruta, String html) {
//        // variables
//        OutputStream os;
//        ITextRenderer renderer = new ITextRenderer();
//
//        try {
//            // creo el archivo
//            os = new FileOutputStream(ruta);
//
//            try {
//                // proceso el html y escribo el archivo
//                renderer.setDocumentFromString(completarHtml(html));
//                renderer.layout();
//                renderer.createPDF(os);
//
//                // si se realiza con exito, devuelvo "true"
//                return true;
//
//            } catch (DocumentException ex) {
//
//                // si hubo una excepcion, devuelvo "false"
//                return false;
//            }
//
//        } catch (FileNotFoundException ex) {
//
//            // si hubo una excepcion, devuelvo "false"
//            return false;
//        }
//
//    }
//    
//    /**
//     * Método que completa un código html con las etiquetas iniciales y finales
//     * de todo documento html.
//     *
//     * @param html
//     * @return
//     */
//    private String completarHtml(String html) {
//        // completo las etiquetas iniciales y finales
//        html = "<html><head></head><body>" + html + "</body></html>";
//
//        // devuelvo el codigo completo
//        return html;
//    }
//
    /**
     * Método que toma la ruta de un archivo html y otra ruta donde se genera su
     * equivalente en latex.
     *
     * @param in ruta del archivo html
     * @param out destino del archivo latex
     * @return valor booleano que indica se se realizó con éxito
     */
    public static boolean aLatex(String in, String out) {
        try {
            // transforma el codigo markdown a codigo latex
            Main.transformar(in, out);

            // si se realiza con exito, devuelvo "true"
            return true;

        } catch (Exception ex) {
            // si hubo una excepcion, devuelvo "false"
            return false;
        }
    }
}