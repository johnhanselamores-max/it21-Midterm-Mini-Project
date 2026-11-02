
package browserapp;

import javax.swing.*;
import java.awt.*;

public class BrowserApp extends JFrame{
    private JTextField urlField;
    private JTextField searchField;
    private JTextArea historyArea;

    private BrowserHistory browser;
    private int currentIndex = -1;

    public BrowserApp() {

        browser = new BrowserHistory();

        setTitle("Browser Navigation Simulator");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // TITLE
        JLabel title = new JLabel(
            "BROWSER NAVIGATION SIMULATOR",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));

        // URL INPUT
        urlField = new JTextField();
        urlField.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton visitButton = new JButton("Visit Webpage");
        JButton backButton = new JButton("Go Back");

        JPanel urlPanel = new JPanel(new BorderLayout(5, 5));

        urlPanel.add(urlField, BorderLayout.CENTER);
        urlPanel.add(visitButton, BorderLayout.EAST);

        // NAVIGATION BUTTONS
        JButton historyButton = new JButton("Display History");
        JButton clearButton = new JButton("Clear History");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(clearButton);

        // SEARCH PANEL
        searchField = new JTextField();

        JButton searchButton = new JButton("Search Visited Pages");

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // HISTORY DISPLAY
        historyArea = new JTextArea();

        historyArea.setEditable(false);
        historyArea.setFont(
            new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane = new JScrollPane(historyArea);

        // TOP PANEL
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        topPanel.add(title);
        topPanel.add(urlPanel);
        topPanel.add(buttonPanel);

        // ADD COMPONENTS
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // BUTTON ACTIONS

        visitButton.addActionListener(e -> visitWebsite());

        urlField.addActionListener(e -> visitWebsite());

        backButton.addActionListener(e -> goBack());

        historyButton.addActionListener(e -> displayHistory());

        searchButton.addActionListener(e -> searchHistory());

        clearButton.addActionListener(e -> clearHistory());

        displayHistory();
    }

    // 1. VISIT WEBPAGE
    private void visitWebsite() {

        String url = urlField.getText().trim();

        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter a website URL!"
            );
            return;
        }

        // Create action object
        VisitWebsite action = new VisitWebsite(url);

        if (action.execute()) {

            url = action.getUrl();

            // Remove forward history
            browser.truncateAfter(currentIndex);

            // Save visited page
            browser.addPage(url);

            currentIndex = browser.size() - 1;

            displayHistory();

        } else {
            JOptionPane.showMessageDialog(
                this, "Unable to open webpage!"
            );
        }
    }

    // 2. DISPLAY BROWSING HISTORY
    private void displayHistory() {

        DisplayHistory display = new DisplayHistory();

        historyArea.setText(
            display.showHistory(browser, currentIndex)
        );
    }

    // 3. GO BACK TO PREVIOUS PAGE
    private void goBack() {

        if (currentIndex > 0) {

            String previousPage =
                browser.getPage(currentIndex - 1);

            GoBack action = new GoBack(previousPage);

            if (action.execute()) {

                currentIndex--;

                urlField.setText(previousPage);

                displayHistory();
            }

        } else {

            JOptionPane.showMessageDialog(
                this, "No previous page available!"
            );
        }
    }

    // 4. SEARCH VISITED PAGES
    private void searchHistory() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "Please enter a keyword!"
            );
            return;
        }

        SearchHistory search = new SearchHistory();

        historyArea.setText(
            search.search(browser, keyword)
        );
    }

    // 5. CLEAR HISTORY
    private void clearHistory() {

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to clear browsing history?",
            "Clear History",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            browser.clearHistory();

            currentIndex = -1;

            urlField.setText("");

            displayHistory();
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BrowserApp app = new BrowserApp();

            app.setVisible(true);

        });
    }
}
