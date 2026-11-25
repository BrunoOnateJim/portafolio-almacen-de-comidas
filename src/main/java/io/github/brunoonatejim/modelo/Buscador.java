package io.github.brunoonatejim.modelo;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class Buscador {
    private static final String MARCADOR_ENIE = "__ENIE_TEMPORAL";

    public enum CriterioBusqueda{
        PROTEINAS,
        CARBOHIDRATOS,
        CALORIAS,
        GRASAS
    }

    private static String normalizarConservandoEnie(String text){
        if(text == null){
            throw new IllegalArgumentException("No se acepta texto nulo");
        }
        String protegido = text.replace("ñ", MARCADOR_ENIE).replace("Ñ", MARCADOR_ENIE);
        String sinAcentos = Normalizer.normalize(protegido, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinAcentos.replace(MARCADOR_ENIE, "ñ").toLowerCase();
    }

    public List<Comida> buscarPorNombre(List<Comida> colComidas, String nombre){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("No se pueden poner nombres vacios");
        }
        if(colComidas == null){
            throw new IllegalArgumentException("No se permiten listas nulas");
        }
        List<Comida> resultado = new ArrayList<>();
        String nombreNormalizado = normalizarConservandoEnie(nombre);
        for(Comida comida: colComidas){
            if(normalizarConservandoEnie(comida.getNombre()).contains(nombreNormalizado)){
                resultado.add(comida);
            }
        }
        return resultado;
    }
}
