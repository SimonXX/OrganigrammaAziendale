package ObserverGUI;


import ObserverGUI.view.OrganigrammaPanel;
import ObserverGUI.view.OrganigrammaTree;
import ObserverGUI.view.OrganigrammaView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Composite.Unit;
import Composite.Employee;
import Composite.Role;
import java.util.List;

public class ActionPanel extends JPanel {

    public ActionPanel(OrganigrammaTree organigrammaTree, OrganigrammaPanel organigrammaPanel, OrganigrammaView view) {

        setPreferredSize(new Dimension(1800, 50));

        try {
            // Applica il look and feel Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crea il bordo delinato per il pannello
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create the buttons with modern style
        JButton editUnit = new JButton("Modifica Nome Unità");

        JButton addSubUnit = new JButton("Aggiungi Sotto Unità");

        JButton removeSubUnit = new JButton("Rimuovi Unità");

        JButton createEmployee = new JButton("Aggiungi Impiegato Azienda");
        JButton removeEmployee = new JButton("Rimuovi Impiegato Azienda");

        JButton assignEmployee = new JButton("Assegna Impiegato ad Unità");
        JButton removeAssignEmployee = new JButton("Rimuovi Impiegato da Unità");

        JButton createRole = new JButton("Crea Ruolo");
        JButton removeRole = new JButton("Rimuovi Ruolo");

        JButton assignRoleUnit = new JButton("Assegna Ruolo ad Unità");
        JButton removeAssignRoleUnit = new JButton("Rimuovi Ruolo da Unità");




        // Personalizza lo stile dei bottoni
        Font buttonFont = new Font("Serif", Font.ROMAN_BASELINE, 18);
        Color buttonTextColor = Color.BLACK;

        for (JButton button : new JButton[]{editUnit, addSubUnit, createEmployee, assignEmployee, createRole, assignRoleUnit, removeSubUnit, removeEmployee, removeAssignEmployee, removeRole, removeAssignRoleUnit}) {
            button.setFont(buttonFont);
            button.setContentAreaFilled(false); // Rimuovi il riempimento del bottone
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
            button.setBorderPainted(false); // Rimuovi il contorno del bottone
            button.setOpaque(false); // Rendi trasparente il bottone

            button.setPreferredSize(new Dimension(button.getPreferredSize().width, button.getPreferredSize().height));
        }


        setLayout(new GridLayout());

        // Configurazione del layout manager
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;


        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false); // Impedisce il trascinamento della barra degli strumenti

        // Creazione del menu a tendina
        JPopupMenu menu = new JPopupMenu();

        // Aggiunta dei bottoni al menu a tendina


        // Aggiunta del listener per aprire il menu a tendina al click sulla toolbar
        toolbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu.show(evt.getComponent(), 0, toolbar.getHeight());
            }
        });



        add(toolbar);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Barra Strumenti Organigramma");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16)); // Imposta il font del titolo
        toolbar.setBorder(titledBorder);

        // Aggiungi i bottoni al pannello
        menu.addSeparator();
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(editUnit);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(addSubUnit);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(removeSubUnit);
        menu.addSeparator();
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(createEmployee);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(removeEmployee);
        menu.addSeparator();
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(assignEmployee);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(removeAssignEmployee);
        menu.addSeparator();
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(createRole);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(removeRole);
        menu.addSeparator();
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(assignRoleUnit);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(removeAssignRoleUnit);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));


        // Add action listeners to the buttons
        editUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Ottenere l'elenco delle unità dall'azienda
                List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                String[] unitNames = new String[units.size()];

                for (int i = 0; i < units.size(); i++) {
                    unitNames[i] = units.get(i).getName();
                }

                // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                String selectedUnitName = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleziona unità da modificare:",
                        "Modifica unità",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        unitNames,
                        unitNames[0]
                );

                if (selectedUnitName != null) {
                    // Ottenere l'unità selezionata dall'azienda
                    Unit selectedUnit =  organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                    if (selectedUnit != null) {
                        // Mostrare un JOptionPane per inserire il nuovo nome dell'unità
                        String newUnitName = JOptionPane.showInputDialog(
                                null,
                                "Inserisci il nuovo nome per l'unità:",
                                "Modifica unità",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (newUnitName != null && !newUnitName.isEmpty()) {
                            // Modificare il nome dell'unità selezionata

                            if(organigrammaPanel.getModel().getAzienda().getNomiUtilizzati().contains(newUnitName))
                                JOptionPane.showMessageDialog(null, "Evitare ominimia tra Unità, Impiegati e Ruoli");

                            else {

                                selectedUnit.setName(newUnitName);
                                organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                            }
                        }
                    }
                }

                view.inizializzaFrame();
            }
        });
///**/


        addSubUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Ottenere l'elenco delle unità dall'azienda
                List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                String[] unitNames = new String[units.size()];

                for (int i = 0; i < units.size(); i++) {
                    unitNames[i] = units.get(i).getName();
                }

                // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                String selectedUnitName = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleziona unità a cui aggiungere sottounità:",
                        "Aggiungi Sottounità",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        unitNames,
                        unitNames[0]
                );

                if (selectedUnitName != null) {
                    // Ottenere l'unità selezionata dall'azienda
                    Unit selectedUnit =  organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                    if (selectedUnit != null) {
                        // Mostrare un JOptionPane per inserire il nome dell nuova sotto unità
                        String newUnitName = JOptionPane.showInputDialog(
                                null,
                                "Inserisci il nuovo nome per la sottounità:",
                                "Aggiungi Sottounità",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (newUnitName != null && !newUnitName.isEmpty()) {
                            // Modificare il nome dell'unità selezionata

                            if(organigrammaPanel.getModel().getAzienda().getNomiUtilizzati().contains(newUnitName))
                                JOptionPane.showMessageDialog(null, "Evitare ominimia tra Unità, Impiegati e Ruoli");

                            else {
                                Unit newUnit = new Unit(newUnitName);
                                selectedUnit.addSubUnit(newUnit);
                                organigrammaPanel.getModel().getAzienda().addUnit(newUnit);
                                organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                            }
                        }
                    }
                }

                view.inizializzaFrame();

            }
        });


        removeSubUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Ottenere l'elenco delle unità dall'azienda
                List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                String[] unitNames = new String[units.size()];

                for (int i = 0; i < units.size(); i++) {
                    unitNames[i] = units.get(i).getName();
                }

                // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                String selectedUnitName = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleziona un'unità da rimuovere:",
                        "Elimina Unità",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        unitNames,
                        unitNames[0]
                );

                if (selectedUnitName != null) {
                    // Ottenere l'unità selezionata dall'azienda
                    Unit selectedUnit =  organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                    if (selectedUnit != null) {


                        if(!selectedUnit.hasSubUnits()){
                            organigrammaPanel.getModel().getAzienda().removeUnit(selectedUnit);
                            selectedUnit.deleteByParent();
                            organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                            organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                        }else{
                            JOptionPane.showMessageDialog(null, "È possibile rimuovere solo sottounità foglia");

                        }


                    }
                }

                view.inizializzaFrame();

            }
        });

        createEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for createEmployee
                String employeeName = JOptionPane.showInputDialog(organigrammaPanel, "Inserisci un nome per l'impiegato:");
                Employee employee = new Employee(employeeName);

                if(organigrammaPanel.getModel().getAzienda().getNomiUtilizzati().contains(employee.getName()))
                    JOptionPane.showMessageDialog(null, "Evitare ominimia tra Unità, Impiegati e Ruoli");

                else {
                    organigrammaPanel.getModel().getAzienda().addEmployee(employee);
                    organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                    organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                    view.inizializzaFrame();
                }



            }
        });

        removeEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Apri il menu a tendina degli impiegati
                String[] employeeNames = organigrammaPanel.getModel().getAzienda().getEmployeeNames().toArray(new String[0]);
                JComboBox<String> employeeComboBox = new JComboBox<>(employeeNames);
                int result1 = JOptionPane.showOptionDialog(
                        null,
                        employeeComboBox,
                        "Rimuovi Impiegato",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result1 == JOptionPane.OK_OPTION) {
                    String selectedEmployeeName = (String) employeeComboBox.getSelectedItem();
                    Employee selectedEmployee = organigrammaPanel.getModel().getAzienda().findEmployeeByName(selectedEmployeeName);

                    organigrammaPanel.getModel().getAzienda().removeEmployee(selectedEmployee);
                    selectedEmployee.deleteByEmployee();

                    organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                    organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                    view.inizializzaFrame();


                }

            }
        });


        assignEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for assignEmployee

                // Apri il menu a tendina degli impiegati
                String[] employeeNames = organigrammaPanel.getModel().getAzienda().getEmployeeNames().toArray(new String[0]);
                JComboBox<String> employeeComboBox = new JComboBox<>(employeeNames);
                int result1 = JOptionPane.showOptionDialog(
                        null,
                        employeeComboBox,
                        "Seleziona un impiegato da assegnare ad una unità",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );



                if (result1 == JOptionPane.OK_OPTION) {
                    String selectedEmployeeName = (String) employeeComboBox.getSelectedItem();
                    Employee selectedEmployee = organigrammaPanel.getModel().getAzienda().findEmployeeByName(selectedEmployeeName);

                    // Ottenere l'elenco delle unità dall'azienda
                    List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                    String[] unitNames = new String[units.size()];

                    for (int i = 0; i < units.size(); i++) {
                        unitNames[i] = units.get(i).getName();
                    }

                    // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                    String selectedUnitName = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleziona un'unità:",
                            "Assegna Impiegato ad Unità",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            unitNames,
                            unitNames[0]
                    );

                    if (selectedUnitName != null) {
                        // Ottenere l'unità selezionata dall'azienda
                        Unit selectedUnit =  organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);



                        if (selectedUnit != null) {

                            if(selectedUnit.getAllowedRoles().isEmpty()){
                                JOptionPane.showMessageDialog(null, "Assegnare Ruoli all'Unità al fine di assegnarvi dipendenti");

                            }else{
                                // Mostrare un JOptionPane per selezionare un ruolo
                                List<Role> roleNames = selectedUnit.getAllowedRoles();
                                String[] roleNamesArray = new String[roleNames.size()];

                                for (int i = 0; i < roleNames.size(); i++) {
                                    roleNamesArray[i] = roleNames.get(i).getName();
                                }
                                String selectedRoleName = (String) JOptionPane.showInputDialog(
                                        //GENERA ERRORE NELLE UNITà CHE NON DISPONGONO DI RUOLI AMMESSI
                                        null,
                                        "Seleziona un ruolo:",
                                        "Assegna Ruolo ad Impiegato",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        roleNamesArray,
                                        roleNamesArray[0]
                                );

                                if (selectedRoleName != null) {
                                    // Ottenere il ruolo selezionato dall'azienda
                                    Role selectedRole = organigrammaPanel.getModel().getAzienda().findRoleByName(selectedRoleName);

                                    if (selectedRole != null) {



                                        // Assegna l'impiegato all'unità e al ruolo
                                        // selectedUnit.addEmployeeRole(selectedEmployee, selectedRole);
                                        selectedUnit.addEmployee(selectedEmployee);
                                        selectedEmployee.addRole(selectedRole);

                                        selectedUnit.addEmployeeRole(selectedEmployee.getName(), selectedRole.getName());


                                        organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                        organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                        view.inizializzaFrame();}



                                }

                            }




                        }
                    }
                }

            }
        });

        removeAssignEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for assignEmployee

                // Apri il menu a tendina degli impiegati
                String[] employeeNames = organigrammaPanel.getModel().getAzienda().getEmployeeNames().toArray(new String[0]);
                JComboBox<String> employeeComboBox = new JComboBox<>(employeeNames);
                int result1 = JOptionPane.showOptionDialog(
                        null,
                        employeeComboBox,
                        "Rimuovi Impiegato da Unità",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );



                if (result1 == JOptionPane.OK_OPTION) {
                    String selectedEmployeeName = (String) employeeComboBox.getSelectedItem();
                    Employee selectedEmployee = organigrammaPanel.getModel().getAzienda().findEmployeeByName(selectedEmployeeName);

                    // Ottenere l'elenco delle unità dall'azienda
                    List<Unit> units = selectedEmployee.getUnits();

                    if(units.isEmpty()){
                        JOptionPane.showMessageDialog(null, "L'impiegato non è assegnato ad alcuna unità");

                    }else{
                        String[] unitNames = new String[units.size()];

                        for (int i = 0; i < units.size(); i++) {
                            unitNames[i] = units.get(i).getName();
                        }

                        // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                        String selectedUnitName = (String) JOptionPane.showInputDialog(
                                null,
                                "Seleziona un'unità:",
                                "Rimuovi assegnamento Impiegato da Unità",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                unitNames,
                                unitNames[0]
                        );

                        if (selectedUnitName != null) {
                            // Ottenere l'unità selezionata dall'azienda
                            Unit selectedUnit =  organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                            if (selectedUnit != null) {
                                // Mostrare un JOptionPane per inserire il nome dell nuova sotto unità

                                selectedUnit.removeEmployee(selectedEmployee);


                                organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                view.inizializzaFrame();

                            }
                        }
                    }

                }

            }
        });

        createRole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for assignEmployee
                String roleName = JOptionPane.showInputDialog(organigrammaPanel, "Inserisci un nome per il ruolo");

                Role role = new Role(roleName);

                if(organigrammaPanel.getModel().getAzienda().getNomiUtilizzati().contains(role.getName()))
                    JOptionPane.showMessageDialog(null, "Evitare ominimia tra Unità, Impiegati e Ruoli");

                else {
                    organigrammaPanel.getModel().getAzienda().addRole(role);
                    organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                    view.inizializzaFrame();

                }

            }
        });

        removeRole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Apri il menu a tendina dei ruoli
                String[] roleNames = organigrammaPanel.getModel().getAzienda().getRoleNames();
                JComboBox<String> roleComboBox = new JComboBox<>(roleNames);
                int result2 = JOptionPane.showOptionDialog(
                        null,
                        roleComboBox,
                        "Seleziona un ruolo da rimuovere",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result2 == JOptionPane.OK_OPTION) {
                    String selectedRoleName = (String) roleComboBox.getSelectedItem();
                    Role selectedRole = organigrammaPanel.getModel().getAzienda().findRoleByName(selectedRoleName);

                    organigrammaPanel.getModel().getAzienda().removeRole(selectedRole);
                    organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                    organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                    view.inizializzaFrame();

                }



            }
        });

        assignRoleUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottenere l'elenco delle unità dall'azienda
                List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                String[] unitNames = new String[units.size()];

                for (int i = 0; i < units.size(); i++) {
                    unitNames[i] = units.get(i).getName();
                }

                // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                String selectedUnitName = (String) JOptionPane.showInputDialog(
                        organigrammaPanel,
                        "Seleziona un'unità:",
                        "Assegna Ruolo ad Unità",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        unitNames,
                        unitNames[0]
                );

                if (selectedUnitName != null) {
                    // Ottenere l'unità selezionata dall'azienda
                    Unit selectedUnit = organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                    if (selectedUnit != null) {
                        // Apri il menu a tendina dei ruoli
                        String[] roleNames = organigrammaPanel.getModel().getAzienda().getRoleNames();
                        JComboBox<String> roleComboBox = new JComboBox<>(roleNames);
                        int result = JOptionPane.showOptionDialog(
                                organigrammaPanel,
                                roleComboBox,
                                "Seleziona un ruolo",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                null
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String selectedRoleName = (String) roleComboBox.getSelectedItem();
                            Role selectedRole = organigrammaPanel.getModel().getAzienda().findRoleByName(selectedRoleName);

                            if (selectedRole != null) {
                                // Assegna il ruolo all'unità selezionata
                                selectedUnit.addAllowedRole(selectedRole);
                                organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                                organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                view.inizializzaFrame();

                            }
                        }
                    }
                }
            }
        });

        removeAssignRoleUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottenere l'elenco delle unità dall'azienda
                List<Unit> units = organigrammaPanel.getModel().getAzienda().getUnits();
                String[] unitNames = new String[units.size()];

                for (int i = 0; i < units.size(); i++) {
                    unitNames[i] = units.get(i).getName();
                }

                // Mostrare un JOptionPane con un menu a tendina per selezionare l'unità
                String selectedUnitName = (String) JOptionPane.showInputDialog(
                        organigrammaPanel,
                        "Seleziona un'unità:",
                        "Rimuovi Ruolo da Unità",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        unitNames,
                        unitNames[0]
                );

                if (selectedUnitName != null) {
                    // Ottenere l'unità selezionata dall'azienda
                    Unit selectedUnit = organigrammaPanel.getModel().getAzienda().findUnitByName(selectedUnitName);

                    if (selectedUnit != null) {
                        // Apri il menu a tendina dei ruoli
                        String[] roleNames = selectedUnit.getAllowedRoleNames();
                        JComboBox<String> roleComboBox = new JComboBox<>(roleNames);
                        int result = JOptionPane.showOptionDialog(
                                organigrammaPanel,
                                roleComboBox,
                                "Seleziona un ruolo da rimuovere",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                null
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String selectedRoleName = (String) roleComboBox.getSelectedItem();
                            Role selectedRole = organigrammaPanel.getModel().getAzienda().findRoleByName(selectedRoleName);

                            if (selectedRole != null) {
                                // Assegna il ruolo all'unità selezionata
                                selectedUnit.removeAllowedRole(selectedRole);
                                organigrammaPanel.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());

                                organigrammaTree.updateOrganigramma(organigrammaPanel.getModel().getOrganigramma());
                                view.inizializzaFrame();

                            }
                        }
                    }
                }
            }
        });




    }


}
