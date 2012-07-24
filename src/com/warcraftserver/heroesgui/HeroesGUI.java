package com.warcraftserver.heroesgui;

import com.warcraftserver.heroesgui.gui.GUIManager;
import com.warcraftserver.heroesgui.listeners.KeyPressedListener;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author somners
 */
public class HeroesGUI extends JavaPlugin{

    
    
    private Logger log = Logger.getLogger("Minecraft");
    private static HeroesGUI instance;
    private GUIManager gui;
    private KeyPressedListener listener;
    
    
    public void log(String msg){
        log.info("[HeroesGUI]" + msg);
    }
    
    public void onEnable(){
        instance = this;
        gui = new GUIManager();
        listener = new KeyPressedListener();
        getServer().getPluginManager().registerEvents(listener, this);
    }
    
    public void onDisable(){
    
    }
    
    public static HeroesGUI getInstance() {
        return instance;
    }
    
    public  GUIManager getGUIManager(){
        return gui;
    }
    
    
    
    
}
