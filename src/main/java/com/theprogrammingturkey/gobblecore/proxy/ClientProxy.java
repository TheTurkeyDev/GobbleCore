package com.theprogrammingturkey.gobblecore.proxy;

import com.theprogrammingturkey.gobblecore.events.ConfigListener;
import com.theprogrammingturkey.gobblecore.events.EventManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy
{

	@Override
	public boolean isClient()
	{
		return true;
	}

	public void registerRenderings()
	{

	}

	public void registerEvents()
	{
		EventManager.registerListener(new ConfigListener());
	}

	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}