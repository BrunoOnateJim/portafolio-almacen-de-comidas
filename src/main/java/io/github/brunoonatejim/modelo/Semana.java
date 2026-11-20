package io.github.brunoonatejim.modelo;
import java.time.DayOfWeek;
import java.time.LocalDate;
public class Semana implements Calculador{
    private final LocalDate fechaInicial;
    private final Dia[] dias;

    public Semana(LocalDate fechaInicial){
        if(fechaInicial == null){
            throw new IllegalArgumentException("La fecha no puede estar vacia");
        }
        if(fechaInicial.getDayOfWeek() != DayOfWeek.MONDAY){
            throw new IllegalArgumentException("Tienes que poner la fecha del dia lunes de la semana en la que estas para crear la semana");
        }
        this.fechaInicial = fechaInicial;
        this.dias = new Dia[7];
    }

    public LocalDate getFechaInicial(){
        return fechaInicial;
    }
    public Dia[] getDias(){
        return dias.clone();
    }

    public void agregarDia(Dia dia){
        if(dia == null){
            throw new IllegalArgumentException("el dia no puede ser vacio");
        }
        LocalDate date = dia.getFecha();
        int diaSemana = date.getDayOfWeek().getValue();
        if(date.isBefore(fechaInicial) || date.isAfter(fechaInicial.plusDays(6))){
            throw new IllegalArgumentException("Este dia no pertenece al rango de la semana");
        }
        if(dias[diaSemana - 1] != null){
            throw new IllegalArgumentException("el dia "+date.getDayOfWeek()+" ya esta ocupado");
        }
        dias[diaSemana-1] = dia;
    }

    @Override 
    public int calcularCaloriasTotales(){
        int suma = 0;
        for(Dia dia: dias){
            if(dia != null){
                suma += dia.calcularCaloriasTotales();
            }
        }
        return suma;

    }
}
