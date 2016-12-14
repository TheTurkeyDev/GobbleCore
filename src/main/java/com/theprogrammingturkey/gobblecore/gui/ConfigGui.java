package com.theprogrammingturkey.gobblecore.gui;

import java.util.ArrayList;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig
{
	public ConfigGui(GuiScreen parent)
	{
		super(parent, getCategories(ConfigLoader.config), GobbleCore.MODID, false, false, GobbleCore.NAME);
	}

	public static ArrayList<IConfigElement> getCategories(Configuration config)
	{
		ArrayList<IConfigElement> categories = new ArrayList<IConfigElement>();
		for(String s : config.getCategoryNames())
			categories.add(new ConfigElement(config.getCategory(s)));
		return categories;
	}
}