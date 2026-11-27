package io.github.brunoonatejim.modelo;
import java.util.ArrayList;
import java.util.List;

public class PreparadorCSV {
    private static final String SEPARADOR = ",";
    private static final char COMILLA = '"';

    public static String escaparNombre(String nombre){
        if(nombre == null){
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        boolean ocupaComillas = nombre.contains(SEPARADOR) 
        || nombre.contains(String.valueOf(COMILLA)) 
        || nombre.contains("\n") 
        || nombre.contains("\r");

        String escapado = nombre.replace(String.valueOf(COMILLA), String.valueOf(COMILLA).repeat(2));
        return ocupaComillas ? String.valueOf(COMILLA) +escapado+String.valueOf(COMILLA) : escapado;
    }

    public static List<String> separarColumnas(String linea){
        if(linea == null){
            throw new IllegalArgumentException("No se aceptan lineas nulas");
        }
        List<String> campos = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean entreComillas = false;

        for(int i = 0; i < linea.length();i++){
            char c = linea.charAt(i);
            if(c == COMILLA){
                if(entreComillas && i+1 < linea.length() && linea.charAt(i+1) == COMILLA){
                    actual.append(COMILLA);
                    i++;
                }else{
                    entreComillas = !entreComillas;
                }
            }else if(c == SEPARADOR.charAt(0) && !entreComillas){
                campos.add(actual.toString());
                actual.setLength(0);
            }else{
                actual.append(c);
            }
        }
        campos.add(actual.toString());
        return campos;
    }
}
