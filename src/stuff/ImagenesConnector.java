/*
 * ImagenesConnector.java
 * Author: Stephano Bravo
 */

package stuff;

import java.net.URL;

import frames.OpcionesFrame;

public class ImagenesConnector {

    public static URL imgURLr,
            imgURL1,
            imgURL2,
            imgURL3,
            imgURL4,
            imgURL5,
            imgURL6,
            imgURL7,
            imgURL8;
    private String selector;

    public ImagenesConnector() {
        selector = OpcionesFrame.seleccion;

        if(selector.equalsIgnoreCase("Frutas")) {
            imgURLr = getClass().getResource("/picturesF/Mem_Frutas.jpg");
            imgURL1 = getClass().getResource("/picturesF/Mem_Blueberry.jpg");
            imgURL2 = getClass().getResource("/picturesF/Mem_Cherry.jpg");
            imgURL3 = getClass().getResource("/picturesF/Mem_Fresas.jpg");
            imgURL4 = getClass().getResource("/picturesF/Mem_Kiwi.jpg");
            imgURL5 = getClass().getResource("/picturesF/Mem_Manzana.jpg");
            imgURL6 = getClass().getResource("/picturesF/Mem_Pera.jpg");
            imgURL7 = getClass().getResource("/picturesF/Mem_Pinia.jpg");
            imgURL8 = getClass().getResource("/picturesF/Mem_Uvas.jpg");

        } else if(selector.equalsIgnoreCase("Animales")) {
            imgURLr = getClass().getResource("/picturesA/Mem_Animals.jpg");
            imgURL1 = getClass().getResource("/picturesA/Mem_Changuito.jpg");
            imgURL2 = getClass().getResource("/picturesA/Mem_Erizo.jpg");
            imgURL3 = getClass().getResource("/picturesA/Mem_Jirafa.jpg");
            imgURL4 = getClass().getResource("/picturesA/Mem_Koala.jpg");
            imgURL5 = getClass().getResource("/picturesA/Mem_Panda.jpg");
            imgURL6 = getClass().getResource("/picturesA/Mem_Perro.jpg");
            imgURL7 = getClass().getResource("/picturesA/Mem_Pinguino.jpg");
            imgURL8 = getClass().getResource("/picturesA/Mem_Zorro.jpg");

        } else if(selector.equalsIgnoreCase("Chisyosos")) {
            imgURLr = getClass().getResource("/picturesM/Mem_Lic.jpg");
            imgURL1 = getClass().getResource("/picturesM/Mem_Aja.jpg");
            imgURL2 = getClass().getResource("/picturesM/Mem_Ambruna.jpg");
            imgURL3 = getClass().getResource("/picturesM/Mem_Arriba.jpg");
            imgURL4 = getClass().getResource("/picturesM/Mem_Burros.jpg");
            imgURL5 = getClass().getResource("/picturesM/Mem_Igual.jpg");
            imgURL6 = getClass().getResource("/picturesM/Mem_Achis.jpg");
            imgURL7 = getClass().getResource("/picturesM/Mem_NoTanto.jpg");
            imgURL8 = getClass().getResource("/picturesM/Mem_Virus.jpg");
        }
    }

    public static URL getImgURLr() {
        return imgURLr;
    }
    public static URL getImgURL1() {
        return imgURL1;
    }
    public static URL getImgURL2() {
        return imgURL2;
    }
    public static URL getImgURL3() {
        return imgURL3;
    }
    public static URL getImgURL4() {
        return imgURL4;
    }
    public static URL getImgURL5() {
        return imgURL5;
    }
    public static URL getImgURL6() {
        return imgURL6;
    }
    public static URL getImgURL7() {
        return imgURL7;
    }
    public static URL getImgURL8() {
        return imgURL8;
    }
}
