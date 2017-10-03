package IMSApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sGUI {
    JLabel outputText;
    JFrame cards;
    JButton back;
    GridLayout layout = new GridLayout(0,1);
    CardLayout cardPattern = new CardLayout();
    JPanel superPanel;

    public static void main(String[] args) {
    }

    public void doGui(){
        cards = new JFrame();
        Container panel = new Container();
        cards.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel home = new JPanel();
        superPanel = new JPanel(new CardLayout());

        cards.setSize(400, 600);

        JLabel tLabel = new JLabel("Country Craftsman Inventory System", SwingConstants.CENTER);

        JButton madeButton = new JButton("I made something");
        madeButton.setActionCommand("add");
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
        JTextField idField = new JTextField();
        addCard.add(idField);
        JPanel salesCard = new JPanel();
        JPanel updateCard = new JPanel();
        JPanel viewCard = new JPanel();
        JPanel exportCard = new JPanel();

        cards.setLayout(cardPattern);
        cards.setSize(800, 500);
        home.setLayout(layout);
        home.add(tLabel);
        home.add(madeButton);
        home.add(soldButton);
        home.add(viewButton);
        home.add(updateButton);
        home.add(exportButton);
        home.add(outputText);

        superPanel.add(home, "home");
        superPanel.add(addCard, "add");
        superPanel.add(salesCard, "sold");
        superPanel.add(updateCard, "update");
        superPanel.add(viewCard, "view");
        superPanel.add(exportCard, "export");

        cards.add(superPanel, BorderLayout.CENTER);
        cards.pack();
        cards.setVisible(true);

    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        }

    }
}
