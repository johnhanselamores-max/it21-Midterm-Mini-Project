
package browserapp;

import java.awt.Desktop;
import java.net.URI;

public class VisitWebsite extends BrowserAction{
    
     private String url;

    // Constructor
    public VisitWebsite(String url) {
        this.url = url;
    }

    // Get website URL
    public String getUrl() {
        return url;
    }

    // Visit website
    @Override
    public boolean execute() {

        try {

            if (!url.startsWith("http://")
                && !url.startsWith("https://")) {

                url = "https://" + url;
            }

            if (!Desktop.isDesktopSupported()) {
                return false;
            }

            Desktop.getDesktop().browse(
                new URI(url)
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }
    
}
