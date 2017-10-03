package IMSApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sGUI {
    private JLabel outputText;
    private JFrame cards;
    private JButton back;
    private GridLayout layout = new GridLayout(0,1);
    private CardLayout cardPattern = new CardLayout();

    public static void main(String[] args) {
    }

    public void doGui(){
        cards = new JFrame();
        JPanel home = new JPanel();

        cards.setSize(400, 600);

        JLabel tLabel = new JLabel("Country Craftsman Inventory System", SwingConstants.CENTER);

        JButton madeButton = new JButton("I made something");
        madeButton.setActionCommand("made");
        madeButton.addActionListener(new ButtonClickListener());

        JButton soldButton = new JButton("I sold something");
        soldButton.setActionCommand("sold");
        soldButton.addActionListener(new ButtonClickListener());

        JButton viewButton = new JButton("View/Print records");
        viewButton.setActionCommand("view");
        viewButton.addActionListener(new ButtonClickListener());

        JButton updateButton = new JButton("Update a record");
        updateButton.setActionCommand("update");
        updateButton.addActionListener(new ButtonClickListener());

        JButton exportButton = new JButton("Export to Excel");
        exportButton.setActionCommand("export");
        exportButton.addActionListener(new ButtonClickListener());

        outputText = new JLabel("", SwingConstants.CENTER);
        JPanel addCard = new JPanel();
        JPanel salesCard = new JPanel();
        JPanel updateCard = new JPanel();
        JPanel viewCard = new JPanel();
        JPanel exportCard = new JPanel();
        this.cards.setLayout(cardPattern);
        this.cards.setSize(800, 500);
        home.setLayout(layout);
        home.add(tLabel);
        home.add(madeButton);
        home.add(soldButton);
        home.add(viewButton);
        home.add(updateButton);
        home.add(exportButton);
        home.add(outputText);
        this.cards.add(home);
        this.cards.add(addCard);
        this.cards.add(salesCard);
        this.cards.add(updateCard);
        this.cards.add(viewCard);
        this.cards.add(exportCard);
        this.cards.setVisible(true);

    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if(command.equals( "made")){
                newRecord();
            } else if(command.equals("sold")){
                saleRecord();
            } else if(command.equals( "view")){
                seeRecord();
            } else if(command.equals("update")) {
                updateRecord();
            } else if(command.equals("export")){
                exportRecord();
            } else if(command.equals("back")){
                goBack();
            }else {
                outputText.setText("unknown command");
            }
        }
        private void saleRecord() {
            outputText.setText("sold");
        } //TODO updates for sold

        private void updateRecord() {
            outputText.setText("update");
        }

        private void newRecord() {
            outputText.setText("new");
        } //TODO initial adding

        private void seeRecord(){
            outputText.setText("seen");
        } //TODO prints reports

        private void exportRecord(){
            outputText.setText("export");
        }

        private void goBack(){
            outputText.setText("back");
        }
    }
}
