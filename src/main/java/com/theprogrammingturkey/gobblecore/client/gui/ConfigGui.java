package com.theprogrammingturkey.gobblecore.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig
{
	public ConfigGui(GuiScreen parent)
	{
		super(parent, getElements(), GobbleCore.MODID, GobbleCore.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigLoader.config.toString()));
	}

	public static List<IConfigElement> getElements()
	{
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new ConfigElement(ConfigLoader.config.getCategory(ConfigLoader.genCat)));
		return list;
	}
}