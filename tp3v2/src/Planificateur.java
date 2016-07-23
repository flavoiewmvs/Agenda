
/**
 *
 * @author flavoie Fabien Lavoie lavf27046702
 *
 * planificateur hebdomadaire. Cette interface va permettre l’ajout, la
 * modification et la suppression d’activité. Ces activités vont être placé en
 * ordre d’heure de début et contenir une courte description.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUIFont(new FontUIResource(new Font("Courrier", 0, 20)));
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
        afficheDetail();
        saisieDescription.setEnabled(false);
        saisieHeure.setEnabled(false);
        saisieTitre.setEnabled(false);
        btnSauvegarder.setEnabled(false);
        btnSupprimer.setEnabled(false);
        btnAjouter.setEnabled(true);
        list.setEnabled(true);
        pack();
        setVisible(true);
    }

    /*
    methode pour batir ma liste de titre dans mon planificateur
     */
    private void afficheListTitre() {
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
        contenu.add(list, BorderLayout.WEST);

    }

    /*
    Affichage de la section detail
     */
    private void afficheDetail() {
        //Construction de la section detail
        //je divise en trois ma section detail pour le formatage
        DetailItem = new JPanel();
        DetailItem.setLayout(new GridLayout(3, 1));
        DetailItem.setBackground(Color.LIGHT_GRAY);
        //portion detail titre et heure
        DetailItem1 = new JPanel();
        DetailItem1.setLayout(new GridLayout(6, 2));
        DetailItem1.setBackground(Color.LIGHT_GRAY);
        //portion de la description
        DetailItem2 = new JPanel();
        DetailItem2.setLayout(new GridLayout(1, 2));
        DetailItem2.setBackground(Color.LIGHT_GRAY);
        //portion de l action
        DetailItem3 = new JPanel();
        DetailItem3.setLayout(new GridLayout(6, 2));
        DetailItem3.setBackground(Color.LIGHT_GRAY);
        DetailItem4 = new JPanel();
        DetailItem4.setLayout(new BorderLayout());
        DetailItem4.setBackground(Color.LIGHT_GRAY);

//ajout de champs vide pour avoir un hauteur de champs normal
        for (int i = 1; i <= 8; ++i) {
            Filler1 = new JLabel("");
            DetailItem1.add(Filler1);
        }

        titre = new JLabel("Titre :");
        DetailItem1.add(titre);
        saisieTitre = new JTextField("");
        DetailItem1.add(saisieTitre);

        Heure = new JLabel("Heure :");
        DetailItem1.add(Heure);
        saisieHeure = new JTextField("");
        saisieHeure.setToolTipText("hh:mm (hh=0..23) (mm=0..59)");
        DetailItem1.add(saisieHeure);

        Description = new JLabel("Description : ");
        DetailItem2.add(Description);
        saisieDescription = new JTextArea("");
        saisieDescription.setColumns(20);
        saisieDescription.setRows(10);
        DetailItem2.add(saisieDescription);

        Filler2 = new JLabel("");
        DetailItem3.add(Filler2);
        btnSauvegarder = new JButton("Sauvegarder");
        DetailItem3.add(DetailItem4);
        DetailItem4.add(btnSauvegarder, BorderLayout.EAST);

        DetailItem.add(DetailItem1);
        DetailItem.add(DetailItem2);
        DetailItem.add(DetailItem3);
        contenu.add(DetailItem, BorderLayout.CENTER);
//               ListeTitre.addListSelectionListener((e) -> saisieTitre.setText(ListeTitre.getSelectedValue()));
//        btnSauvegarder.addActionListener((action) -> faireItem(saisieTitre.getText(), 11.5, saisieDescription.getText()));
        btnSauvegarder.addActionListener((action) -> faireItem());
    }

    /*
    listener de ma liste de titre
     */
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        String itemSelectionné = (String) list.getSelectedValue();
        System.out.println(itemSelectionné);

        String heureSelectionText = itemSelectionné.substring(0, 5);
        if (heureSelectionText != "_____") {
            double heureSelection = Item.faitHeureDouble(heureSelectionText);
            Item monItemCherché = new Item("", heureSelection, " ");
            Item itemSelectioné = (Item) this.mesActivités.chercher(monItemCherché).getElement();
            monItemCherché = null;
            saisieDescription.setText(itemSelectioné.getDescription());
            saisieHeure.setText(Item.faitHeureString(itemSelectioné.getHeure()));
            saisieTitre.setText(itemSelectioné.getTitre());
            pack();
            saisieDescription.setEnabled(true);
            saisieHeure.setEnabled(false);
            saisieTitre.setEnabled(false);
            btnSauvegarder.setEnabled(true);
            btnSupprimer.setEnabled(true);
            btnAjouter.setEnabled(false);
            list.setEnabled(false);
        }
    }

    /*
    Ajout de l item , activation de la section detail pour la saisie
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
        list.setEnabled(false);
    }

    /*
    efface l item en cours et  le contenue de la section detail et desactive la saisie
     */
    private void effaceItem() {
        String heureSelectionText = saisieHeure.getText();
             double heureSelection = Item.faitHeureDouble(heureSelectionText);
            Item monItemCherché = new Item("", heureSelection, " ");
            Item itemSupprimé = (Item) this.mesActivités.supprimer(monItemCherché).getElement();
            monItemCherché = null;
        contenu.remove(list);
        afficheListTitre();
        System.out.println("item suprimé");
        contenu.add(list, BorderLayout.WEST);
        saisieDescription.setText("");
        saisieHeure.setText("");
        saisieTitre.setText("");
        saisieDescription.setEnabled(false);
        saisieHeure.setEnabled(false);
        saisieTitre.setEnabled(false);
        btnSauvegarder.setEnabled(false);
        btnSupprimer.setEnabled(false);
        btnAjouter.setEnabled(true);
        list.setEnabled(true);
        pack();
    }

    /*
    Bati item et ajoute dans mon ABR
    rebatie la liste de titre
     */
    private void faireItem() {
        Item monItem = new Item(saisieTitre.getText(), Item.faitHeureDouble(saisieHeure.getText()), saisieDescription.getText());
        this.mesActivités.inserer(monItem);
        // important de retirer le jlist precedent car fonctionne pas !!!
        contenu.remove(list);
        afficheListTitre();
        System.out.println("item ajouté");
        contenu.add(list, BorderLayout.WEST);
        saisieDescription.setText("");
        saisieHeure.setText("");
        saisieTitre.setText("");
        btnSauvegarder.setEnabled(false);
        btnSupprimer.setEnabled(false);
        btnAjouter.setEnabled(true);
        saisieDescription.setEnabled(false);
        saisieHeure.setEnabled(false);
        saisieTitre.setEnabled(false);
        list.setEnabled(true);
        pack();
    }

    /*
    Init de mon ABR qui contiendra mes donnees
     */
    private void faireABR() {
// initialise mon arbre binaire avec une valeur a minuit pour balancer mon arbre
        Item monItem = new Item("___________________", 23.99, "               ");
        this.mesActivités = new ABR_activités(monItem);
        System.out.println("Arbre Initialisé");
    }

    /*
    Affichage de la section des bouton d action
     */
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

    /*
    methode pour ajuster le font car en ecran 4k 
     */
    public static void setUIFont(FontUIResource f) {
        // methode pour ajuster la font 
        // besoin d<ajuster font sur ecran 4k
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    public static void main(String args[]) {

        Planificateur ecran = new Planificateur();
//        ecran.setVisible(true);
    }

}
