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
    CardLayout cardPattern = new CardLayout();
    JPanel superPanel;
    String[] searchResults = new String[6];
    /* send array into update panel by assign in search panel
    have DBHandler search method return an array, and use that to set with setArray method
     */

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
        superPanel.add(searchCard(), "update");
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

        JLabel field = new JLabel("Field", SwingConstants.CENTER);
        JLabel newText = new JLabel("New Values", SwingConstants.CENTER);
        JLabel oldText = new JLabel("Old Values", SwingConstants.CENTER);

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        JButton submit = new JButton("Submit and Save");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        }); // TODO fill out action listener
        JButton delete = new JButton("Delete this record");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); // Todo fill action listener


        JLabel id = new JLabel("Product ID", SwingConstants.LEFT);
        JLabel desc = new JLabel("Description", SwingConstants.LEFT);
        JLabel cogs = new JLabel("COGS", SwingConstants.LEFT);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.LEFT);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.LEFT);
        JLabel salePrice = new JLabel("Sale Price", SwingConstants.LEFT);

        JTextField idText = new JTextField();
        JTextField descText = new JTextField();
        JTextField cogsText = new JTextField();
        JDateChooser DateMadeText = new JDateChooser();
        JDateChooser saleDateText = new JDateChooser();
        JTextField salePriceText = new JTextField();

        JLabel oldID = new JLabel(searchResults[0]);
        JLabel oldDesc = new JLabel(searchResults[1]);
        JLabel oldCogs = new JLabel(searchResults[2]);
        JLabel oldDateMade = new JLabel(searchResults[3]);
        JLabel oldSaleDate = new JLabel(searchResults[4]);
        JLabel oldSalePrice = new JLabel(searchResults[5]);

        addComponent(updateCard, field, 0,0,1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, newText, 1,0,1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldText, 2,0,1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, id, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, idText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldID, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, desc, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, descText, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldDesc, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, cogs, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, cogsText, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldCogs, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, DateMade, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, DateMadeText, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldDateMade, 2, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, saleDate, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, saleDateText, 1, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldSaleDate, 2, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, salePrice, 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, salePriceText, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, oldSalePrice, 2, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(updateCard, submit, 0, 7, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, delete, 0, 8, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(updateCard, back, 0, 9, 3, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

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
    private JPanel searchCard(){
        final JPanel superPanel =  new JPanel();
        superPanel.setLayout(cardPattern);
        JPanel searchCard = new JPanel();
        searchCard.setLayout(new GridBagLayout());

        JLabel instructions = new JLabel("Search by product ID", SwingConstants.CENTER);
        JLabel pID = new JLabel("Product ID");
        JTextField pText = new JTextField();
        searchResults[0]= "0";
        searchResults[1]="1";
        searchResults[2]="2";
        searchResults[3]="3";
        searchResults[4]="4";
        searchResults[5]="5";

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c1 = (CardLayout) superPanel.getLayout();
                c1.show(superPanel,"update" );
            }
        });

        addComponent(searchCard, instructions, 0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(searchCard, pID, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(searchCard, pText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(searchCard, search, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(searchCard, back, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        superPanel.add(searchCard, "search");
        superPanel.add(updateCard(), "update");
        return superPanel;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        } // end method
    } // end private class

    private static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
        Insets insets = new Insets(0,0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    private void setArray(String[] setValues){
        System.arraycopy(setValues, 0, searchResults, 0, setValues.length);
    }
}//end of class