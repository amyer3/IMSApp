package IMSApp;

import com.toedter.calendar.JCalendar;

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

        JPanel salesCard = new JPanel();
        JPanel updateCard = new JPanel();
        JPanel viewCard = new JPanel();
        JPanel exportCard = new JPanel();

        cards.setLayout(cardPattern);
        cards.setSize(500, 500);

        superPanel.add(home(), "home");
        superPanel.add(addCard(), "add");
        superPanel.add(salesCard(), "sold");
        superPanel.add(updateCard, "update");
        superPanel.add(viewCard, "view");
        superPanel.add(exportCard, "export");

        cards.add(superPanel, BorderLayout.CENTER);
        cards.pack();
        cards.setVisible(true);

    }
    private JPanel home(){
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
        GridLayout addLayout = new GridLayout(3, 4);

        JLabel id = new JLabel("Product ID");
        JLabel desc = new JLabel("Description");
        JLabel cogs = new JLabel("COGS");
        JLabel DateMade = new JLabel("Date Made");

        final JTextField idText = new JTextField("ProductID", 1);
        final JTextField descText = new JTextField(1);
        final JTextField cogsText = new JTextField(1);
        final JCalendar DateMadeText = new JCalendar();
        String[] values = null;
        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        JButton addButton = new JButton("Submit and Save");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] values = new String[4];
                    outputText.setText(DateMadeText.getDate().toString());
                    values[0] = idText.getText();
                    values[1] = descText.getText();
                    values[2] = cogsText.getText();
                    values[3] = DateMadeText.getDate().toString();

                }catch (Exception ex){
                    outputText.setText("All fields are required");
                }
                finally {
                    idText.setText("");
                }

            }
        });
        outputText = new JLabel();

        addCard.add(id);
        addCard.add(desc);
        addCard.add(cogs);
        addCard.add(DateMade);

        addCard.add(idText);
        addCard.add(descText);
        addCard.add(cogsText);
        addCard.add(DateMadeText);

        addCard.add(back);
        addCard.add(addButton);
        addCard.add(outputText);

        addCard.setLayout(addLayout);

        return addCard;
    }
    private JPanel salesCard(){
        JPanel salesCard = new JPanel();
        JLabel id = new JLabel("Product Id");
        JLabel saleDate = new JLabel("Date of Sale");
        JLabel salePrice = new JLabel("Sale Price");

        JTextField idText = new JTextField(1);
        JTextField saleDateText = new JTextField(1);
        JTextField salePriceText = new JTextField(1);


        return salesCard;}
    private JPanel updateCard(){return null;}
    private JPanel viewCard(){return null;}
    private JPanel exportCard(){return null;}


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        }

    }

}
