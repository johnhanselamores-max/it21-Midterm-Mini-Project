package browserapp;


public class SearchHistory {
    
    public String search(BrowserHistory browser,
                         String keyword) {

        String result = "===== SEARCH RESULTS =====\n\n";

        boolean found = false;

        for (int i = 0; i < browser.size(); i++) {

            String page = browser.getPage(i);

            if (page.toLowerCase().contains(
                keyword.toLowerCase())) {

                result += page + "\n";
                found = true;
            }
        }

        if (!found) {
            result += "No visited page found!";
        }

        return result;
    }
    
}
