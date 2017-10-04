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
        cards.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        superPanel = new JPanel(new CardLayout());

        cards.setSize(400, 600);


        JPanel salesCard = new JPanel();
        JPanel updateCard = new JPanel();
        JPanel viewCard = new JPanel();
        JPanel exportCard = new JPanel();

        cards.setLayout(cardPattern);
        cards.setSize(800, 500);


        superPanel.add(home(), "home");
        superPanel.add(addCard(), "add");
        superPanel.add(salesCard, "sold");
        superPanel.add(updateCard, "update");
        superPanel.add(viewCard, "view");
        superPanel.add(exportCard, "export");

        cards.add(superPanel, BorderLayout.CENTER);
        cards.pack();
        cards.setVisible(true);

    }
    public JPanel home(){
        JPanel home = new JPanel();
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


        home.setLayout(layout);
        home.add(tLabel);
        home.add(madeButton);
        home.add(soldButton);
        home.add(viewButton);
        home.add(updateButton);
        home.add(exportButton);
        home.add(outputText);
        return home;
    }
    private JPanel addCard(){
        JPanel addCard = new JPanel();
        addCard.setBorder(BorderFactory.createEtchedBorder());
        GridLayout addLayout = new GridLayout(2, 4);

        JLabel id = new JLabel("Product ID");
        JLabel desc = new JLabel("Description");
        JLabel cogs = new JLabel("COGS");
        JLabel DateMade = new JLabel("Date Made");

        JTextField idText = new JTextField("ProductID", 1);
        JTextField descText = new JTextField(1);
        JTextField cogsText = new JTextField(1);
        JTextField DateMadeText = new JTextField("Date Made", 1);

        addCard.add(id);
        addCard.add(desc);
        addCard.add(cogs);
        addCard.add(DateMade);

        addCard.add(idText);
        addCard.add(descText);
        addCard.add(cogsText);
        addCard.add(DateMadeText);

        addCard.setLayout(addLayout);

        return addCard;
    }
    public JPanel salesCard(){return null;}
    public JPanel updateCard(){return null;}
    public JPanel viewCard(){return null;}
    public JPanel exportCard(){return null;}

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        }

    }
}
