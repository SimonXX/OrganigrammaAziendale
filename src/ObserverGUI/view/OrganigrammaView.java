package ObserverGUI.view;

import ObserverGUI.ActionPanel;
import ObserverGUI.OrganigrammaModel;
import ObserverGUI.view.style.CustomTableCellRenderer;
import Visitor.PrintVisitor;
import Visitor.StatsVisitor;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


import Composite.Unit;
import Composite.Role;
import Composite.Azienda;
import Composite.Employee;

//La classe OrganigrammaView rappresenta la vista dell'organigramma e si occupa della visualizzazione grafica degli elementi dell'organigramma.
public class OrganigrammaView {

    private int screenWidth;
    private int screenHeight;

    private OrganigrammaModel model;

    JFrame frame= new JFrame();
    OrganigrammaPanel organigrammaPanel;

    OrganigrammaTree organigrammaTree;
    JPanel titlePanel;
    JLabel titleLabel;

    Toolkit toolkit;
    Dimension screenSize;


    String[] columnNamesTable1;
    DefaultTableModel tableModel1;

    JTable table1;

    JPanel tablesPanelButton;
    JScrollPane scrollPaneTable1;

    String[] columnNames2;

    DefaultTableModel tableModel2;

    JTable table2;

    JScrollPane scrollPaneTable2;

    String[] columnNames3;

    DefaultTableModel tableModel3;

    JTable table3;

    JScrollPane scrollPaneTable3;



    JFrame infoRuoliAmmessi;
    JFrame infoImpiegati;

    JFrame infoRuoliCreati;


    JButton ruoliConsentiniButton;
    JButton impiegatiButton;
    JButton ruoliCreatiButton;

    JToolBar toolbar;


    JButton saveData;
    JButton loadData;

    JButton helpButton;

    JButton clearButton;

    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();


    public void setModel(OrganigrammaModel model){
        this.model = model;
    }
    public OrganigrammaView(OrganigrammaModel model) {
        this.model = model;

        inizializzaFrame();



    }

    public void inizializzaFrame(){


        impostaDimensioniSchermo();

        creazionePannelloPrincipale();

        creazioneTabelleRuoliConsentiti();

        creazioneTabellaInfoImpiegati();

        creazioneTabellaRuoliCreati();

        gestionePannelloTabella();

        displayStatistics();

        stampaInfoAzienda();


        salvataggioDati();

        gestioneActionBar();


        ripristinoDati();

        azzeraOrganigramma();

        helpButton();



    }

    private void impostaDimensioniSchermo(){

        //Misure dello schermo
        toolkit = Toolkit.getDefaultToolkit();

        // Ottieni l'oggetto Dimension che rappresenta la dimensione dello schermo
        screenSize = toolkit.getScreenSize();

        // Preleva l'altezza e la larghezza dello schermo
        screenWidth = screenSize.width - 40;
        screenHeight = screenSize.height-40;

        // Creazione della finestra principale
        ;//creiamo il frame principale
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);

        frame.setLayout(new BorderLayout());


    }

    private void creazionePannelloPrincipale(){
        // Creazione del JPanel



        frame.getContentPane().removeAll();


        organigrammaPanel = new OrganigrammaPanel(model, screenWidth, screenHeight);
        JScrollPane scrollOrganigrammaPanel = new JScrollPane(organigrammaPanel);
        frame.add(scrollOrganigrammaPanel, BorderLayout.CENTER);


        //PANNELLO SINISTRO CHE OSPITA JTREE ORGANIGRAMMA


        // Crea il pannello per il titolo
        titlePanel = new JPanel();
        titleLabel = new JLabel("Organigramma Aziendale");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.SOUTH);




        organigrammaTree = new OrganigrammaTree(model, screenWidth, screenHeight);


        JScrollPane scrollOrganigrammaTree = new JScrollPane(organigrammaTree);


        frame.add(scrollOrganigrammaTree, BorderLayout.WEST);



    }

    public void creazioneTabelleRuoliConsentiti(){

        headerRenderer.setBackground(Color.GRAY); // Imposta il colore di sfondo desiderato per la riga del titolo
        headerRenderer.setForeground(Color.WHITE); // Imposta il colore del testo desiderato per la riga del titolo

        columnNamesTable1 = new String[]{"Unità", "Ruoli Consentiti"};

        // Creazione del modello della tabella
        tableModel1 = new DefaultTableModel(columnNamesTable1, 0);

        for (Unit unit : model.getAzienda().getUnits()) {
            String unitName = unit.getName();
            StringBuilder rolesBuilder = new StringBuilder();


            for(Role r: unit.getAllowedRoles()){
                rolesBuilder.append(r.getName()).append("  -  ");
            }

            String roles = rolesBuilder.toString();
            if (roles.length() > 2) {
                roles = roles.substring(0, roles.length() - 2); // Rimuovi l'ultima virgola e lo spazio
            }

            Object[] row = {unitName, roles};
            tableModel1.addRow(row);
        }

        table1 = new JTable(tableModel1);
        table1.setBackground(Color.WHITE);
        table1.setEnabled(false);
        table1.setRowHeight(40);

        table1.setIntercellSpacing(new Dimension(0, 5));
        CustomTableCellRenderer cellRenderer = new CustomTableCellRenderer();
        table1.setDefaultRenderer(Object.class, cellRenderer);
        table1.getTableHeader().setDefaultRenderer(headerRenderer);

        // Creazione dello JScrollPane per la tabella
        scrollPaneTable1 = new JScrollPane(table1);


        ruoliConsentiniButton = new JButton("Visualizza Ruoli Ammessi per Unità");
        ruoliConsentiniButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoRuoliAmmessi = new JFrame();

                infoRuoliAmmessi.setSize(new Dimension(screenWidth/2, screenHeight/2));

                infoRuoliAmmessi.add(scrollPaneTable1);
                infoRuoliAmmessi.setVisible(true);

                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/unit.png"));

                // Imposta l'icona del JFrame
                infoRuoliAmmessi.setIconImage(icon.getImage());
                infoRuoliAmmessi.setTitle("Informazioni Ruoli Ammessi in Unità");

            }
        });



    }

    private void creazioneTabellaInfoImpiegati(){
        // Creazione della tabella per impiegati unità e ruolo

        //seconda tabella
        columnNames2 = new String[]{"NomeImpiegato", "Unità", "Ruolo"};

        tableModel2 = new DefaultTableModel(columnNames2, 0);


        int rowIndex = 0;
        for (Employee employee : model.getAzienda().getEmployees()) {
            String employeeName = employee.getName();
            Object[] row = {employeeName, "", ""};
            tableModel2.addRow(row);

            String unitName = "";
            String roleName = "";


            for (Unit unit : employee.getUnits()) {


                unitName += unit.getName() +" - ";


                if(unit.getEmployeeRole().get(employee.getName())!=null){
                    for (String role : unit.getEmployeeRole().get(employee.getName())) {
                        roleName += role + " - ";



                    }
                }


            }
            tableModel2.setValueAt(unitName, rowIndex, 1);
            tableModel2.setValueAt(roleName, rowIndex, 2);



            rowIndex++;
        }

        table2 = new JTable(tableModel2);

        table2.setBackground(Color.WHITE);
        table2.setRowHeight(40);

        table2.setIntercellSpacing(new Dimension(0, 5));
        CustomTableCellRenderer cellRenderer = new CustomTableCellRenderer();
        table2.setDefaultRenderer(Object.class, cellRenderer);
        table2.getTableHeader().setDefaultRenderer(headerRenderer);


        table2.setEnabled(false);

        scrollPaneTable2 = new JScrollPane(table2);

        impiegatiButton = new JButton("Visualizza Impiegati nell'Azienda");
        impiegatiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoImpiegati = new JFrame();

                infoImpiegati.setSize(new Dimension(screenWidth/2, screenHeight/2));

                infoImpiegati.add(scrollPaneTable2);
                infoImpiegati.setVisible(true);

                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/employeeView.png"));

                // Imposta l'icona del JFrame
                infoImpiegati.setIconImage(icon.getImage());
                infoImpiegati.setTitle("Informazioni Impiegati Azienda");


            }
        });




    }


    private void creazioneTabellaRuoliCreati(){

        //terza tabella

        columnNames3 = new String[]{"Ruoli Creati"};

        tableModel3 = new DefaultTableModel(columnNames3, 0);

        for (Role role : model.getAzienda().getRoles()) {
            String roleName = role.getName();

            Object[] row = {roleName};
            tableModel3.addRow(row);


        }

        table3 = new JTable(tableModel3);

        table3.setBackground(Color.WHITE);
        table3.setRowHeight(40);

        table3.setIntercellSpacing(new Dimension(0, 5));

        // Creazione del renderer personalizzato per la riga di intestazione

        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Imposta l'allineamento al centro del testo

// Applicazione del renderer personalizzato alla riga di intestazione
        table3.getTableHeader().setDefaultRenderer(headerRenderer);

// Utilizzo del renderer personalizzato
        CustomTableCellRenderer cellRenderer = new CustomTableCellRenderer();
        table3.setDefaultRenderer(Object.class, cellRenderer);

        table3.setEnabled(false);

        scrollPaneTable3 = new JScrollPane(table3);

        ruoliCreatiButton = new JButton("Visualizza Ruoli Creati nell'Azienda");
        ruoliCreatiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoRuoliCreati = new JFrame();

                infoRuoliCreati.setSize(new Dimension(screenWidth/2, screenHeight/2));

                infoRuoliCreati.add(scrollPaneTable3);
                infoRuoliCreati.setVisible(true);

                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/roleView.png"));

                // Imposta l'icona del JFrame
                infoRuoliCreati.setIconImage(icon.getImage());
                infoRuoliCreati.setTitle("Informazioni Ruoli Azienda");

            }
        });

    }

    private void gestionePannelloTabella(){

        tablesPanelButton = new JPanel(new BorderLayout(0, 0));
        tablesPanelButton.setBackground(Color.WHITE);
        tablesPanelButton.setLayout(new BoxLayout(tablesPanelButton, BoxLayout.Y_AXIS));

        tablesPanelButton.add(ruoliCreatiButton);
        tablesPanelButton.add(impiegatiButton);
        tablesPanelButton.add(ruoliConsentiniButton);


        toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setFloatable(false); // Impedisce il trascinamento della barra degli strumenti
        toolbar.setBackground(Color.WHITE); // Imposta il colore di sfondo




        toolbar.addSeparator();
        toolbar.add(ruoliConsentiniButton);
        toolbar.addSeparator();
        toolbar.add(impiegatiButton);
        toolbar.addSeparator();
        toolbar.add(ruoliCreatiButton);
        toolbar.addSeparator();
        toolbar.add(new JSeparator(SwingConstants.HORIZONTAL));




    }

    private JButton printStats;
    private JFrame statsFrame;
    public void  displayStatistics() {
        printStats = new JButton("Visualizza Statistiche");
        toolbar.add(printStats);
        toolbar.addSeparator();

        printStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                statsFrame = new JFrame();

                statsFrame.setSize(new Dimension(screenWidth/2, screenHeight/2));

                //statsFrame.add(scrollPaneTable3);
                statsFrame.setVisible(true);

                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/roleView.png"));

                // Imposta l'icona del JFrame
                statsFrame.setIconImage(icon.getImage());
                statsFrame.setTitle("Statistiche Organigramma");

                StatsVisitor visitor = new StatsVisitor();
                model.getOrganigramma().accept(visitor);

                JPanel statsPane = new JPanel();


                StringBuilder statistics = new StringBuilder();
                statistics.append("Totale Unità: ").append(visitor.getTotalUnits()).append("\n");
                statistics.append("Totale Impiegati: ").append(visitor.getTotalEmployees()).append("\n");
                statistics.append("Totale Ruoli: ").append(visitor.getTotalRoles()).append("\n");


                statsPane.removeAll();
                // Creazione dell'area di testo per le statistiche
                JTextArea statisticsArea = new JTextArea(statistics.toString());
                statisticsArea.setEditable(false);
                statisticsArea.setLineWrap(true);
                statisticsArea.setWrapStyleWord(true);
                statisticsArea.setMargin(new Insets(10, 10, 10, 10)); // Imposta i margini desiderati
                statisticsArea.setFont(new Font(Font.SERIF, Font.PLAIN, 12)); // Imposta il font come Serif
                statisticsArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Imposta uno spazio verticale tra le linee

                JScrollPane scrollStats = new JScrollPane(statisticsArea);

                statsPane.add(scrollStats);
                statsPane.revalidate();
                statsPane.repaint();

                statsFrame.add(statsPane);

            }
        });

    }

    private JButton stampaAzienda;
    private JFrame stampaFrame;
    public void  stampaInfoAzienda() {
        stampaAzienda = new JButton("Stampa Info Azienda");
        toolbar.add(stampaAzienda);
        toolbar.addSeparator();

        stampaAzienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stampaFrame = new JFrame();

                stampaFrame.setLayout(new GridLayout());


                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/roleView.png"));

                // Imposta l'icona del JFrame
                stampaFrame.setIconImage(icon.getImage());
                stampaFrame.setTitle("Statistiche Organigramma");


                PrintVisitor printVisitor = new PrintVisitor();
                StringBuilder stringBuilder = new StringBuilder();
                model.getOrganigramma().accept(printVisitor);

                String componentInfo = printVisitor.getSb().toString();
                System.out.println("<html>" + componentInfo + "</html>");

                JTextArea textArea = new JTextArea(componentInfo.toString());
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                JPanel stampaPanel = new JPanel();
                stampaPanel.add(scrollPane);

                stampaFrame.setTitle("Stampa Info Organigramma");
                stampaFrame.getContentPane().add(stampaPanel);
                stampaFrame.pack();
                stampaFrame.setVisible(true);

            }
        });

    }




    public void salvataggioDati(){

        toolbar.add(new JSeparator(SwingConstants.HORIZONTAL));
        saveData = new JButton("Salva Organigramma");
        toolbar.add(saveData);
        toolbar.addSeparator();



        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salva Organigramma"); // Titolo della finestra di dialogo
                fileChooser.setFileFilter(new FileNameExtensionFilter("File JSON (*.json)", "json")); // Filtra solo i file JSON
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    // Aggiungi l'estensione .json se non è presente
                    if (!filePath.endsWith(".json")) {
                        filePath += ".json";
                    }

                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        // Salvataggio dell'organigramma nel file selezionato
                        objectMapper.writeValue(new File(filePath), organigrammaPanel.getModel().getAzienda());
                        System.out.println("Organigramma salvato correttamente.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Errore durante il salvataggio dell'organigramma.");
                    }
                }
            }
        });
    }

    public void ripristinoDati(){

        loadData = new JButton("Carica Organigramma");
        toolbar.add(loadData);
        toolbar.addSeparator();

        loadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Carica Organigramma"); // Titolo della finestra di dialogo
                fileChooser.setFileFilter(new FileNameExtensionFilter("File JSON (*.json)", "json")); // Filtra solo i file JSON
                int userSelection = fileChooser.showOpenDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToLoad = fileChooser.getSelectedFile();
                    String filePath = fileToLoad.getAbsolutePath();

                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        // Caricamento dell'organigramma dal file selezionato
                        Azienda aziendaCaricata = objectMapper.readValue(new File(filePath), Azienda.class);

                        // Aggiorna il modello dell'organigramma con l'organigramma caricato

                        model = new OrganigrammaModel(aziendaCaricata.getRootUnit(), aziendaCaricata);
                        setModel(model);

                        inizializzaFrame();
                        // ...
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }




    private JFrame helpFrame;

    public void helpButton(){

        helpButton = new JButton("Guida all'utilizzo");
        toolbar.add(helpButton);
        toolbar.addSeparator();

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                helpFrame = new JFrame();

                helpFrame.setLayout(new GridLayout());


                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/roleView.png"));

                // Imposta l'icona del JFrame
                helpFrame.setIconImage(icon.getImage());
                helpFrame.setTitle("Istruzioni d'utilizzo");

                InputStream file = getClass().getClassLoader().getResourceAsStream("res/file/istruzioni.txt");
                String text = null;
                try {
                    text = loadTextFromFile(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(text);

                JEditorPane textArea  = new JEditorPane();
                textArea.setContentType("text/html");
                textArea.setText(text);
                textArea.setEditable(false);


                JScrollPane scrollPane = new JScrollPane(textArea);

                JPanel stampaPanel = new JPanel();
                scrollPane.setPreferredSize(new Dimension(800, 800));

                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                textArea.setCaretPosition(0);
                // Imposta la posizione di scorrimento verticale all'inizio
                scrollPane.getVerticalScrollBar().setValue(0);
                stampaPanel.add(scrollPane);

                helpFrame.setTitle("Istruzioni di utilizzo");
                helpFrame.getContentPane().add(stampaPanel);
                helpFrame.pack();
                helpFrame.setVisible(true);

            }
        });

    }

    public static String loadTextFromFile(InputStream file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }

    public void azzeraOrganigramma(){

        clearButton = new JButton("Azzera Organigramma");
        toolbar.add(clearButton);
        toolbar.addSeparator();

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Azienda aziendaCaricata = new Azienda(new Unit("Azienda"));

                // Aggiorna il modello dell'organigramma con l'organigramma caricato

                model = new OrganigrammaModel(aziendaCaricata.getRootUnit(), aziendaCaricata);
                setModel(model);

                inizializzaFrame();

            }
        });
    }



    private void gestioneActionBar(){

        ActionPanel actionPanel = new ActionPanel(organigrammaTree, organigrammaPanel, this);
        frame.add(actionPanel, BorderLayout.NORTH);
        // Carica l'icona dell'applicazione da un file
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("res/image/azienda.png"));

        // Imposta l'icona del JFrame
        frame.setIconImage(icon.getImage());
        frame.setTitle("Organigramma Aziendale");


        JScrollPane toolBarPane = new JScrollPane(toolbar);

        frame.add(toolBarPane, BorderLayout.EAST);


        frame.setVisible(true);

    }






}
