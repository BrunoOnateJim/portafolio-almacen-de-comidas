package io.github.brunoonatejim.modelo;

public class Comida implements Calculador{
    private final String nombre;
    private final int calorias;
    private final float proteinas;
    private final float grasas;
    private final float carbohidratos;

    public Comida(String nombre, int calorias, float proteinas, float grasas, float carbohidratos){
        if(nombre == null){
            throw new IllegalArgumentException("No puede estar vacio el nombre");
        }
        if(nombre.isBlank()){
            throw new IllegalArgumentException("No puede estar vacio el nombre");
        }
        if(calorias < 0){
            throw new IllegalArgumentException("Las calorias tienen que ser mayores o iguales a cero");
        }
        if(proteinas < 0){
            throw new IllegalArgumentException("La proteina tiene que ser mayor o igual a cero");
        } 
        if(grasas < 0){
            throw new IllegalArgumentException("La grasa tiene que ser mayor o igual a cero");
        } 
        if(carbohidratos < 0){
            throw new IllegalArgumentException("Los carbohidratos tienen que ser mayores o iguales a cero");
        }
        this.nombre = nombre;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
    }

    public String getNombre(){
        return nombre;
    }
    public int getCalorias(){
        return calorias;
    }
    public float getProteinas(){
        return proteinas;
    }
    public float getGrasas(){
        return grasas;
    }
    public float getCarbohidratos(){
        return carbohidratos;
    }

    @Override 
    public int calcularCaloriasTotales(){
        return calorias;
    }    
}
