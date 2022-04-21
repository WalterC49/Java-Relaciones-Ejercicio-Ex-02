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
            for(char j='A';j<'F';j++){
                String asiento=(8-i)+String.valueOf(j);
                a.add(asiento);
            }
        }
        c.setAsientosLibres(a);

        
    }
    
    public void IngresarPelicula(){
        boolean listo=false;
        String nombre;
        String duracion;
        Integer edad;
        String director;
        System.out.print("Ingrese el nombre de una película: ");
        nombre=leer.next();
        /*do{
            System.out.print("Ingrese su duración(00:00:00): ");
            duracion=leer.next();
            String aux="";
            for(int i=0;i<duracion.length();i++){
                if(duracion.substring(i, i+1).equals(",")||duracion.substring(i, i+1).equals(";")){
                    aux=aux.concat(":");
                }else{
                    aux=aux.concat(duracion.substring(i, i+1));
                }
            }
        }while(listo);*/
        System.out.print("Ingrese la edad mínima para ver la película: ");
        edad=leer.nextInt();
        System.out.print("Ingrese el director de la película: ");
        director=leer.next();
        //c.setPeli(new Pelicula(nombre,duracion,edad,director));
    }
    
    public void IngresarPrecioCine(){
        float precio;
        String aux;
        System.out.print("Ingrese un precio: ");
        aux=leer.next();
        if(aux.contains(",")){
            aux=aux.replace(",", ".");
        }
        precio=parseFloat(aux);
        System.out.println(precio);
        c.setPrecio(precio);
    }
    
    public void MostrarSala(){
        System.out.println("Sala:");
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
        System.out.print("Ingrese la cantidad de espectadores que quiere ingresar: ");
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
            boolean listo=false;
            do{
                
            }while(listo);
            System.out.print(e.getNombre()+" elija un lugar(A2 por ejemplo): ");
            lugar=leer.next();
            if(lugar.length()>8||lugar.length()<2){
                System.out.println("Ingreso no valido.");
            }else{
                boolean fc,cc=false;
                String f,col;
                
                f=lugar.substring(0,1);
                col=toUpperCase(lugar.substring(1));
                
                fc = !(parseInt(f)>8||parseInt(f)<1);
                
                for(char i='A';i<'F';i++){
                    cc = String.valueOf(i).equals(col);
                }
                
                if(fc&&cc){
                    
                }
                
                Asiento visual[][] = c.getSala();
                for(int i=0;i<8;i++){
                    for(int j=0;j<6;j++){
                        if(c.getSala()[i][j].getUbicacion().equalsIgnoreCase(lugar)){
                            
                        }
                    }
                }
            }
        }
        
    }
    
}
