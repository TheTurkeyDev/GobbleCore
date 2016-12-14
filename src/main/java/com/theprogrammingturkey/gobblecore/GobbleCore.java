package com.theprogrammingturkey.gobblecore;

import com.theprogrammingturkey.gobblecore.config.ConfigLoader;
import com.theprogrammingturkey.gobblecore.proxy.IBaseProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GobbleCore.MODID, name = GobbleCore.NAME, version = GobbleCore.VERSION, guiFactory = "com.theprogrammingturkey.gobblecore.gui.ConfigGuiFactory")
public class GobbleCore implements IModCore
{
	public static final String MODID = "gobblecore";
	public static final String NAME = "GobbleCore";
	public static final String VERSION = "@VERSION@";

	@Instance(value = MODID)
	public static GobbleCore instance;
	
	@SidedProxy(clientSide = "com.theprogrammingturkey.gobblecore.proxy.ClientProxy", serverSide = "com.theprogrammingturkey.gobblecore.proxy.CommonProxy")
	public static IBaseProxy proxy;

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		ConfigLoader.loadConfigSettings(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerGuis();
		proxy.registerEvents();
		proxy.registerRenderings();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	@Override
	public String getModID()
	{
		return GobbleCore.MODID;
	}

	@Override
	public String getName()
	{
		return GobbleCore.NAME;
	}

	@Override
	public String getVersion()
	{
		return GobbleCore.VERSION;
	}
}
