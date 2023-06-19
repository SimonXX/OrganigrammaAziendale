package ObserverGUI.view;


import Composite.Unit;
import Composite.Employee;
import ObserverGUI.OrganigrammaModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

import java.util.List;


public class OrganigrammaTree extends JPanel {

    public JTree organigrammaTree;
    OrganigrammaModel model;
    int screenWidth;

    int screenHeight;

    public OrganigrammaTree(OrganigrammaModel model, int screenWidth, int screenHeight) {

        this.model = model;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        createOrganigrammaTree();

        add(organigrammaTree);
    }

    public void updateOrganigramma(Unit newOrganigramma) {


        model.setOrganigramma(newOrganigramma);

        remove(organigrammaTree);

        createOrganigrammaTree();
        add(organigrammaTree);

        repaint();
        revalidate();

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



    }


    //CREA un JTREE con la struttura gerarchica dell'organigramma

    private void createOrganigrammaTree() {
        DefaultMutableTreeNode rootNode = createNode(model.getOrganigramma());
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        organigrammaTree = new JTree(treeModel);

        CustomTreeCellRenderer cellRenderer = new CustomTreeCellRenderer();
        // Impostazione delle icone per i diversi tipi di nodi

        Font customFont = new Font("Serif", Font.ROMAN_BASELINE ,14);


        //cellRenderer.setUnitIcon(new ImageIcon("res/image/module.png"), customFont);
        cellRenderer.setUnitIcon(new ImageIcon(getClass().getClassLoader().getResource("res/image/module.png")), customFont);

        //cellRenderer.setEmployeeIcon(new ImageIcon("employee.png"));

        organigrammaTree.setCellRenderer(cellRenderer);


        if(model.getOrganigramma().hasEmployees()){
            for (Employee e : model.getOrganigramma().getEmployees()){
                List<String> roles = model.getOrganigramma().getEmployeeRole().get(e.getName());
                DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Impiegato: " + e.getName() + " - Ruoli: " + roles);
                rootNode.add(employeeNode);
            }
        }
    }



    private DefaultMutableTreeNode createNode(Unit unit) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(unit.getName());




        if (unit.hasSubUnits()) {
            List<Unit> subUnits = unit.getSubUnits();


            for (Unit subUnit : subUnits) {
                DefaultMutableTreeNode childNode = createNode(subUnit);
                node.add(childNode);
            }

        }

        if(unit.hasEmployees()){//aggiunge eventuali impiegati al JTREE
            for (Employee e : unit.getEmployees()){

                List<String> roles = unit.getEmployeeRole().get(e.getName());

                DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Impiegato: " + e.getName() + " - Ruoli Unità: " + roles);//+ unit.getEmployeeRoleMap().get(e).getName());
                node.add(employeeNode);
            }
        }

        return node;
    }
    private static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private ImageIcon unitIcon;
        private ImageIcon employeeIcon;
        private Font customFont;

        public void setUnitIcon(ImageIcon icon, Font font) {
            unitIcon = icon;
            customFont = font;

        }

        public void setEmployeeIcon(ImageIcon icon) {
            employeeIcon = icon;
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);




            setIcon(unitIcon);
            setFont(customFont);


            return this;
        }
    }


}
