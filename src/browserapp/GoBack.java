
package browserapp;

import java.awt.Desktop;
import java.net.URI;

public class GoBack extends BrowserAction {
    private String previousUrl;

    public GoBack(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    @Override
    public boolean execute() {

        try {
            if (!Desktop.isDesktopSupported()) {
                return false;
            }

            Desktop.getDesktop().browse(
                new URI(previousUrl)
            );

            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
}
