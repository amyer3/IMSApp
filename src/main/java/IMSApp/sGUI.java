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

    public void doGui() throws java.lang.InterruptedException {
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
        JPanel home = new JPanel();
        home.setLayout(new GridBagLayout());
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

        addComponent(home, tLabel, 0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(home, madeButton, 0, 1, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(home, soldButton, 0, 3, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(home, viewButton, 0, 5, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(home, updateButton, 0, 7, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(home, outputText, 0, 9, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        return home;
    }
    private JPanel addCard() {
        final JPanel addCard = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();
        addCard.setLayout(bagLayout);

        JLabel id = new JLabel("Product ID", SwingConstants.CENTER);
        JLabel desc = new JLabel("Description", SwingConstants.CENTER);
        JLabel cogs = new JLabel("COGS", SwingConstants.CENTER);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.CENTER);
        final JLabel outputText = new JLabel(" ", SwingConstants.CENTER);

        final JTextField idText = new JTextField();
        final JTextField descText = new JTextField();
        final JTextField cogsText = new JTextField();
        final JDateChooser DateMadeText = new JDateChooser();

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        final JButton addButton = new JButton("Submit and Save");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] values = new String[4];
                    values[0] = idText.getText();
                    values[1] = descText.getText();
                    values[2] = cogsText.getText();
                    values[3] = DateMadeText.getDate().toString();
                    //if(Operations.blankChecker(values)){
                        //DBHandler.addRec(databaseConnection,values);
                        //outputText.setText("Record added to database")
                    //} else {
                       // throw new NullPointerException();
                   // }
                    //TODO uncomment for full functionality
                }catch (NullPointerException ex){
                    outputText.setText("Add failed: all fields are required (error: nullPointer)");
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

        addComponent(addCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, idText, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, desc, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, descText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, cogs, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, cogsText, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, DateMade,0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, DateMadeText, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(addCard, addButton, 0, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, back, 0, 5, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(addCard, outputText, 0, 6, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return addCard;
    } // TODO uncomment but then complete
    private JPanel salesCard(){
        JPanel salesCard = new JPanel();
        salesCard.setLayout(new GridBagLayout());// TODO Box or Spring Layout
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

        addComponent(salesCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, idText, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, saleDate, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, saleDateText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, salePrice, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, salePriceText, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, save, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, back, 0, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(salesCard, outputText, 0, 5, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return salesCard;
    }
    private JPanel updateCard(){
        final JPanel updateCard = new JPanel();
        updateCard.setLayout(new GridBagLayout());

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
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(idText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(desc);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(descText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(cogs);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(cogsText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(DateMade);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(DateMadeText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(saleDate);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(saleDateText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(salePrice);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        updateCard.add(salePriceText);
        addComponent(updateCard, id, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, submit, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, back, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return updateCard;
    }
    private JPanel viewCard(){
        JPanel query = new JPanel();
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
        }); //todo

        JButton excel = new JButton("Export to Excel");
        excel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); //todo

        JButton searchAgain = new JButton("New Search");
        searchAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); //todo

        JButton exportAll = new JButton("Export ALL Records to Excel");
        exportAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); // todo

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