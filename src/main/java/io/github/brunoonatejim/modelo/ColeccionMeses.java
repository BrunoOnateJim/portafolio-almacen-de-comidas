package io.github.brunoonatejim.modelo;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class ColeccionMeses {
    private final Map<YearMonth, Mes> colMeses;

    public ColeccionMeses(){
        this.colMeses = new TreeMap<>();
    }

    public Map<YearMonth, Mes> getColMeses(){
        return Collections.unmodifiableMap(colMeses);
    }

    public void agregarMes(Mes mes){
        if(mes == null){
            throw new IllegalArgumentException("No se pueden poner meses vacios");
        }
        YearMonth yearMes = YearMonth.from(mes.getFechaInicialMes());
        if(colMeses.containsKey(yearMes)){
            throw new IllegalArgumentException("Este mes ya existe");
        }
        colMeses.put(yearMes, mes);
    }

    public Mes buscarMes(YearMonth clave) throws MesInexistenteException{
        if(clave == null){
            throw new IllegalArgumentException("No se puede enviar clave vacia");
        }
        if(!colMeses.containsKey(clave)){
            throw new MesInexistenteException("Este mes no existe");
        }
        Mes mes = colMeses.get(clave);
        return mes;
    }
    private Map<LocalDate, Semana> obtenerSemanasUnicas(){
        Map<LocalDate, Semana> semUnicas = new TreeMap<>();
        for(Mes mes: colMeses.values()){
            for(Semana semana: mes.getSemanas()){
                LocalDate fecha = semana.getFechaInicial();
                semUnicas.put(fecha, semana);
            }
        }
        return semUnicas;
    }

    public List<Comida> obtenerColComidas(){
        Map<LocalDate, Semana> semUnicas = obtenerSemanasUnicas();
        List<Comida> colComidas = new ArrayList<>();
        for(Semana semana: semUnicas.values()){
            colComidas.addAll(semana.obtenerComidasSemanales());
        }
        return colComidas;
    }
}
