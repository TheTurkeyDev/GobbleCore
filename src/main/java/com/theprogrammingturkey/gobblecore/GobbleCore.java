package com.theprogrammingturkey.gobblecore;

import org.apache.logging.log4j.Logger;

import com.google.gson.JsonElement;
import com.theprogrammingturkey.gobblecore.blocks.BlockManager;
import com.theprogrammingturkey.gobblecore.commands.CommandManager;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;
import com.theprogrammingturkey.gobblecore.config.GobbleCoreSettings;
import com.theprogrammingturkey.gobblecore.items.ItemManager;
import com.theprogrammingturkey.gobblecore.managers.ProxyManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager.ModWebHook;
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

@Mod(modid = GobbleCore.MODID, name = GobbleCore.NAME, version = GobbleCore.VERSION, guiFactory = "com.theprogrammingturkey.gobblecore.client.gui.ConfigGuiFactory")
public class GobbleCore implements IModCore
{
	public static final String MODID = "gobblecore";
	public static final String NAME = "GobbleCore";
	public static final String VERSION = "@VERSION@";

	@Instance(value = MODID)
	public static GobbleCore instance;

	@SidedProxy(clientSide = "com.theprogrammingturkey.gobblecore.proxy.ClientProxy", serverSide = "com.theprogrammingturkey.gobblecore.proxy.CommonProxy")
	public static IBaseProxy proxy;

	public static Logger logger;

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		ConfigLoader.loadConfigSettings(event.getSuggestedConfigurationFile());
		ProxyManager.registerModProxy(proxy);
		ProxyManager.initProxies();

		BlockManager.registerBlocks();
		ItemManager.registerItems();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
		{
			BlockManager.registerModels();
			ItemManager.registerModels();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if(GobbleCoreSettings.allowWebServerConnect)
		{
			WebHookManager.registerHook(new ModWebHook(this)
			{
				@Override
				public void onResponse(JsonElement json)
				{
				}
			});

			WebHookManager.processHooks();
		}
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