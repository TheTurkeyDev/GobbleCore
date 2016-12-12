package com.theprogrammingturkey.gobblecore;

import com.theprogrammingturkey.gobblecore.events.EventManager;
import com.theprogrammingturkey.gobblecore.events.TickListener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GobbleCore.MODID, name = GobbleCore.NAME, version = GobbleCore.VERSION)
public abstract class GobbleCore
{
	public static final String MODID = "gobblecore";
	public static final String NAME = "GobbleCore";
	public static final String VERSION = "@VERSION@";

	@Instance(value = MODID)
	public static GobbleCore instance;

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		EventManager.registerListener(new TickListener());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
