package IMSApp;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

class sGUI {

    private JButton back;
    private CardLayout cardPattern = new CardLayout();
    private JPanel superPanel;

    public static void main(String[] args) {
    }

    void doGui() {
        JFrame cards = new JFrame();
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
        JLabel outputText = new JLabel("", SwingConstants.CENTER);

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
        JLabel desc = new JLabel("Description (255 Character MAX)", SwingConstants.CENTER);
        JLabel cogs = new JLabel("COGS", SwingConstants.CENTER);
        JLabel DateMade = new JLabel("Date Made", SwingConstants.CENTER);

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
                    DBHandler.addRec(idText.getText(), descText.getText(), Ops.priceFormattter(cogsText.getText()),
                            Ops.scrubDate
                            (DateMadeText.getDate()));
                    infoBox("Record Added!", "Success!");

                }catch (Exception ex){
                    infoBox("Add failed: all fields are required (error: nullPointer)", "Failure");
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

        return addCard;
    }
    private JPanel salesCard(){
        JPanel salesCard = new JPanel();
        salesCard.setLayout(new GridBagLayout());

        JLabel id = new JLabel("Product Id", SwingConstants.CENTER);
        JLabel saleDate = new JLabel("Date of Sale", SwingConstants.CENTER);
        final JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        final JTextField idText = new JTextField(1);
        final JDateChooser saleDateText = new JDateChooser();
        final JTextField salePriceText = new JTextField(1);

        JButton save = new JButton("Submit and Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DBHandler.soldRec(idText.getText(), saleDateText.getDate().toString(), Ops.priceFormattter(salePriceText.getText()));
                    infoBox("Record Updated for Sale!", "Success!");
                } catch (SQLException e1) {
                    infoBox("Item not found!", "Error!");
                }
                idText.setText(null);
                saleDateText.setDate(null);
                salePriceText.setText(null);
            }});
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
        final JLabel salePrice = new JLabel("Sale Price", SwingConstants.CENTER);

        final JTextField idText = new JTextField();
        final JTextField descText = new JTextField();
        final JTextField cogsText = new JTextField();
        final JDateChooser DateMadeText = new JDateChooser();
        final JDateChooser saleDateText = new JDateChooser();
        final JTextField salePriceText = new JTextField();

        /*
        the following JLabels should show in real time the information
        retrieved from searching in JTextField "idText"
         */

        final JLabel oldID = new JLabel("", SwingConstants.CENTER);
        final JLabel oldDesc = new JLabel("", SwingConstants.CENTER);
        final JLabel oldCogs = new JLabel("", SwingConstants.CENTER);
        final JLabel oldDateMade = new JLabel("", SwingConstants.CENTER);
        final JLabel oldSaleDate = new JLabel("", SwingConstants.CENTER);
        final JLabel oldSalePrice = new JLabel("", SwingConstants.CENTER);

        idText.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {
                oldID.setText(idText.getText());
                String values[] = DBHandler.searchID(idText.getText());

                //String[] cols = {"ID", "Desc", "COGS", "Date_Made", "Sale_Date", "Sale_Price"};
                oldDesc.setText(values[1]);
                oldCogs.setText(values[2]);
                oldDateMade.setText(values[3]);
                oldSaleDate.setText(values[4]);
                oldSalePrice.setText(values[5]);
            }
        });

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        JButton submit = new JButton("Submit and Save");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] newValues = {
                        idText.getText(),
                        descText.getText(),
                        Ops.priceFormattter(cogsText.getText()),
                        Ops.scrubDate(DateMadeText.getDate()),
                        Ops.scrubDate(saleDateText.getDate()),
                        Ops.priceFormattter(salePriceText.getText())
                };

                String[] oldValues = {
                        oldID.getText(),
                        oldDesc.getText(),
                        oldCogs.getText(),
                        oldDateMade.getText(),
                        oldSaleDate.getText(),
                        oldSalePrice.getText()
                };

                DBHandler.update(Ops.updateArrayFactory(oldValues, newValues));

                idText.setText("");
                descText.setText("");
                cogsText.setText("");
                DateMadeText.setDate(null);
                saleDateText.setDate(null);
                salePriceText.setText("");
                oldID.setText("");
                oldDesc.setText("");
                oldCogs.setText("");
                oldDateMade.setText("");
                oldSaleDate.setText("");
                oldSalePrice.setText("");
                infoBox("Record Updated to new Values!", "Success!");
            }
        });

        JButton delete = new JButton("Delete this record");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                int dialogButton = JOptionPane.showConfirmDialog(null, "Permanently delete record #"+id+"?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if(dialogButton == JOptionPane.YES_OPTION){
                    DBHandler.deleteRec(id);
                    idText.setText("");
                    descText.setText("");
                    cogsText.setText("");
                    DateMadeText.setDate(null);
                    saleDateText.setDate(null);
                    salePriceText.setText("");
                    oldID.setText("");
                    oldDesc.setText("");
                    oldCogs.setText("");
                    oldDateMade.setText("");
                    oldSaleDate.setText("");
                    oldSalePrice.setText("");
                    infoBox("Record #"+id+"deleted!", "Success");
                }
            }
        });

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

        JLabel instructions = new JLabel("Search individually by ID, or by date range", SwingConstants.CENTER);
        JLabel pID = new JLabel("Product ID", SwingConstants.CENTER);
        final JTextField productIDText = new JTextField("");
        JLabel fromDate = new JLabel("From Date", SwingConstants.CENTER);
        final JDateChooser fromDateChoose = new JDateChooser();
        JLabel toDate = new JLabel("To Date", SwingConstants.CENTER);
        final JDateChooser toDateChoose = new JDateChooser();
        String[] listOptions = {"Search by Date Made", "Search by Date Sold"};
        final JComboBox picker = new JComboBox(listOptions);

        back = new JButton("Back");
        back.setActionCommand("home");
        back.addActionListener(new ButtonClickListener());

        JButton PDF = new JButton("View as PDF");
        PDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = productIDText.getText();
                String toDate = Ops.scrubDate(toDateChoose.getDate());
                String fromDate = Ops.scrubDate(fromDateChoose.getDate());
                if ((!id.equals("")) && (toDate == null && fromDate == null)) {
                    Ops.createPDF(DBHandler.exportFromID(id));
                } else if ((fromDate != null && toDate != null)) {
                    Ops.createPDF(DBHandler.exportFromDates(fromDate, toDate, Ops.datePicker(picker.getSelectedItem().toString())));
                } else {
                    infoBox("Can not search using both ID and Dates or two dates needed", "Error");
                }
            }
        });

        JButton excel = new JButton("Export to Excel");
        excel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = productIDText.getText();
                String toDate = Ops.scrubDate(toDateChoose.getDate());
                String fromDate = Ops.scrubDate(fromDateChoose.getDate());
                if ((!id.equals("")) && (toDate == null && fromDate == null)) {
                    Ops.createExcel(DBHandler.exportFromID(id));
                } else if ((fromDate != null && toDate != null)) {
                    Ops.createExcel(DBHandler.exportFromDates(fromDate, toDate, Ops.datePicker(picker.getSelectedItem().toString())));
                } else {
                    infoBox("Can not search using both ID and Dates or two dates needed", "Error");
                }
            }
        });

        JButton searchAgain = new JButton("New Search");
        searchAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productIDText.setText("");
                fromDateChoose.setDate(null);
                toDateChoose.setDate(null);
            }
        });


        JButton exportAll = new JButton("Export ALL Records to Excel");
        exportAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ops.createExcel(DBHandler.exportEverything());
            }
        });

        JButton exportAllPDF = new JButton("Export ALL Records to PDF");
        exportAllPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ops.createPDF(DBHandler.exportEverything());
            }
        });

        query.add(instructions);
        addComponent(query, instructions, 0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(query, pID, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, productIDText, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, picker, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, fromDate, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, fromDateChoose, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, toDate, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, toDateChoose, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        addComponent(query, PDF, 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, excel, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, searchAgain, 0, 7, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, exportAll, 0, 8, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, exportAllPDF, 0, 9, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(query, back, 0, 10, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        return query;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            CardLayout c1 = (CardLayout) superPanel.getLayout();
            c1.show(superPanel, e.getActionCommand());
        } // end method
    }

    private static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
                                     int gridheight, int anchor, int fill) {
        Insets insets = new Insets(0,0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    private static void infoBox(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }
}