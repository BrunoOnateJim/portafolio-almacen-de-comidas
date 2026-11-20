package io.github.brunoonatejim.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dia implements Calculador{
    private final LocalDate fecha;
    private final List<Comida> comidas;

    public Dia(LocalDate fecha){
        if(fecha == null){
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        this.fecha = fecha;
        this.comidas = new ArrayList<>();
    }

    public LocalDate getFecha(){
        return fecha;
    }
    public List<Comida> getComidas(){
        return Collections.unmodifiableList(comidas);
    }

    public void agregarComida(Comida comida){
        if(comida == null){
            throw new IllegalArgumentException("No se puede agregar una comida vacia");
            
        }
        comidas.add(comida);
    }

    @Override 
    public int calcularCaloriasTotales(){
        int suma = 0; 
        for(Comida comida: comidas){
            suma += comida.calcularCaloriasTotales();
        }
        return suma;
    }
}
