package com.theprogrammingturkey.gobblecore.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ConfigErrorReporter
{
	private static List<String> errorQueue = new ArrayList<String>();

	public static void queueErrorMessage(TextFormatting color, String mod, String message)
	{
		errorQueue.add(color + "[" + mod + "]: " + message);
	}

	public static void outputErrors(EntityPlayer player)
	{
		if(errorQueue.size() > 0)
		{
			player.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + "Gobble Core has been provided with config errors!"));
			player.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + "-------------------------------------------------"));
			for(String s : errorQueue)
			{
				player.addChatMessage(new TextComponentString(s));
			}
			player.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + "-------------------------------------------------"));
			errorQueue.clear();
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(final PlayerLoggedInEvent event)
	{
		outputErrors(event.player);
	}
}
