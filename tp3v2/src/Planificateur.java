
/**
 *
 * @author flavoie Fabien Lavoie lavf27046702
 *
 * planificateur hebdomadaire. Cette interface va permettre l’ajout, la
 * modification et la suppression d’activité. Ces activités vont être placé en
 * ordre d’heure de début et contenir une courte description.
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author flavo
 */
public class Planificateur extends javax.swing.JFrame {
    // Variables           

    private javax.swing.JList<String> jList1;
    ABR_activités mesActivités;
    JPanel action;
    JButton btnAjouter;
    JButton btnSupprimer;
//champ section detail
    JLabel Filler1;
    JLabel Filler2;
    JLabel titre;
    JLabel Heure;
    JLabel Description;
    JTextField saisieTitre;
    JTextField saisieHeure;
    JTextArea saisieDescription;
//mes container pour defenir l ecran
    Container contenu;
    JPanel DetailItem;
    JPanel DetailItem1;
    JPanel DetailItem2;
    JPanel DetailItem3;
    JPanel DetailItem4;
    JButton btnSauvegarder;
    //vairable de list
    JList list;

    DefaultListModel model;

    // End of variables declaration                
    public Planificateur() {
        initComposante();
    }

    private void initComposante() {

//        jList1 = new javax.swing.JList<>();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        contenu = getContentPane();
        faireABR();
        //temporaire pour mettre de item et tester
               Item monItem = new Item("Test 1", 10.5, "Test 1 desc");
               Item monItem1 = new Item("Test 2", 8.25, "Test 2 desc");
        this.mesActivités.inserer(monItem);
        this.mesActivités.inserer(monItem1);
        //
        afficheAction();
        afficheListTitre();
//        contenu.add(jList1, BorderLayout.WEST);
        contenu.add(list, BorderLayout.WEST);

        pack();
    }// </editor-fold>                        

    private void afficheListTitre() {
//        jList1.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
//
//            public int getSize() {
//                return strings.length;
//            }
//
//            public String getElementAt(int i) {
//                return strings[i];
//            }
//        });
//        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                jList1MouseClicked(evt);
//            }
//        });
        model = new DefaultListModel();
        list = new JList(model);

        Integer indice = 0;
        ArrayList<Item> listeItem = new ArrayList<Item>();
        mesActivités.ParcoursInfixe(listeItem);
        for (int i = 0; i < mesActivités.taille(); ++i) {
            model.addElement(listeItem.get(i).faitAffichageListe());
        }
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        list.repaint();
//        contenu.add(list, BorderLayout.WEST);
    }

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        String test = (String) list.getSelectedValue();
        System.out.println(test);
    }

    /**
     * @param args the command line arguments
     */
    private void ajoutItem() {
        saisieDescription.setText("");
        saisieHeure.setText("");
        saisieTitre.setText("");
//        DetailItem2.repaint();
        pack();
        saisieDescription.setEnabled(true);
        saisieHeure.setEnabled(true);
        saisieTitre.setEnabled(true);
        btnSauvegarder.setEnabled(true);
        btnSupprimer.setEnabled(true);
        btnAjouter.setEnabled(false);
    }

    private void effaceItem() {
        saisieDescription.setText("");
        saisieHeure.setText("");
        saisieTitre.setText("");
//        DetailItem2.repaint();
        saisieDescription.setEnabled(false);
        saisieHeure.setEnabled(false);
        saisieTitre.setEnabled(false);
        btnSauvegarder.setEnabled(false);
        btnSupprimer.setEnabled(false);
        btnAjouter.setEnabled(true);
        pack();
    }

    private void faireABR() {
// j initialise mon arbre binaire avec une valeur a midi pour balancer mon arbre
        Item monItem = new Item("___________________", 23.99, "               ");
        this.mesActivités = new ABR_activités(monItem);
        System.out.println("Arbre Initialisé");
    }

    private void afficheAction() {
        //Construction de la section detail
        action = new JPanel();
        action.setLayout(new GridLayout(1, 4));

        btnAjouter = new JButton("Ajouter");
        btnSupprimer = new JButton("Supprimer");
        btnAjouter.addActionListener((action) -> ajoutItem());
        btnSupprimer.addActionListener((action) -> effaceItem());
        Filler1 = new JLabel("");
        action.add(Filler1);
        action.add(btnAjouter);
        action.add(btnSupprimer);
        Filler1 = new JLabel("");
        action.add(Filler1);

        contenu.add(action, BorderLayout.NORTH);
    }

    public static void main(String args[]) {

        Planificateur ecran = new Planificateur();
        ecran.setVisible(true);
    }

}
