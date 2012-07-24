package com.warcraftserver.heroesgui.listeners;

import com.warcraftserver.heroesgui.HeroesGUI;
import com.warcraftserver.heroesgui.gui.GUIManager;
import com.warcraftserver.heroesgui.gui.MainMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;
/**
 *
 * @author somners
 */
public class KeyPressedListener implements Listener {
    
    HeroesGUI plugin;
    GUIManager gui;
    
    public KeyPressedListener(){
        plugin = HeroesGUI.getInstance();
        gui = plugin.getGUIManager();
    }
    
//    @EventHandler()
//    public void onKeyPressed(KeyPressedEvent event){
//        Player player = event.getPlayer();
//        Keyboard key = event.getKey();
//        if(key.equals(Keyboard.KEY_H)){
//            GUIManager gui = new GUIManager(player, key);
//        }
//    }
    
    @EventHandler()
    public void onKeyPressed(KeyPressedEvent event) { //Gets fired when the binded key is pressed.
        
        if(event.getKey().equals(Keyboard.KEY_Y)){
            if (event.getScreenType() != ScreenType.GAME_SCREEN){ //Check if the screen is NOT the main game screen.
                return; //Return if this is the case.
            }
            gui.createMainMenu(event.getPlayer()); //Create the overlay for the player.
            return;
        }
        if(event.getKey().equals(Keyboard.KEY_ESCAPE)){
            SpoutPlayer sp = SpoutManager.getPlayer(event.getPlayer());
            if(MainMenu.mainMenuInstance.containsKey(sp)){
                MainMenu mm = MainMenu.mainMenuInstance.get(sp);
                mm.close();
            }
            return;
        }
    }
    
    @EventHandler()
    public void onButtonClick(ButtonClickEvent event) {

        SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());

        if(!player.isSpoutCraftEnabled())
            return;

        if(MainMenu.uuidMainMenu == null || MainMenu.uuidMainMenu.isEmpty())
            return;
        
        if(!event.getButton().getScreen().getId().equals(MainMenu.uuidMainMenu.get(player)))
            return;

        if(event.getButton().getText().equalsIgnoreCase("Close Menu")){

            MainMenu mm = MainMenu.mainMenuInstance.get(player);
            mm.close();
        }
 
    }
    
    @EventHandler()
    public void onDisconnect(PlayerQuitEvent event){
        SpoutPlayer sp = SpoutManager.getPlayer(event.getPlayer());
        if(MainMenu.mainMenuInstance.containsKey(sp)){
            MainMenu mm = MainMenu.mainMenuInstance.get(sp);
            mm.close();
        }
    }


}
