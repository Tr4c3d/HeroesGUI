package com.warcraftserver.heroesgui.gui;

import com.warcraftserver.heroesgui.HeroesGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;
/**
 *
 * @author somners
 */
public class GUIManager {
    
    private HeroesGUI plugin;
    private SpoutManager sm;
    
    public GUIManager(){
        plugin = HeroesGUI.getInstance();
    }
    
    public void createMainMenu(Player player){
        SpoutPlayer sp = SpoutManager.getPlayer(player);
        if(MainMenu.mainMenuInstance.containsKey(sp)){
            return;
        }
        MainMenu main = new MainMenu();
        main.createMainMenu(sp);
    }
    
    
    
    
}