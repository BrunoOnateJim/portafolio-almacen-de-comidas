package io.github.brunoonatejim.modelo;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class Buscador {
    private static final String MARCADOR_ENIE = "__ENIE_TEMPORAL";

    public enum CriterioBusqueda{
        PROTEINAS(20),
        CARBOHIDRATOS(20),
        CALORIAS(300),
        GRASAS(20);

        private final int margen;
        CriterioBusqueda(int margen){
            this.margen = margen;
        }
        public int getMargen(){
            return margen;
        }
    }

    private float devolverValorCriterio(Comida comida, CriterioBusqueda criterio){
        if(criterio == null){
            throw new IllegalArgumentException("El criterio no puede ser null");
        }
        switch (criterio) {
            case PROTEINAS:
                return comida.getProteinas();
            
            case CARBOHIDRATOS:
                return comida.getCarbohidratos();

            case CALORIAS:
                return comida.getCalorias();

            case GRASAS:
                return comida.getGrasas();
        
            default:
                throw new IllegalArgumentException("Criterio no encontrado");
        }
    }

    public List<Comida> buscarPorValor(List<Comida> colComidas, float valor, CriterioBusqueda criterio){
        if(valor < 0){
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        if(colComidas == null){
            throw new IllegalArgumentException("No puede ser una lista nula");
        }
        if(criterio == null){
            throw new IllegalArgumentException("No puede haber un criterio nulo");
        }
        List<Comida> cumplenCriterio = new ArrayList<>();
        int margen = criterio.getMargen();
        for(Comida comida: colComidas){
            float valCrit = devolverValorCriterio(comida, criterio);
            if(valCrit >= (valor - margen) && valCrit <= (valor + margen)){
                cumplenCriterio.add(comida); 
            }
        }
        return cumplenCriterio;
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

    public enum AscendenteDescendente{
        ASCENDENTE, DESCENDENTE
    }

    public List<Comida> ordenarorCriterio(List<Comida> colComidas, AscendenteDescendente tipo, CriterioBusqueda criterio){
        if(colComidas == null){
            throw new IllegalArgumentException("No se permiten listas nulas");
        }
        if(tipo == null){
            throw new IllegalArgumentException("No se permite tipo nulo");
        }
        if(criterio == null){
            throw new IllegalArgumentException("No se permiten criterios nulos");
        }
        List<Comida> ordenada = new ArrayList<>(colComidas);
        switch (tipo) {
            case ASCENDENTE:
                ordenada.sort((a,b) -> Float.compare(devolverValorCriterio(a, criterio), devolverValorCriterio(b, criterio)));
                return ordenada;
        
            case DESCENDENTE:
                ordenada.sort((a,b)->Float.compare(devolverValorCriterio(b, criterio), devolverValorCriterio(a, criterio)));
                return ordenada;    
            default:
                throw new IllegalArgumentException("No existe ese tipo de ordenamiento");
        }
    }
}
