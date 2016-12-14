package com.theprogrammingturkey.gobblecore.proxy;

import net.minecraft.entity.player.EntityPlayer;

public interface IBaseProxy
{
	public boolean isClient();

	public void registerGuis();

	public void registerRenderings();
	
	public void registerEvents();
	
	public EntityPlayer getClientPlayer();
}
