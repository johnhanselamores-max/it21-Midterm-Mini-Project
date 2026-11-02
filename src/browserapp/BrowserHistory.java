
package browserapp;


public class BrowserHistory {
    
     private String[] history = new String[100];
    private int count = 0;

    // Add visited website
    public void addPage(String url) {
        if (count < history.length) {
            history[count] = url;
            count++;
        }
    }

    // Get website
    public String getPage(int index) {
        if (index >= 0 && index < count) {
            return history[index];
        }
        return null;
    }

    // Get number of visited websites
    public int size() {
        return count;
    }

    // Remove forward history
    public void truncateAfter(int index) {
        while (count > index + 1) {
            count--;
            history[count] = null;
        }
    }

    // Clear all history
    public void clearHistory() {
        for (int i = 0; i < count; i++) {
            history[i] = null;
        }
        count = 0;
    }
    
}
