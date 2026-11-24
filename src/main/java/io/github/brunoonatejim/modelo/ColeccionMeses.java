package io.github.brunoonatejim.modelo;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

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

}
