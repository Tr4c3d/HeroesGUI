package com.warcraftserver.heroesgui.gui;

import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.CharacterTemplate;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.skill.SkillManager;
import com.warcraftserver.heroesgui.HeroesGUI;
import java.util.*;
import org.getspout.spoutapi.gui.*;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author somners
 */
public class MainMenu {
    
    public static HashMap<SpoutPlayer, UUID> uuidMainMenu = new HashMap<SpoutPlayer, UUID>();
    public static HashMap<SpoutPlayer, MainMenu> mainMenuInstance = new HashMap<SpoutPlayer, MainMenu>();
    private TextureBarWidget health = new TextureBarWidget("https://dl.dropbox.com/u/64166459/Background.png","https://dl.dropbox.com/u/64166459/Foreground.png");
    private TextureBarWidget level = new TextureBarWidget("https://dl.dropbox.com/u/64166459/Background.png","https://dl.dropbox.com/u/64166459/Foreground.png");
    private UUID screenId;
    private MainMenu mm;
    private GenericPopup pop;
    private SpoutPlayer sp;
    private HeroesGUI plugin;
    
    public MainMenu() {
        plugin = HeroesGUI.getInstance();
    }
    
    public void createMainMenu(SpoutPlayer splayer){
        pop = new GenericPopup();
        this.screenId = pop.getId();
        sp = splayer;
        CharacterTemplate character = plugin.getHeroesManager().getCharacter(splayer.getPlayer());
        Hero hero = plugin.getHeroesManager().getHero(sp.getPlayer());
        
        Set<String> skillNames = hero.getHeroClass().getSkillNames();
        Map ski = hero.getSkillSettings();
        StringBuilder sb = new StringBuilder();
        Iterator names = skillNames.iterator();
        String skillname;
        while(names.hasNext()){
            skillname = (String)names.next();
            if(hero.canUseSkill(skillname)){
                sb.append(", ").append(skillname);
            }
        }
//        for(int i = 0 ; i < skillNames.size() ; i ++){
//            sb.append(", ").append(skillNames.iterator().);
//        }
        
        
        uuidMainMenu.put(sp, screenId);
        mainMenuInstance.put(sp, this);
        
//        int height = sp.getMainScreen().getHeight();
//        int width = sp.getMainScreen().getWidth();
        
        GenericLabel nameLabel = new GenericLabel(sp.getDisplayName());
        nameLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        nameLabel.setScale(1.0f);
        nameLabel.setX(125).setY(20);
        nameLabel.setAlign(WidgetAnchor.CENTER_CENTER);
        
        GenericLabel heroLabel = new GenericLabel("Hero:");
        heroLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        heroLabel.setScale(1.0f);
        heroLabel.setX(225).setY(20);
        heroLabel.setAlign(WidgetAnchor.CENTER_CENTER);
        
        GenericLabel heroNameLabel = new GenericLabel(hero.getHeroClass().getName());
        heroNameLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        heroNameLabel.setScale(1.0f);
        heroNameLabel.setX(260).setY(20);
        heroNameLabel.setAlign(WidgetAnchor.CENTER_LEFT);
        
        GenericLabel skillLabel = new GenericLabel("Proffession:");
        skillLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        skillLabel.setScale(1.0f);
        skillLabel.setX(225).setY(40);
        skillLabel.setAlign(WidgetAnchor.CENTER_CENTER);
        
//        hero.getSkillSettings().keySet().toString()
        GenericLabel skillListLabel = new GenericLabel();
        skillListLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        skillListLabel.setScale(1.0f);
        skillListLabel.setX(260).setY(40);
        skillListLabel.setAlign(WidgetAnchor.CENTER_LEFT);
        
        GenericLabel levelLabel = new GenericLabel("Level:");
        levelLabel.setHeight(15).setWidth(50);
//        nameLabel.setTextColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        levelLabel.setScale(1.0f);
        levelLabel.setX(225).setY(60);
        levelLabel.setAlign(WidgetAnchor.CENTER_CENTER);
        
//        GenericLabel levelValLabel = new GenericLabel(String.valueOf(hero.getLevel()));
        level.setDimension(75, 8);
        int xp = (int)hero.getExperience(hero.getHeroClass());
        int toNext = (int)hero.currentXPToNextLevel(hero.getHeroClass());
        level.setValue(xp, xp + toNext);
        level.setPosition(260, 50);
        level.setTextColor(0.0f, 1.0f, 0.0f, 0.5f);
        level.setTextScale(0.65f);
        
        GenericEntityWidget entityWidget = new GenericEntityWidget(sp.getEntityId());
        entityWidget.setDirty(true);
        entityWidget.setHeight(46).setWidth(230);
        entityWidget.setPriority(RenderPriority.High);
        entityWidget.setX(1);
        entityWidget.setY(1);
        
       health.setDimension(100, 10);
       health.setPosition(65, 130);
       health.setTextColor(0.0f, 1.0f, 0.0f, 0.5f);
       health.setValue(character.getHealth(), character.getMaxHealth());
       health.setTextScale(0.65f);
        
        
        GenericButton button1 = new GenericButton("Close Menu");
        button1.setHeight(15).setWidth(75);
        button1.setHoverColor(new Color(1.0F, 5, 0, 1.0F));
        button1.setX(350).setY(200);
        
        
        pop.attachWidget(HeroesGUI.getInstance(), entityWidget);
        pop.attachWidget(HeroesGUI.getInstance(), button1);
        pop.attachWidget(plugin, nameLabel);
        pop.attachWidget(plugin, heroLabel);
        pop.attachWidget(plugin, heroNameLabel);
//        pop.attachWidget(plugin, skillLabel);
//        pop.attachWidget(plugin, skillListLabel);
        pop.attachWidget(plugin, levelLabel);
//        pop.attachWidget(plugin, levelValLabel);
        pop.setDirty(true);
//        health.attachTo(pop);
        sp.getMainScreen().attachPopupScreen(pop);
        health.attachTo(pop);
        level.attachTo(pop);
    }
    
    public void close(){
        mainMenuInstance.remove(sp);
        uuidMainMenu.remove(sp);
        pop.close();
    }
    
    public void updateHealth(){
       CharacterTemplate character = plugin.getHeroesManager().getCharacter(sp.getPlayer());
       health.setValue(character.getHealth(), character.getMaxHealth());
    }
}
