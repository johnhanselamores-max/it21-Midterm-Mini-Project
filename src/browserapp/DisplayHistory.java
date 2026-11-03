package browserapp;


public class DisplayHistory {
    
    public String showHistory(BrowserHistory browser,
                              int currentIndex) {

        String result = "===== BROWSING HISTORY =====\n\n";

        if (browser.size() == 0) {
            return result + "No browsing history.";
        }

        for (int i = 0; i < browser.size(); i++) {

            result += (i + 1) + ". "
                    + browser.getPage(i);

            if (i == currentIndex) {
                result += "  <-- CURRENT PAGE";
            }

            result += "\n";
        }

        return result;
    }
    
}