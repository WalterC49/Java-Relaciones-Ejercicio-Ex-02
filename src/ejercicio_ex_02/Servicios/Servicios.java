/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_ex_02.Servicios;

import ejercicio_ex_02.Entidades.Asiento;
import ejercicio_ex_02.Entidades.Cine;
import ejercicio_ex_02.Entidades.Espectador;
import ejercicio_ex_02.Entidades.Pelicula;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Walter
 */
public class Servicios {
    private Cine c = new Cine();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void CrearSala(){
        Asiento s[][] = new Asiento[8][6];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                Integer a = 8 - i;
                String b = a.toString();
                switch (j) {
                    case 0:
                        b = b.concat("A");
                        break;
                    case 1:
                        b = b.concat("B");
                        break;
                    case 2:
                        b = b.concat("C");
                        break;
                    case 3:
                        b = b.concat("D");
                        break;
                    case 4:
                        b = b.concat("E");
                        break;
                    case 5:
                        b = b.concat("F");
                        break;
                    default:
                        throw new AssertionError();
                }
                s[i][j] = new Asiento(b,null);
            }
        }
        c.setSala(s);
        
        ArrayList<String> a = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for(char j='A';j<'G';j++){
                String asiento=(8-i)+String.valueOf(j);
                a.add(asiento);
            }
        }
        System.out.println(a);
        c.setAsientosLibres(a);
    }
    
    public void IngresarPelicula(){
        boolean vofHora;
        String nombre;
        String duracion;
        Integer edad;
        String director;
        System.out.print("\nIngrese el nombre de una película: ");
        nombre=leer.next();
        do{
            vofHora=false;
            System.out.print("Ingrese su duración(00.00.00): ");
            duracion=leer.next();
            for(char i='A';i<='z';i++){
                if(duracion.contains(String.valueOf(i))){
                    vofHora = true;
                    System.out.println("Vuelva a ingresar la hora.");
                    break;
                }
            }
        }while(vofHora);
        duracion=FormatoHora(duracion);
        System.out.print("Ingrese la edad mínima para ver la película: ");
        edad=leer.nextInt();
        System.out.print("Ingrese el director de la película: ");
        director=leer.next();
        c.setPeli(new Pelicula(nombre,duracion,edad,director));
    }
    
    public void IngresarPrecioCine(){
        float precio;
        String aux;
        System.out.print("Ingrese el precio de la entrada: ");
        aux=leer.next();
        if(aux.contains(",")){
            aux=aux.replace(",", ".");
        }
        precio=parseFloat(aux);
        c.setPrecio(precio);
    }
    
    public void MostrarInfoSala(){
        System.out.println("\nPelicula: " + c.getPelicula().getTitulo());
        System.out.println("Duración: " + c.getPelicula().getDuracion());
        System.out.println("Edad mínima requerida: "+c.getPelicula().getEdadMinima());
        System.out.println("Precio de entrada: "+c.getPrecio());
    }
    
    public void MostrarSala(){
        System.out.println("\nEstado de actual de la sala:");
        System.out.println("Asientos libres: "+c.getAsientosLibres().size());
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){
                System.out.print(c.getSala()[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void IngresarEspectadores(){
        int cant;
        System.out.print("\nIngrese la cantidad de espectadores que quiere ingresar: ");
        cant=leer.nextInt();
        System.out.println("Ahora se les dará edades y dinero de forma aleatoria.");
        ArrayList<Espectador> interesados = new ArrayList();
        for (int i = 0; i < cant; i++) {
            Espectador e = new Espectador("Interesado " + (i + 1), (int) (Math.random() * 70), (float) (Math.random() * 1000));
            interesados.add(e);
        }
        
        for (Espectador e : interesados) {
            MostrarSala();
            String lugar;
            boolean vof;
            
            do{
                vof=false;
                System.out.println(e);
                System.out.print("Elija un lugar(2A por ejemplo): ");
                lugar=toUpperCase(leer.next());
                //COMPRUEBA QUE SE INGRESE UN ASIENTO VALIDO
                if(ValidarEntrada(lugar)){
                    //COMPRUEBA SI QUEDAN ASIENTOS LIBRES
                    if(c.getAsientosLibres().size()>0){
                        //COMPRUEBA LA EDAD DEL ESPECTADOR
                        if (e.getEdad()>=c.getPelicula().getEdadMinima()){
                            //COMPRUEBA SI EL ESPECTADOR TIENE EL DINERO PARA PAGAR LA ENTRADA
                            if(e.getDinero()>=c.getPrecio()){
                                e.setDinero(e.getDinero()-c.getPrecio());
                                //BUSCAMOS EL ASIENTO QUE SE INGRESO
                                AsignarAsiento(e,lugar);
                            }else{
                                System.out.println("\n"+e.getNombre()+" le falta dinero para pagar la entrada.");
                            } 
                        }else{
                            System.out.println("\n"+e.getNombre()+" no tiene la edad mínima para ver la película.");
                        }
                    }else{
                        System.out.println("\nNo quedan asientos libre en la sala.");
                    }
                }else{
                    System.out.println("\nIngreso no valido.");
                    vof=true;
                }   
            }while(vof);
        }    
    }           
                
    private boolean ValidarEntrada(String lugar){
        boolean fc=false,cc=false;
        String f,col;
        
        if(lugar.length()>8||lugar.length()<2){
            return false;
        }else{
            f=lugar.substring(0,1);
            for(char i='A';i<'z';i++){
                if(f.contains(String.valueOf(i))){
                    return false;
                }
            }
            fc = !(parseInt(f)>8||parseInt(f)<1);
            
            col=lugar.substring(1,2);
                
            for(char i='A';i<'G';i++){
                if(String.valueOf(i).equals(col)){
                    cc=true;
                    break;
                }
            }
            return fc&&cc;
        }
    }        
    
    private void AsignarAsiento(Espectador e,String lugar){
        boolean listo=false;
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){ 
                if(c.getSala()[i][j].getUbicacion().equals(lugar)){
                    //SI EL ASIENTO ESTÁ LIBRE SE LO ASIGNAMOS Y QUITAMOS DE LA LISTA DE ASIENTOS LIBRES
                    if(c.getSala()[i][j].getE()==null){
                        c.getSala()[i][j].setE(e);
                        c.getAsientosLibres().remove(lugar);
                        listo=true;
                    //EN CASO CONTRARIO SE LE DA UNO DE FORMA ALEATORIA Y SE QUITA DE LA LISTA DE ASIENTOS LIBRES
                    }else{
                        System.out.println("Ese asiento está ocupado.");
                        System.out.println("Se le dará otro de forma aleatoria.");
                        for(int k=0;k<8;k++){
                            for(int l=0;l<6;l++){
                                if(c.getSala()[k][l].getE()==null){
                                    c.getSala()[k][l].setE(e);
                                    c.getAsientosLibres().remove(c.getSala()[k][l].getUbicacion());
                                    listo=true;
                                    break;
                                }
                            }
                            if(listo){
                                break;
                            }
                        }
                    }
                }
                if(listo){
                    break;
                }
            }
            if(listo){
                break;
            }
        }
    }
    
    private String FormatoHora(String hora){
        DecimalFormat dc = new DecimalFormat("00.#");
        String formato="";
        //Recibimos la hora con un formato de (1,3,56) o (1.3.56) y lo cambiamos a (01:03:56)
        if(hora.contains(".") || hora.contains(":") || hora.contains(" ")){
            hora = hora.replaceAll("\\.", ",");
            hora = hora.replaceAll(":", ",");
            hora = hora.replaceAll(" ", ",");
        }
        
        if(hora.contains(",")){
            String horaFormato[] = hora.split(",");
            //Controlamos la equivalencia de segundo a minuto y minuto a horas (01:08:62 a 01:09:02).
            for(int i=(horaFormato.length-1);i>0;i--){
                int num = Integer.parseInt(horaFormato[i]);
                if(num>59){
                    horaFormato[i-1] = String.valueOf((Integer.parseInt(horaFormato[i-1]))+1);
                    horaFormato[i] = String.valueOf(num-60);
                }
            }
            //retornamos el formato de la hora
            for(int i=0;i<horaFormato.length;i++){
                horaFormato[i] = dc.format(Integer.parseInt(horaFormato[i]));
                if((i+1)==horaFormato.length){
                   if(horaFormato.length==2 &&((i+1)==horaFormato.length)){
                       formato += horaFormato[i]+":00";
                    } else {
                    formato += horaFormato[i];
                    }
                } else {
                    formato += horaFormato[i]+":";
                }
            }
            return formato;
        }
        //Si existe o no existe la hora usamos isEmpty.
        if(!(hora.isEmpty())){
            if(Integer.parseInt(hora)>=0){
            formato = dc.format(Integer.parseInt(hora))+":00:00";
            return formato;
            }
        }
        return formato = "00:00:00";
    }
}
