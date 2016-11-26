package com.theprogrammingturkey.gobblecore.events;

import net.minecraftforge.common.MinecraftForge;

public class EventManager
{
	public static void registerListener(Object event)
	{
		MinecraftForge.EVENT_BUS.register(event);
	}
}
