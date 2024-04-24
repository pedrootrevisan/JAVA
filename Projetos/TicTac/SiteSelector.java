//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SiteSelector extends JApplet {
    private HashMap<String, URL> sites;
    private ArrayList<String> siteNames;
    private JList siteChooser;

    public SiteSelector() {
    }

    public void init() {
        this.sites = new HashMap();
        this.siteNames = new ArrayList();
        this.getSitesFromHTMLParameters();
        this.add(new JLabel("Choose a site to browse"), "North");
        this.siteChooser = new JList(this.siteNames.toArray());
        this.siteChooser.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                Object object = SiteSelector.this.siteChooser.getSelectedValue();
                URL newDocument = (URL)SiteSelector.this.sites.get(object);
                AppletContext browser = SiteSelector.this.getAppletContext();
                browser.showDocument(newDocument);
            }
        });
        this.add(new JScrollPane(this.siteChooser), "Center");
    }

    private void getSitesFromHTMLParameters() {
        int counter = 0;

        for(String title = this.getParameter("title" + counter); title != null; title = this.getParameter("title" + counter)) {
            String location = this.getParameter("location" + counter);

            try {
                URL url = new URL(location);
                this.sites.put(title, url);
                this.siteNames.add(title);
            } catch (MalformedURLException var6) {
                var6.printStackTrace();
            }

            ++counter;
        }

    }
}
