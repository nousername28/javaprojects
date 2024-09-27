import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RecordViewer extends JFrame {
    public RecordViewer(HashMap<String, String> records) {
        super("All Records");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        StringBuilder allRecords = new StringBuilder("<html>");
        int serialNumber = 1;

        if (records.isEmpty()) {
            allRecords.append("No records available.");
        } else {
            for (Map.Entry<String, String> entry : records.entrySet()) {
                allRecords.append(serialNumber).append(". ").append(entry.getKey())
                          .append(": ").append(entry.getValue()).append("<br/>");
                serialNumber++;
            }
        }
        allRecords.append("</html>");

        JLabel recordsLabel = new JLabel(allRecords.toString());
        recordsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(recordsLabel);
        setVisible(true);
    }
}
