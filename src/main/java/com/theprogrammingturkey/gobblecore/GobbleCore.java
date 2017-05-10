package com.theprogrammingturkey.gobblecore;

import com.theprogrammingturkey.gobblecore.blocks.BlockManager;
import com.theprogrammingturkey.gobblecore.commands.CommandManager;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;
import com.theprogrammingturkey.gobblecore.managers.ProxyManager;
import com.theprogrammingturkey.gobblecore.proxy.IBaseProxy;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

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
		ProxyManager.registerModProxy(proxy);
		ProxyManager.initProxies();

		BlockManager.registerBlocks();
		// ItemManager.registerItems();
		if(event.getSide() == Side.CLIENT)
		{
			BlockManager.registerModels();
			// ItemManager.registerModels();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		CommandManager.loadCommandHandlers(event.getSide().isClient(), (CommandHandler) event.getServer().getCommandManager());
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