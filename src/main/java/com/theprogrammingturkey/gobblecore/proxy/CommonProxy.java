package com.theprogrammingturkey.gobblecore.proxy;

import com.theprogrammingturkey.gobblecore.blocks.BlockManager;
import com.theprogrammingturkey.gobblecore.config.ConfigErrorReporter;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;
import com.theprogrammingturkey.gobblecore.events.EventManager;
import com.theprogrammingturkey.gobblecore.events.TickListener;
import com.theprogrammingturkey.gobblecore.items.ItemManager;

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
		EventManager.registerListener(new ConfigLoader());
		EventManager.registerListener(new BlockManager());
		EventManager.registerListener(new ItemManager());
	}

	public EntityPlayer getClientPlayer()
	{
		return null;
	}
}