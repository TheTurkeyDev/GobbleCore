package com.theprogrammingturkey.gobblecore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.client.ClientCommandHandler;

public class CommandManager
{
	private static List<BaseCommandHandler> commandHandlers = new ArrayList<BaseCommandHandler>();

	public static void registerCommandHandlers(BaseCommandHandler handler)
	{
		commandHandlers.add(handler);
	}

	public static void loadCommandHandlers(boolean clientSide, CommandHandler ch)
	{
		for(BaseCommandHandler handler : commandHandlers)
		{
			if(clientSide && handler.isClientSideCommands())
				ClientCommandHandler.instance.registerCommand(handler);
			else if(!clientSide && !handler.isClientSideCommands())
				ch.registerCommand(handler);
		}
	}
}
