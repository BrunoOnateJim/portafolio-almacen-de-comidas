package io.github.brunoonatejim.modelo;

public class MesInexistenteException extends Exception {
    public MesInexistenteException(String mensaje){
        super(mensaje);
    }
    public MesInexistenteException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
