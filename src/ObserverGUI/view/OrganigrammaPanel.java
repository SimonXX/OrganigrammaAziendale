package ObserverGUI.view;


import ObserverGUI.OrganigrammaModel;

import javax.swing.*;
import java.awt.*;
import Composite.Unit;

public class OrganigrammaPanel extends JPanel {


    private int screenWidth;
    private int screenHeight;

    OrganigrammaModel model;
    private Rectangle selectedRectangle; // Rettangolo selezionato per lo spostamento


    private Unit selectedUnit;  // L'unità attualmente selezionata
    private int offsetX, offsetY;  // Offset per calcolare la posizione del mouse durante il trascinamento

    public Unit getSelectedUnit(){
        return selectedUnit;
    }


    public OrganigrammaModel getModel(){
        return model;
    }

    public OrganigrammaPanel(OrganigrammaModel model, int screenWidth, int screenHeight){

        this.model = model;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        setBackground(new Color(255, 255, 250));



    }



    public void updateOrganigramma(Unit newOrganigramma) {
        // Aggiorna la struttura dell'organigramma con l'unità aggiornata
        // Richiama il metodo calculateUnitRectangles per ricalcolare i rettangoli

        model.setOrganigramma(newOrganigramma);


        repaint();
        revalidate();

    }
    private void drawUnit(Graphics g, Unit unit, int x, int y) {
        // Disegna il rettangolo con il nome dell'unità
        FontMetrics fm = g.getFontMetrics();
        int nameWidth = fm.stringWidth(unit.getName());
        int width = Math.max(nameWidth + 40, 100); // Larghezza minima del rettangolo: 100 pixel
        int height = fm.getHeight() + 20; // Altezza del rettangolo

        // Imposta i colori per l'ombreggiatura del rettangolo
        Color lightColor = new Color(210, 240, 220); // Colore chiaro per l'ombra
        Color darkColor = new Color(160, 210, 180); // Colore scuro per l'ombra

        // Disegna l'ombreggiatura del rettangolo
        g.setColor(lightColor);
        g.fillRoundRect(x - width / 2 + 4, y - height / 2 + 4, width, height, 20, 20); // Rettangolo chiaro leggermente spostato
        g.setColor(darkColor);
        g.fillRoundRect(x - width / 2, y - height / 2, width, height, 20, 20); // Rettangolo scuro principale

        // Imposta il colore del contorno del rettangolo
        g.setColor(Color.BLACK);
        g.drawRoundRect(x - width / 2, y - height / 2, width, height, 20, 20); // Utilizza drawRoundRect per ottenere bordi arrotondati

        // Imposta il font per il testo
        Font font = new Font("Serif", Font.BOLD, 12);
        g.setFont(font);

        // Adatta il testo alla larghezza del rettangolo
        String name = unit.getName();
        if (nameWidth > width - 20) { // Riduce la dimensione del font se il testo supera la larghezza del rettangolo
            int fontSize = 12;
            while (fm.stringWidth(name) > width - 20 && fontSize > 6) {
                fontSize--;
                font = new Font("Serif", Font.BOLD, fontSize);
                g.setFont(font);
                fm = g.getFontMetrics();
            }
        }

        // Disegna il nome dell'unità
        int textX = x - fm.stringWidth(name) / 2;
        int textY = y - fm.getHeight() / 2 + fm.getAscent();
        g.drawString(name, textX, textY);

        // Disegna le sotto-unità e le frecce
        // Aumenta lo spazio verticale tra le sotto-unità
        int subUnitY = y + 200; // Posizione verticale delle sotto-unità

        // Aumenta lo spazio orizzontale tra le sotto-unità
        int subUnitGap = 200; // Spazio orizzontale tra le sotto-unità

        // Calcola lo spazio orizzontale tra i rettangoli nella stessa riga
        int rowGap = Math.max(subUnitGap * (unit.getSubUnits().size() - 1), 0);

        // Calcola l'inizio della riga in base alla larghezza complessiva dei rettangoli
        int rowStartX = x - rowGap / 2;

        for (Unit subUnit : unit.getSubUnits()) {
            int subUnitX = rowStartX + subUnitGap * unit.getSubUnits().indexOf(subUnit);

            // Disegna la freccia dalla sotto-unità alla sua unità genitore
            g.drawLine(x, y + height / 2, subUnitX, subUnitY - height / 2);

            // Disegna la sotto-unità ricorsivamente
            drawUnit(g, subUnit, subUnitX, subUnitY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawUnit(g, model.getOrganigramma(), getWidth() / 2, 50);

//        }

    }





}
