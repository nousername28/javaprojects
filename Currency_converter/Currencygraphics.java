import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyGraphics extends JFrame {
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;
    private JComboBox<String> fromCurrency, toCurrency;

    public CurrencyGraphics() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(10, 20, 100, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 20, 150, 25);
        add(amountField);

        fromCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "CAD", "GBP", "AUD"});
        fromCurrency.setBounds(120, 60, 70, 25);
        add(fromCurrency);

        toCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "CAD", "GBP", "AUD"});
        toCurrency.setBounds(200, 60, 70, 25);
        add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.setBounds(100, 100, 100, 30);
        add(convertButton);

        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setBounds(10, 140, 250, 25);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double exchangeRate = getExchangeRate((String) fromCurrency.getSelectedItem(), (String) toCurrency.getSelectedItem());
            Currency currency = new Currency(exchangeRate);
            double convertedAmount = currency.convert(amount);
            resultLabel.setText("Converted Amount: " + convertedAmount + " " + getCurrencySymbol((String) toCurrency.getSelectedItem()));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount. Please enter a number.");
        }
    }

    private double getExchangeRate(String from, String to) {
        if (from.equals("USD") && to.equals("INR")) return 82.00;
        if (from.equals("INR") && to.equals("USD")) return 0.012;
        if (from.equals("USD") && to.equals("EUR")) return 0.91;
        if (from.equals("EUR") && to.equals("USD")) return 1.1;
        if (from.equals("USD") && to.equals("CAD")) return 1.25;
        if (from.equals("CAD") && to.equals("USD")) return 0.80;
        if (from.equals("USD") && to.equals("GBP")) return 0.75;
        if (from.equals("GBP") && to.equals("USD")) return 1.33;
        if (from.equals("USD") && to.equals("AUD")) return 1.35;
        if (from.equals("AUD") && to.equals("USD")) return 0.74;
        if (from.equals("INR") && to.equals("CAD")) return 0.015;
        if (from.equals("CAD") && to.equals("INR")) return 66.67;
        if (from.equals("INR") && to.equals("GBP")) return 0.0091;
        if (from.equals("GBP") && to.equals("INR")) return 110.00;
        if (from.equals("INR") && to.equals("AUD")) return 0.018;
        if (from.equals("AUD") && to.equals("INR")) return 55.56;
        return 1.0;
    }

    private String getCurrencySymbol(String currency) {
        switch (currency) {
            case "INR": return "₹";
            case "USD": return "$";
            case "EUR": return "€";
            case "CAD": return "C$";
            case "GBP": return "£";
            case "AUD": return "A$";
            default: return "";
        }
    }
}
