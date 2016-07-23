/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.lang.Math;

/**
 *
 * @author flavoi Fabien Lavoie lavf27046702
 */
public class Item implements Comparable<Item> {
// cette class contient notre reférence a notre objet contenue dans le tableau

    private String titre;
    private double heure;
    private String description;

    public Item(String titre, double heure) {
        this.titre = titre;
        this.heure = heure;
    }

    public Item(String titre, double heure, String description) {
        this.titre = titre;
        this.heure = heure;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getHeure() {
        return heure;
    }

    public String faitAffichageListe() {
        int hh = (int) heure;
        double pDecimalhh = (heure - hh) * 60;
        int mm = (int) pDecimalhh;
        int longueur = (titre.length() > 15 ? 15 : titre.length());
        String retour = "";
        if (heure == 23.99) {
            retour = "___________________";
         } else {
            retour = (hh < 10 ? "0" : "") + hh;
            retour += ":" + (mm < 10 ? "0" : "") + mm;
            retour += " " + titre.substring(0, longueur);
        }
        return retour;
    }

    public static double faitHeureDouble(String heureTexte) {
        //throw heureValide ici
        int hh = 0;
        int mm = 0;
        int longueur = heureTexte.length();
        hh = Integer.parseInt(heureTexte.split(":")[0]);
        mm = Integer.parseInt(heureTexte.split(":")[1]);
        Double retour = 0.0;
        retour = (double) hh + ((double) mm / 60);
        return retour;
    }

    public void setHeure(float heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(final Item o) {
        return Double.compare(this.getHeure(), o.getHeure());
    }
}
