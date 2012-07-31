package com.warcraftserver.heroesgui;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.skill.SkillManager;
import com.warcraftserver.heroesgui.gui.GUIManager;
import com.warcraftserver.heroesgui.listeners.KeyPressedListener;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
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
//    private static CharacterManager pl;
    
    
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
    
    

    public CharacterManager getHeroesManager() {
        Plugin hl = Bukkit.getPluginManager().getPlugin("Heroes");
        CharacterManager pl;
        pl = (CharacterManager) ((Heroes) hl).getCharacterManager();
        return pl;
    }
    
    public SkillManager getSkillManager() {
        Plugin hl = Bukkit.getPluginManager().getPlugin("Heroes");
        SkillManager pl;
        pl = (SkillManager) ((Heroes) hl).getSkillManager();
        return pl;
    }
    
    
    
    
}
