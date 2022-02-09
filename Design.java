package StudentManagementProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.Cleaner;

public class Design extends JFrame implements ActionListener {
    Container c;
    JLabel studentDetails, firstName, lastName, cgpValue, phoneNumber;
    JTextArea fn, ln, cg, phn;
    JButton addButton, updateButton, clearButton, deleteButton;
    Font font = new Font("Roboto", Font.PLAIN, 12);
    Font font2 = new Font("Roboto", Font.BOLD, 14);
    JTable table;
    DefaultTableModel tableModel;
    String[] colum = {"First Name", "Last Name", "Contact Number", "CGPA"};
    String[] row = new String[4];
    JScrollPane scroll;

    Design() {
        frameDesign();
        containerDesign();
        componentDesign();
        tableDesign();
        addComponent();
        callListener();
    }

    void frameDesign() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management");
        setLocationRelativeTo(null);
        setSize(1000, 1000);
    }

    void containerDesign() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.CYAN);
    }

    void componentDesign() {
        studentDetails = new JLabel("Student Details");
        studentDetails.setBounds(250, 20, 120, 100);
        studentDetails.setFont(font2);

        firstName = new JLabel("First Name  : ");
        firstName.setBounds(150, 80, 120, 100);
        firstName.setFont(font2);

        lastName = new JLabel("Last Name  : ");
        lastName.setBounds(150, 130, 120, 100);
        lastName.setFont(font2);


        cgpValue = new JLabel("CGPA  : ");
        cgpValue.setBounds(150, 180, 120, 100);
        cgpValue.setFont(font2);

        phoneNumber = new JLabel("Contact No  : ");
        phoneNumber.setBounds(150, 230, 120, 100);
        phoneNumber.setFont(font2);

        fn = new JTextArea();
        fn.setBounds(250, 115, 180, 30);
        fn.setFont(font);
        fn.setToolTipText("Enter First Name");

        ln = new JTextArea();
        ln.setBounds(250, 165, 180, 30);
        ln.setFont(font);
        ln.setToolTipText("Enter Last Name");


        cg = new JTextArea();
        cg.setBounds(250, 215, 180, 30);
        cg.setFont(font);
        cg.setToolTipText("Enter CGPA");


        phn = new JTextArea();
        phn.setBounds(250, 265, 180, 30);
        phn.setFont(font);
        phn.setToolTipText("Enter Contact Number");

        addButton = new JButton("ADD");
        addButton.setBounds(550, 120, 100, 35);
        addButton.setFont(font2);

        updateButton = new JButton("UPDATE");
        updateButton.setBounds(550, 170, 100, 35);
        updateButton.setFont(font2);


        clearButton = new JButton("CLEAR");
        clearButton.setBounds(550, 220, 100, 35);
        clearButton.setFont(font2);


        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(550, 270, 100, 35);
        deleteButton.setFont(font2);

    }

    void tableDesign() {
        table = new JTable();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(colum);
        table.setModel(tableModel);

        table.setFont(font2);
        table.setSelectionBackground(Color.pink);
        table.setRowHeight(50);

        scroll = new JScrollPane(table);
        scroll.setBounds(40, 340, getWidth() - 80, 300);


    }

    void addComponent() {
        c.add(studentDetails);
        c.add(firstName);
        c.add(lastName);
        c.add(cgpValue);
        c.add(phoneNumber);
        c.add(fn);
        c.add(ln);
        c.add(cg);
        c.add(phn);
        c.add(addButton);
        c.add(updateButton);
        c.add(clearButton);
        c.add(deleteButton);
        c.add(scroll);
    }

    void callListener() {
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNumber = table.getSelectedRow();
                String first = String.valueOf(tableModel.getValueAt(rowNumber, 0));
                String last = String.valueOf(tableModel.getValueAt(rowNumber, 1));
                String p = String.valueOf(tableModel.getValueAt(rowNumber, 2));
                String cgpa = String.valueOf(tableModel.getValueAt(rowNumber, 3));

                fn.setText(first);
                ln.setText(last);
                phn.setText(p);
                cg.setText(cgpa);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            row[0] = fn.getText();
            row[1] = ln.getText();
            row[2] = phn.getText();
            row[3] = cg.getText();

            if (row[0].isEmpty() || row[1].isEmpty() || row[2].isEmpty() || row[3].isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have not provided all information ");
            } else {
                try {
                    double r = Double.parseDouble(cg.getText());
                    row[3] = String.valueOf(r);

                    if (r > 5.0) {
                        throw new Exception();
                    }
                    if (row[2].length() == 11 && row[2].startsWith("0")) {
                        tableModel.addRow(row);

                    } else {
                        JOptionPane.showMessageDialog(null, "Your Phone number is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "CGPA is incorrect");
                }


            }

        } else if (e.getSource() == clearButton) {
            fn.setText("");
            ln.setText("");
            phn.setText("");
            cg.setText("");
        } else if (e.getSource() == deleteButton) {
            int rowNumber = table.getSelectedRow();
            try {
                tableModel.removeRow(rowNumber);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "No Row is selected", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == updateButton) {

            JOptionPane.showMessageDialog(null, "Select a row");


            int rowNumber = table.getSelectedRow();

            String first = fn.getText();
            String last = ln.getText();
            String p = phn.getText();
            String c = cg.getText();

            tableModel.setValueAt(first, rowNumber, 0);
            tableModel.setValueAt(last, rowNumber, 1);
            tableModel.setValueAt(p, rowNumber, 2);
            tableModel.setValueAt(c, rowNumber, 3);
        }

    }
}
