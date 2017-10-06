package IMSApp;

import com.toedter.calendar.JDateChooser;

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
    //Connection databaseConnection = Operations.firstRun();
    // TODO uncomment for full functionality

    public static void main(String[] args) {
    }

    public void doGui(){
        cards = new JFrame();
        cards.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        superPanel = new JPanel(new CardLayout());

        JPanel exportCard = new JPanel();

        cards.setLayout(cardPattern);
        cards.setSize(500, 500);

        superPanel.add(home(), "home");
        superPanel.add(addCard(), "add");
        superPanel.add(salesCard(), "sold");
        superPanel.add(updateCard(), "update");
        superPanel.add(viewCard(), "view");
        superPanel.add(exportCard, "export");

        cards.add(superPanel, BorderLayout.CENTER);
        cards.pack();
        cards.setVisible(true);

    }
    private JPanel home(){
        JPanel superPanel = new JPanel();
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
        superPanel.add(home);
        return superPanel;
    }
    private JPanel addCard(){
        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        JPanel status = new JPanel();
        final JPanel addCard = new JPanel(); // TODO Box or Spring Layout
        addCard.setBorder(BorderFactory.createEtchedBorder());
        buttons.setBorder(BorderFactory.createEtchedBorder());
        final GridLayout addLayout = new GridLayout(2, 5);

        JLabel id = new JLabel("Product ID", SwingConstants.CENTER);
        JLabel desc = new JLabel("Description", SwingConstants.CENTER);
        JLabel cogs = new JLabel("COGS", SwingConstants.CENTER);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.CENTER);
        outputText = new JLabel(" ", SwingConstants.CENTER);

        final JTextField idText = new JTextField(1);
        final JTextField descText = new JTextField(1);
        final JTextField cogsText = new JTextField(1);
        final JDateChooser DateMadeText = new JDateChooser();

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
                    //if(Operations.blankChecker(values)){
                        //DBHandler.addRec(databaseConnection,values);
                    //} else {
                       // throw new NullPointerException();
                   // }
                    //TODO uncomment for full functionality


                }catch (NullPointerException ex){
                    outputText.setText("All fields are required");
                    ex.printStackTrace();
                }
                finally {
                    idText.setText("");
                    descText.setText("");
                    cogsText.setText("");
                    DateMadeText.setDate(null);
                }

            }
        });

        addCard.add(id);
        addCard.add(desc);
        addCard.add(cogs);
        addCard.add(DateMade);

        addCard.add(idText);
        addCard.add(descText);
        addCard.add(cogsText);
        addCard.add(DateMadeText);

        buttons.add(back);
        buttons.add(addButton);
        status.add(outputText);

        addCard.setLayout(addLayout);

        superPanel.add(addCard, BorderLayout.NORTH);
        superPanel.add(buttons, BorderLayout.CENTER);
        superPanel.add(status, BorderLayout.AFTER_LAST_LINE);
        return superPanel;
    }
    private JPanel salesCard(){
        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BorderLayout());
        JPanel salesCard = new JPanel();
        salesCard.setLayout(new GridLayout(2,3));// TODO Box or Spring Layout
        JPanel buttons = new JPanel();

        JLabel id = new JLabel("Product Id", SwingConstants.CENTER);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.CENTER);
        JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        JTextField idText = new JTextField(1);
        JDateChooser saleDateText = new JDateChooser();
        JTextField salePriceText = new JTextField(1);

        JButton save = new JButton("Submit and Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); //Todo fill out action
        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        outputText = new JLabel();

        buttons.add(back);
        buttons.add(save);
        salesCard.add(id);
        salesCard.add(saleDate);
        salesCard.add(salePrice);
        salesCard.add(idText);
        salesCard.add(saleDateText);
        salesCard.add(salePriceText);

        superPanel.add(salesCard, BorderLayout.NORTH);
        superPanel.add(buttons, BorderLayout.SOUTH);
        return superPanel;
    }
    private JPanel updateCard(){
        final JPanel superPanel = new JPanel();
        superPanel.setLayout(new BorderLayout());
        final JPanel updateCard = new JPanel();
        updateCard.setLayout(new GridLayout(2, 6)); // TODO spring layout
        JPanel buttons = new JPanel();

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        JButton submit = new JButton("Submit and Save");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        }); // TODO fill out action listener


        JLabel id = new JLabel("Product ID to be Updated", SwingConstants.CENTER);
        JLabel desc = new JLabel("Description", SwingConstants.CENTER);
        JLabel cogs = new JLabel("COGS", SwingConstants.CENTER);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.CENTER);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.CENTER);
        JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        JTextField idText = new JTextField(1);
        JTextField descText = new JTextField(1);
        JTextField cogsText = new JTextField(1);
        JDateChooser DateMadeText = new JDateChooser();
        JDateChooser saleDateText = new JDateChooser();
        JTextField salePriceText = new JTextField(1);

        updateCard.add(id);
        updateCard.add(desc);
        updateCard.add(cogs);
        updateCard.add(DateMade);
        updateCard.add(saleDate);
        updateCard.add(salePrice);
        updateCard.add(idText);
        updateCard.add(descText);
        updateCard.add(cogsText);
        updateCard.add(DateMadeText);
        updateCard.add(saleDateText);
        updateCard.add(salePriceText);

        buttons.add(back);
        buttons.add(submit);


        superPanel.add(updateCard, BorderLayout.NORTH);
        superPanel.add(buttons, BorderLayout.SOUTH);
        return superPanel;
    }
    private JPanel viewCard(){
        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BorderLayout());
        JPanel query = new JPanel();
        JPanel buttons = new JPanel();
        JPanel results = new JPanel();

        JLabel instructions = new JLabel("Search individually by ID, or by date range", SwingConstants.CENTER);
        JLabel pID = new JLabel("Product ID", SwingConstants.CENTER);
        JLabel sold = new JLabel("Sold?", SwingConstants.CENTER);
        JLabel fromDate = new JLabel("From Date", SwingConstants.CENTER);
        JLabel toDate = new JLabel("To Date", SwingConstants.CENTER);

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        JButton lookup = new JButton("Look up item");
        lookup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton searchAgain = new JButton("New Search");
        searchAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });



        buttons.add(back);
        buttons.add(lookup);
        buttons.add(searchAgain);
        superPanel.add(query, BorderLayout.NORTH);
        superPanel.add(results, BorderLayout.CENTER);
        superPanel.add(buttons, BorderLayout.SOUTH);
        return superPanel;
    }
    private JPanel exportCard(){return null;}



    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        }

    }

}
