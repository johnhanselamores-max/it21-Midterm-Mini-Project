package browserapp;

import java.awt.Desktop;
import java.net.URI;

public class GoBack extends BrowserAction {
    private String previousUrl;

    public GoBack(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    GoBack(String previousPage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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