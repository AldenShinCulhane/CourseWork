// Group 6, Topic 26 - Payroll Management DBMS
// Alden Shin-Culhane 501036054
// Ekrem Yilmaz 501108034

package src.main.java;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Database Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton createButton = new JButton("Create Tables");
        createButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to create all tables?",
                    "Confirm Create",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseOperations.createTables();
            }
        });
        panel.add(createButton, gbc);

        JButton dropButton = new JButton("Drop Tables");
        dropButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to drop all tables?",
                    "Confirm Drop",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseOperations.dropTables();
            }
        });
        panel.add(dropButton, gbc);

        JButton populateButton = new JButton("Populate Tables");
        populateButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to populate all tables?",
                    "Confirm Populate",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseOperations.populateTables();
            }
        });
        panel.add(populateButton, gbc);

        JButton queryButton = new JButton("Query Tables");
        queryButton.addActionListener(e -> DatabaseOperations.queryTables());
        panel.add(queryButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton, gbc);
    }
}
