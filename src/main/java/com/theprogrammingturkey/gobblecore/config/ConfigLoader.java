package com.theprogrammingturkey.gobblecore.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigLoader
{
	public static Configuration config;
	

	public static void loadConfigSettings(File file)
	{
		config = new Configuration(file);

		config.load();
		
		config.save();

	}
}