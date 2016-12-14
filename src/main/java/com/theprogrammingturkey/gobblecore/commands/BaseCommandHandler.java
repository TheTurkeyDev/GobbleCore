package com.theprogrammingturkey.gobblecore.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.theprogrammingturkey.gobblecore.util.MessageUtil;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

public class BaseCommandHandler extends CommandBase
{
	private List<String> subCommands = new ArrayList<String>();
	private HashMap<String, ISubCommand> commandList = new HashMap<String, ISubCommand>();

	private List<String> aliases = new ArrayList<String>();

	private String commandName;

	public BaseCommandHandler(String commandName)
	{
		this.commandName = commandName;
	}

	public void registerSubCommand(String subName, ISubCommand command)
	{
		commandList.put(subName, command);
		subCommands.add(subName);
	}

	public void registerSubCommandAlias(String subName, String alias)
	{
		commandList.put(alias, commandList.get(subName));
	}

	public void addCommandAliases(String... alias)
	{
		for(String a : alias)
			this.aliases.add(a);
	}

	@Override
	public String getCommandName()
	{
		return this.commandName;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("/");
		builder.append(commandName);
		builder.append("(");
		for(String s : subCommands)
		{
			builder.append(s);
			builder.append(", ");
		}
		builder.delete(builder.length() - 2, builder.length());
		builder.append(")");

		return builder.toString();
	}

	@Override
	public List<String> getCommandAliases()
	{
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args)
	{
		EntityPlayer player = null;
		if(sender instanceof EntityPlayer)
			player = (EntityPlayer) sender;

		if(args.length <= 0 || args == null)
		{
			MessageUtil.sendMessageToPlayer(player, " Type /" + commandName + " help for a list of commands!");
			return;
		}
		if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))
		{
			
		}
		else if(commandList.containsKey(args[0].toLowerCase()))
		{
			this.commandList.get(args[0].toLowerCase()).execute(server, sender, args);
		}
		else
		{
			MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "No such command! Type /" + commandName + " help for a list of commands and a desctription!");
		}
	}
}
