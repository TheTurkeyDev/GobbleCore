package com.theprogrammingturkey.gobblecore.events;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigListener
{
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent event)
	{
		if(event.getModID().equals(GobbleCore.MODID))
		{
			ConfigLoader.config.save();
		}
	}
}
