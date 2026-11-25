    package io.github.brunoonatejim.modelo;
    import java.time.DayOfWeek;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;

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

        public int calcularCaloriasMes(int mes){
            if(mes < 1 || mes > 12){
                throw new IllegalArgumentException("El mes no puede ser menor a 1 o mayor a 12");
            }
            if(mes != fechaInicial.getMonthValue() && mes != fechaInicial.plusDays(6).getMonthValue()){
                throw new IllegalArgumentException("esta semana no corresponde al mes dado");
            }
            int suma = 0;
            for(Dia dia: dias){
                if(dia != null){
                    if(dia.getFecha().getMonthValue() == mes){
                        suma += dia.calcularCaloriasTotales();
                    }
                }    
            }
            return suma;
        }

        public List<Comida> obtenerComidasSemanales(){
            List<Comida> comSem = new ArrayList<>();    
            for(Dia dia: dias){
                if(dia != null){
                    comSem.addAll(dia.getComidas());
                }
            }
            return comSem;
        }
    }
