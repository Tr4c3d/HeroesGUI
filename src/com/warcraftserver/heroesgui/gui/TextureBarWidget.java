package com.warcraftserver.heroesgui.gui;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.gui.*;

/**
 * Encapsulate the custom bar widget
 * 
* @author Wolftein <agustin.l.alvarez@hotmail.com>
 * @version 1.0 ,01-feb-2012
 * @since 1.6
 */
public class TextureBarWidget {

    private GenericTexture background, progress;
    private GenericLabel description;

    /**
     * Constructor of the custom widget
     *     
* @param backgroundImage the background image
     * @param progressImage the progress overlay image
     * @param description the description of the bar
     */
    public TextureBarWidget(String backgroundImage, String progressImage) {
        background = (GenericTexture) new GenericTexture(backgroundImage).setDrawAlphaChannel(true).setPriority(RenderPriority.Highest);
        progress = (GenericTexture) new GenericTexture(
                progressImage).setDrawAlphaChannel(true).setPriority(RenderPriority.Normal);
        description = (GenericLabel) new GenericLabel().setPriority(RenderPriority.Lowest);
    }

    /**
     * Set the value of the bar
     *     
* @param current the current value
     * @param max the maximus value
     */
    public void setValue(int current, int max) {
        if (max != 0) {
            progress.setWidth(((current * background.getWidth()) / max)-2);
        } else {
            progress.setWidth(background.getWidth() - 2);
        }
        description.setText(current + "/" + max);
        description.setX(progress.getX() + (background.getWidth() / 2) - description.getText().length());
    }

    /**
     * Set the scale of the text inside the bar
     *     
* @param scale the scale within clamp
     * @return this for chaining
     */
    public TextureBarWidget setTextScale(float scale) {
        description.setScale(scale);

        return this;
    }

    /**
     * Set the text color of the bar
     *     
* @param r red component
     * @param g green component
     * @param b blue component
     * @param a alpha component
     * @return this for chaining
     */
    public TextureBarWidget setTextColor(float r, float g, float b, float a) {
        description.setTextColor(new Color(r, g, b, a));

        return this;
    }

    /**
     * Attach the entire bar to a screen
     *     
* @param screen the screen to attach to
     */
    public void attachTo(Screen screen) {
        screen.attachWidgets(Bukkit.getPluginManager().getPlugin("HeroesGUI"), background,
                progress, description);
    }
    
    public void removeFrom(Screen screen){
        screen.removeWidget(background);
        screen.removeWidget(progress);
        screen.removeWidget(description);
    }

    /**
     * Set the anchor of the widgets
     *     
* @param anchor the anchor of the widget
     * @return this for chaining
     */
    public TextureBarWidget setAnchor(WidgetAnchor anchor) {
        background.setAnchor(anchor);
        progress.setAnchor(anchor);
        description.setAnchor(anchor);

        return this;
    }

    /**
     * Set the dimesion of the objects
     *     
* @param width the width of the objects
     * @param height the height of the objects
     * @return this for chaining
     */
    public TextureBarWidget setDimension(int width, int height) {
        background.setWidth(width).setHeight(height);
        progress.setWidth(width - 2).setHeight(height - 2);
        description.setWidth(width).setHeight(height);

        return this;
    }

    /**
     * Set the position of both of the widgets
     *     
* @param x the x coordinate
     * @param y the y coordinate
     * @return this for chaining
     */
    public TextureBarWidget setPosition(int x, int y) {
        background.setX(x).setY(y);
        progress.setX(x+1).setY(y+1);
        description.setX(x + (background.getWidth() / 2) - description.getText().length()).setY(y+3);

        return this;
    }
}