package com.theprogrammingturkey.gobblecore.proxy;

import com.theprogrammingturkey.gobblecore.config.ConfigErrorReporter;
import com.theprogrammingturkey.gobblecore.events.EventManager;
import com.theprogrammingturkey.gobblecore.events.TickListener;

import net.minecraft.entity.player.EntityPlayer;

public class CommonProxy implements IBaseProxy
{

	public boolean isClient()
	{
		return false;
	}

	public void registerGuis()
	{

	}

	public void registerRenderings()
	{

	}

	public void registerEvents()
	{
		EventManager.registerListener(new TickListener());
		EventManager.registerListener(new ConfigErrorReporter());
	}

	public EntityPlayer getClientPlayer()
	{
		return null;
	}
}