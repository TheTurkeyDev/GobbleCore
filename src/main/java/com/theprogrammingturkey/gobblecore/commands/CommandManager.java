package com.theprogrammingturkey.gobblecore.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandHandler;

public class CommandManager
{
	private static List<BaseCommandHandler> commandHandlers = new ArrayList<BaseCommandHandler>();

	// Version 0.1.5 6/18/17
	@Deprecated
	public static void registerCommandHandlers(BaseCommandHandler handler)
	{
		registerCommandHandler(handler);
	}

	public static void registerCommandHandler(BaseCommandHandler handler)
	{
		commandHandlers.add(handler);
	}

	public static void loadCommandHandlers(boolean clientSide, CommandHandler ch)
	{
		for(BaseCommandHandler handler : commandHandlers)
			ch.registerCommand(handler);
	}
}
