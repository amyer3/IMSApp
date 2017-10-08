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
        outputText = new JLabel("", SwingConstants.CENTER);

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

        home.setLayout(layout);
        home.add(tLabel);
        home.add(madeButton);
        home.add(soldButton);
        home.add(viewButton);
        home.add(updateButton);
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
        final GridLayout addLayout = new GridLayout(5, 2);

        JLabel id = new JLabel("Product ID", SwingConstants.RIGHT);
        JLabel desc = new JLabel("Description", SwingConstants.RIGHT);
        JLabel cogs = new JLabel("COGS", SwingConstants.RIGHT);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.RIGHT);
        final JLabel outputText = new JLabel(" ", SwingConstants.RIGHT);

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
        addCard.add(idText);
        addCard.add(desc);
        addCard.add(descText);
        addCard.add(cogs);
        addCard.add(cogsText);
        addCard.add(DateMade);
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
        salesCard.setLayout(new GridLayout(3,2));// TODO Box or Spring Layout
        JPanel buttons = new JPanel();
        final JLabel outputText = new JLabel();

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

        buttons.add(back);
        buttons.add(save);
        salesCard.add(id);
        salesCard.add(idText);
        salesCard.add(saleDate);
        salesCard.add(saleDateText);
        salesCard.add(salePrice);
        salesCard.add(salePriceText);

        superPanel.add(salesCard, BorderLayout.NORTH);
        superPanel.add(buttons, BorderLayout.SOUTH);
        return superPanel;
    }
    private JPanel updateCard(){
        final JPanel superPanel = new JPanel();
        superPanel.setLayout(new BorderLayout());
        final JPanel updateCard = new JPanel();
        updateCard.setLayout(new GridLayout(6, 2)); // TODO spring layout
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
        updateCard.add(idText);
        updateCard.add(desc);
        updateCard.add(descText);
        updateCard.add(cogs);
        updateCard.add(cogsText);
        updateCard.add(DateMade);
        updateCard.add(DateMadeText);
        updateCard.add(saleDate);
        updateCard.add(saleDateText);
        updateCard.add(salePrice);
        updateCard.add(salePriceText);

        buttons.add(back);
        buttons.add(submit);


        superPanel.add(updateCard, BorderLayout.NORTH);
        superPanel.add(buttons, BorderLayout.SOUTH);
        return superPanel;
    }
    private JPanel viewCard(){
        JPanel query = new JPanel();
        //query.setLayout(new GridLayout(7, 2));
        GridBagLayout bag = new GridBagLayout();
        query.setLayout(bag);
        final JLabel outputText = new JLabel("", SwingConstants.CENTER);
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel instructions = new JLabel("Search individually by ID, or by date range", SwingConstants.CENTER);
        JLabel sold = new JLabel("Show sold items", SwingConstants.CENTER);
        JCheckBox soldText = new JCheckBox();
        JLabel pID = new JLabel("Product ID", SwingConstants.CENTER);
        JTextField productIDText = new JTextField();
        JLabel fromDate = new JLabel("From Date", SwingConstants.CENTER);
        JDateChooser fromDateChoose = new JDateChooser();
        JLabel toDate = new JLabel("To Date", SwingConstants.CENTER);
        JDateChooser toDateChoose = new JDateChooser();
        JLabel unsold = new JLabel("Show unsold items", SwingConstants.CENTER);
        JCheckBox unsoldCheck = new JCheckBox();

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        JButton PDF = new JButton("View as PDF");
        PDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton excel = new JButton("Export to Excel");
        excel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton searchAgain = new JButton("New Search");
        searchAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton exportAll = new JButton("Export ALL Records to Excel");
        exportAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        query.add(instructions);
        addComponent(query, instructions, 0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(query, pID, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, productIDText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, fromDate, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, fromDateChoose, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, toDate, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, toDateChoose, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, sold, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, soldText, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, unsold, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, unsoldCheck, 1, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, outputText, 0, 6, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(query, PDF, 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, excel, 1, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, searchAgain, 0, 8, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, exportAll, 0, 9, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, back, 0, 10, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return query;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        } // end method
    } // end private class

    private static void addComponent(Container container, Component component, int gridx, int gridy,
                                     int gridwidth, int gridheight, int anchor, int fill) {
        Insets insets = new Insets(0,0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }
}//end of class