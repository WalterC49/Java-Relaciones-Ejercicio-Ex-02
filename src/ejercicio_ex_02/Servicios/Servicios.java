/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_ex_02.Servicios;

import ejercicio_ex_02.Entidades.Asiento;
import ejercicio_ex_02.Entidades.Cine;

/**
 *
 * @author Walter
 */
public class Servicios {
    private Cine c = new Cine();
    
    public void CrearCine(){
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
    }
    
}
