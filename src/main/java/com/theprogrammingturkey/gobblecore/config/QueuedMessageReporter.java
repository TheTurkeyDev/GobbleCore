package com.theprogrammingturkey.gobblecore.config;

import java.util.ArrayList;
import java.util.List;

import com.theprogrammingturkey.gobblecore.util.MessageUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class QueuedMessageReporter
{
	private static List<String> messageQueue = new ArrayList<String>();
	private static List<String> errorQueue = new ArrayList<String>();

	public static void queueErrorMessage(TextFormatting color, String mod, String message)
	{
		errorQueue.add(color + "[" + mod + "]: " + message);
	}

	public static void outputErrors(EntityPlayer player)
	{
		if(errorQueue.size() > 0)
		{
			MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "Gobble Core has been provided with config errors!");
			MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "-------------------------------------------------");
			for(String s : errorQueue)
			{
				MessageUtil.sendMessageToPlayer(player, s);
			}
			MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "-------------------------------------------------");
			errorQueue.clear();
		}
	}

	public static void queueMessage(TextFormatting color, String mod, String message)
	{
		messageQueue.add(color + "[" + mod + "]: " + message);
	}

	public static void outputMessages(EntityPlayer player)
	{
		if(messageQueue.size() > 0)
		{
			for(String s : messageQueue)
				MessageUtil.sendMessageToPlayer(player, s);
			messageQueue.clear();
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(final PlayerLoggedInEvent event)
	{
		outputMessages(event.player);
		outputErrors(event.player);
	}
}