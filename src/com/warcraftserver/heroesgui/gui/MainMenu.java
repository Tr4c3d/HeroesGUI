package com.warcraftserver.heroesgui.gui;

import com.warcraftserver.heroesgui.HeroesGUI;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.*;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author somners
 */
public class MainMenu {
    
    public static HashMap<SpoutPlayer, UUID> uuidMainMenu = new HashMap<SpoutPlayer, UUID>();
    public static HashMap<SpoutPlayer, MainMenu> mainMenuInstance = new HashMap<SpoutPlayer, MainMenu>();
    private UUID screenId;
    private MainMenu mm;
    private GenericPopup pop;
    private SpoutPlayer sp;
    
    public MainMenu() {
        
    }
    
    public void createMainMenu(SpoutPlayer splayer){
        pop = new GenericPopup();
        this.screenId = pop.getId();
        sp = splayer;
        
        uuidMainMenu.put(sp, screenId);
        mainMenuInstance.put(sp, this);
        
        int height = sp.getMainScreen().getHeight();
        int width = sp.getMainScreen().getWidth();
        
        GenericEntityWidget entityWidget = new GenericEntityWidget(sp.getEntityId());
        entityWidget.setDirty(true);
        entityWidget.setHeight(46).setWidth(230);
        entityWidget.setPriority(RenderPriority.High);
        entityWidget.setAnchor(WidgetAnchor.CENTER_CENTER);
        entityWidget.setX((int)(.1 * width));
        entityWidget.setY((int)(.1 * height));
        
        GenericButton button1 = new GenericButton("Close Menu");
        button1.setX((int)(.1 * width));
        button1.setY((int)(.1 * height));
        button1.setAnchor(WidgetAnchor.CENTER_CENTER);
        button1.setHeight(15).setWidth(200);
        button1.setHoverColor(new Color(1.0F, 5, 0, 1.0F));
        
        pop.attachWidget(HeroesGUI.getInstance(), entityWidget);
        pop.attachWidget(HeroesGUI.getInstance(), button1);
        pop.setDirty(true);
        sp.getMainScreen().attachPopupScreen(pop);
    }
    
    public void close(){
        mainMenuInstance.remove(sp);
        uuidMainMenu.remove(sp);
        pop.close();
    }
}
