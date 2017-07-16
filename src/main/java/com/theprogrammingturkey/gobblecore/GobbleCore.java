package com.theprogrammingturkey.gobblecore;

import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.theprogrammingturkey.gobblecore.blocks.BlockManager;
import com.theprogrammingturkey.gobblecore.commands.CommandManager;
import com.theprogrammingturkey.gobblecore.config.ConfigLoader;
import com.theprogrammingturkey.gobblecore.config.GobbleCoreSettings;
import com.theprogrammingturkey.gobblecore.config.QueuedMessageReporter;
import com.theprogrammingturkey.gobblecore.entity.EntityManager;
import com.theprogrammingturkey.gobblecore.items.ItemManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager.ModWebHook;
import com.theprogrammingturkey.gobblecore.network.NetworkManager;
import com.theprogrammingturkey.gobblecore.proxy.IBaseProxy;
import com.theprogrammingturkey.gobblecore.proxy.ProxyManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.CommandHandler;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = GobbleCore.MODID, name = GobbleCore.NAME, version = GobbleCore.VERSION, guiFactory = GobbleCore.GUIFACTORY)
public class GobbleCore implements IModCore
{
	public static final String MODID = "gobblecore";
	public static final String NAME = "GobbleCore";
	public static final String VERSION = "@VERSION@";
	public static final String GUIFACTORY = "com.theprogrammingturkey.gobblecore.client.gui.ConfigGuiFactory";

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

		EntityManager.registerEntities();
		NetworkManager.registerPackets();

		if(event.getSide() == Side.CLIENT)
		{
			EntityManager.registerRenderings();
		}
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
					if(json.isJsonObject())
					{
						for(Entry<String, JsonElement> task : json.getAsJsonObject().entrySet())
						{
							if(task.getKey().equals("ChatMessage"))
							{
								for(JsonElement messageElement : task.getValue().getAsJsonArray())
								{
									JsonObject messageData = messageElement.getAsJsonObject();

									if(messageData.has("Username"))
									{
										if(proxy.isClient() && Minecraft.getMinecraft().getSession().getUsername().equalsIgnoreCase(messageData.get("Username").getAsString()))
											QueuedMessageReporter.queueMessage(TextFormatting.GREEN, "GobbleCore", messageData.get("Message").getAsString());
									}
									else
									{
										QueuedMessageReporter.queueMessage(TextFormatting.GREEN, "GobbleCore", messageData.get("Message").getAsString());
									}
								}
							}
						}
					}
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