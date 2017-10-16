package IMSApp;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    // TODO uncomment for full functionality

    public static void main(String[] args) {
    }

    public void doGui() {
        cards = new JFrame();
        cards.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        superPanel = new JPanel(new CardLayout());

        cards.setLayout(cardPattern);

        superPanel.add(home(), "home");
        superPanel.add(addCard(), "add");
        superPanel.add(salesCard(), "sold");
        superPanel.add(updateCard(), "search");
        superPanel.add(viewCard(), "view");
        Dimension size = new Dimension(900, 900);

        cards.add(superPanel, BorderLayout.CENTER);
        cards.setPreferredSize(size);
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
        updateButton.setActionCommand("search");
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
                    String[] values = {
                            idText.getText(),
                            descText.getText(),
                            cogsText.getText(),
                            Operations.scrubDate(DateMadeText.getDate())
                    };
                    if(Operations.blankChecker(values)){
                        DBHandler.addRec(values);
                        outputText.setText("Record added to database!");
                    } else {
                        throw new NullPointerException();
                    }
                }catch (NullPointerException ex){
                    outputText.setText("Add failed: all fields are required (error: nullPointer)");
                }finally {
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
    } // TODO COMPLETE
    private JPanel salesCard(){
        JPanel salesCard = new JPanel();
        salesCard.setLayout(new GridBagLayout());// TODO Box or Spring Layout
        final JLabel outputText = new JLabel();

        JLabel id = new JLabel("Product Id", SwingConstants.CENTER);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.CENTER);
        final JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        final JTextField idText = new JTextField(1);
        final JDateChooser saleDateText = new JDateChooser();
        final JTextField salePriceText = new JTextField(1);

        JButton save = new JButton("Submit and Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] items = {idText.getText(), saleDateText.getDate().toString(), salePriceText.getText()};

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


        JLabel id = new JLabel("Product ID", SwingConstants.CENTER);
        JLabel desc = new JLabel("Description", SwingConstants.CENTER);
        JLabel cogs = new JLabel("COGS", SwingConstants.CENTER);
        final JLabel DateMade = new JLabel("Date Made", SwingConstants.CENTER);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.CENTER);
        JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        final JTextField idText = new JTextField();
        final JTextField descText = new JTextField();
        final JTextField cogsText = new JTextField();
        final JDateChooser DateMadeText = new JDateChooser();
        final JDateChooser saleDateText = new JDateChooser();
        final JTextField salePriceText = new JTextField();

        /*
        the following JLabels should show in real time the information
        retreived from searching in JTextField "idText"
         */

        final JLabel oldID = new JLabel("", SwingConstants.RIGHT);
        final JLabel oldDesc = new JLabel("", SwingConstants.RIGHT);
        final JLabel oldCogs = new JLabel("", SwingConstants.RIGHT);
        final JLabel oldDateMade = new JLabel("", SwingConstants.RIGHT);
        final JLabel oldSaleDate = new JLabel("", SwingConstants.RIGHT);
        final JLabel oldSalePrice = new JLabel("", SwingConstants.RIGHT);

        idText.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {
                oldID.setText(idText.getText());
                String values[] = DBHandler.searchID(idText.getText());

                //String[] cols = {"ID", "Desc", "COGS", "Date_Made", "Sold", "Sale_Date", "Sale_Price"};
                oldDesc.setText(values[1]);
                oldCogs.setText(values[2]);
                oldDateMade.setText(values[3]);
                oldSaleDate.setText(values[5]);
                oldSalePrice.setText(values[6]);
            }
        });

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        JButton submit = new JButton("Submit and Save");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] values = {
                        descText.getText(),
                        cogsText.getText(),
                        Operations.scrubDate(DateMadeText.getDate()),
                        Operations.scrubDate(saleDateText.getDate()),
                        salePriceText.getText()
                };


            }
        }); // TODO fill out action listener

        JButton delete = new JButton("Delete this record");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }); // Todo fill action listener

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
        final JPanel query = new JPanel();
        GridBagLayout bag = new GridBagLayout();
        query.setLayout(bag);
        final JLabel outputText = new JLabel(" ", SwingConstants.CENTER);

        JLabel instructions = new JLabel("Search individually by ID, or by date range", SwingConstants.CENTER);
        JLabel sold = new JLabel("Show sold items", SwingConstants.CENTER);
        final JCheckBox soldText = new JCheckBox();
        JLabel pID = new JLabel("Product ID", SwingConstants.CENTER);
        final JTextField productIDText = new JTextField();
        JLabel fromDate = new JLabel("From Date", SwingConstants.CENTER);
        final JDateChooser fromDateChoose = new JDateChooser();
        JLabel toDate = new JLabel("To Date", SwingConstants.CENTER);
        final JDateChooser toDateChoose = new JDateChooser();
        JLabel unsold = new JLabel("Show unsold items", SwingConstants.CENTER);
        JCheckBox unsoldCheck = new JCheckBox();

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        JButton PDF = new JButton("View as PDF");
        PDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputText.setText("View as PDF");
            }
        }); //todo

        JButton excel = new JButton("Export to Excel");
        excel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputText.setText("Export to Excel");
            }
        }); //todo

        JButton searchAgain = new JButton("New Search");
        searchAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productIDText.setText("");
                fromDateChoose.setDate(null);
                toDateChoose.setDate(null);
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

        addComponent(query, PDF, 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, excel, 1, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, searchAgain, 0, 8, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, exportAll, 0, 9, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, back, 0, 10, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, outputText, 0, 11, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return query;
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