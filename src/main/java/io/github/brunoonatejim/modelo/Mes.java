package io.github.brunoonatejim.modelo;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mes implements Calculador{
    private final LocalDate fechaInicialMes;
    private final List<Semana> semanas;

    public Mes(LocalDate fechaInicialMes){
        if(fechaInicialMes == null){
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        if(fechaInicialMes.getDayOfMonth() != 1){
            throw new IllegalArgumentException("Para crear el mes tienes que ingresar la fecha del primer dia del mes");
        }
        this.fechaInicialMes = fechaInicialMes;
        this.semanas = new ArrayList<>();
    }

    public LocalDate getFechaInicialMes(){
        return fechaInicialMes;
    }
    public List<Semana> getSemanas(){
        return Collections.unmodifiableList(semanas);
    }

    public void agregarSemana(Semana semana){
        if(semana == null){
            throw new IllegalArgumentException("No se pueden agregar semanas vacias");
        }
        for(Semana semanaL: semanas){
            if(semanaL.getFechaInicial().isEqual(semana.getFechaInicial())){
                throw new IllegalArgumentException("No se pueden agregar semanas repetidas");
            }
        }
        LocalDate fechaSemana = semana.getFechaInicial();
        if((!YearMonth.from(fechaSemana).equals(YearMonth.from(fechaInicialMes))) && (!YearMonth.from(fechaSemana.plusDays(6)).equals(YearMonth.from(fechaInicialMes)))){
            throw new IllegalArgumentException("No puedes agregar una semana que no corresponda al mes");
        }
        semanas.add(semana);
    }

    @Override 
    public int calcularCaloriasTotales(){
        int suma = 0;
        for(Semana semana: semanas){
            suma += semana.calcularCaloriasMes(fechaInicialMes.getMonthValue());
        }
        return suma;
    }

    @Override 
    public float contarMacros(CriterioSuma tipo){
        if(tipo == null){
            throw new IllegalArgumentException("No se permite tipo de criterio nulo");
        }
        float suma = 0;
        for(Semana semana: semanas){
            suma += semana.contarMacrosMes(tipo, fechaInicialMes.getMonthValue());
        }
        return suma;
    }
    
}
