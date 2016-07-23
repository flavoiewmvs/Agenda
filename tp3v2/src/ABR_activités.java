/*
cette class contient chacun des items activités
l'utilisation d'un arbre binaire me permet d'effectuer une recherche efficace de mes items
J'ai repris la structure vue en classe
 code: (watt & brown 2001)
        guy lapalme 2011)
        (b.m   2016)

 */


import java.util.ArrayList;

/**
 *
 * @author flavoie Fabien Lavoie lavf27046702
 */
public class ABR_activités<E extends Comparable<E>> {

    protected E _element;
    protected ABR_activités<E> _gauche;
    protected ABR_activités<E> _droite;

    public ABR_activités() {
    }

    public ABR_activités(E element) {
        _element = element;
        _gauche = null;
        _droite = null;
    }

    public E getElement() {
        return _element;
    }

    public ABR_activités<E> getGauche() {
        return _gauche;
    }

    public ABR_activités<E> getDroite() {
        return _droite;
    }

    public ABR_activités<E> inserer(E element) {
        int direction = element.compareTo(_element);
        if (direction < 0) {
            _gauche = (_gauche == null) ? new ABR_activités<E>(element) : _gauche.inserer(element);
        } else if (direction > 0) {
            _droite = (_droite == null) ? new ABR_activités<E>(element) : _droite.inserer(element);
        } else {
           Item ItemCourant = (Item) _element;
           Item ItemNouveau = (Item) element;
           ItemCourant.setDescription(ItemNouveau.getDescription());
//           ItemCourant.setHeure(ItemNouveau.getHeure());
          }
        return this;
    }

    public ABR_activités<E> chercher(E element) {
        boolean pasLa = false;
        ABR_activités ABRTrouvé=this;
        int direction = element.compareTo(_element);
        if (direction < 0) {
            if (_gauche == null) {
                pasLa = true;
                //lever exception ici
            } else {
                ABRTrouvé = _gauche.chercher(element);
            }
        } else if (direction > 0) {
            if (_droite == null) {
                pasLa = true;
//                lever exception ici 
            } else {
                ABRTrouvé = _droite.chercher(element);
            }
        }
        return ABRTrouvé;
    }

    public int taille() {
        return ((_gauche == null) ? 0 : _gauche.taille()) + ((_droite == null) ? 0 : _droite.taille()) + 1;
    }

    protected E elementPlusAGauche() {
        return (_gauche == null) ? _element : _gauche.elementPlusAGauche();
    }

    public ABR_activités<E> supprimer(E element) {
        ABR_activités<E> resultat = this;
        int direction = element.compareTo(_element);
        if (direction < 0) {
            if (_gauche != null) {
                _gauche = _gauche.supprimer(element);
            }
        } else if (direction > 0) {
            if (_droite != null) {
                _droite = _droite.supprimer(element);
            }
        } else if (_gauche == null) {; // 2 cas >>>
            resultat = _droite;
        } else if (_droite == null) {
            resultat = _gauche;

        } else {
            _element = _droite.elementPlusAGauche();
            _droite = _droite.supprimer(_element);
        }
        return resultat;
    }

    /**
     * Affiche l'arbre selon un parcours prefixe
     */
    public void ParcoursPrefixe() {
        System.out.print(getElement() + ",");
        if (getGauche() != null) {
            getGauche().ParcoursPrefixe();
        }
        if (getDroite() != null) {
            getDroite().ParcoursPrefixe();
        }
    }

    /**
     * Affiche l'arbre selon un parcours infixe
     */
    public void ParcoursInfixe(ArrayList<Item> items) {
//        Item[] listeItem= new Item[this.taille()];
        if (getGauche() != null) {
            getGauche().ParcoursInfixe(items);
        }
        items.add((Item) getElement());
        if (getDroite() != null) {
            getDroite().ParcoursInfixe(items);
        }
//        return listeItem;
    }

    /**
     * Affiche l'arbre selon un parcours Suffixe
     */
    public void ParcoursSuffixe() {
        if (getGauche() != null) {
            getGauche().ParcoursSuffixe();
        }
        if (getDroite() != null) {
            getDroite().ParcoursSuffixe();
        }
        System.out.print(getElement() + ",");
    }

}
