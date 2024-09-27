import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
    private DirectoryManager directoryManager = new DirectoryManager();
    private JTextField nameField, phoneField;
    private JLabel statusLabel;

    public UserInterface() {
        super("Telephone Directory");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        phoneField = new JTextField(15);
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.BLUE);

        addComponent(gbc, 0, 0, new JLabel("Name:"), 1);
        addComponent(gbc, 1, 0, nameField, 1);
        addComponent(gbc, 0, 1, new JLabel("Phone Number:"), 1);
        addComponent(gbc, 1, 1, phoneField, 1);
        addComponent(gbc, 0, 2, createButton("Add", this), 1);
        addComponent(gbc, 0, 3, createButton("Modify", this), 1);
        addComponent(gbc, 1, 3, createButton("Delete", this), 1);
        addComponent(gbc, 0, 4, createButton("View All", this), 1);
        addComponent(gbc, 1, 4, createButton("Search", this), 1);
        addComponent(gbc, 0, 5, createButton("Clear", this), 1);
        addComponent(gbc, 0, 6, statusLabel, 2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    private void addComponent(GridBagConstraints gbc, int x, int y, Component comp, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        add(comp, gbc);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.CYAN);
        button.setForeground(Color.BLACK);
        button.addActionListener(listener);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        switch (e.getActionCommand()) {
            case "Add":
                if (!directoryManager.recordExists(name)) {
                    directoryManager.addRecord(name, phone);
                    showMessage("Record added.");
                } else {
                    showMessage("Name exists. Use Modify.");
                }
                break;
            case "Modify":
                if (directoryManager.recordExists(name)) {
                    directoryManager.modifyRecord(name, phone);
                    showMessage("Record modified.");
                } else {
                    showMessage("Name does not exist.");
                }
                break;
            case "Delete":
                if (directoryManager.recordExists(name)) {
                    directoryManager.deleteRecord(name);
                    showMessage("Record deleted from database.");
                } else {
                    showMessage("Name does not exist.");
                }
                break;
            case "View All":
                new RecordViewer(directoryManager.getAllRecords());
                break;
            case "Search":
                String existingPhone = directoryManager.searchRecord(name);
                if (existingPhone != null) {
                    showMessage("Phone Number: " + existingPhone);
                } else {
                    showMessage("Name does not exist.");
                }
                break;
            case "Clear":
                clearFields();
                break;
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        statusLabel.setText("Fields cleared.");
    }

    private void showMessage(String message) {
        statusLabel.setText(message);
    }
}
