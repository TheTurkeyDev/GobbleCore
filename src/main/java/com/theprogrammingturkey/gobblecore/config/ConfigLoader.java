package com.theprogrammingturkey.gobblecore.config;

import java.io.File;

import com.theprogrammingturkey.gobblecore.GobbleCore;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigLoader
{
	public static Configuration config;

	public static final String genCat = "General Settings";

	public static void loadConfigSettings(File file)
	{
		config = new Configuration(file);

		refreshSettings();
	}

	private static void refreshSettings()
	{
		config.load();

		GobbleCoreSettings.allowWebServerConnect = config.get(genCat, "Allow GobbleCore to send loaded mods (Mod name and version) that use GobbleCore for dynamic settings to Turkey's web server? Questions and inquires can be sent to  Turkey for more information.", GobbleCoreSettings.allowWebServerConnect).setRequiresMcRestart(false).getBoolean();

		config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if(GobbleCore.MODID.equals(eventArgs.getModID()))
			refreshSettings();
	}
}