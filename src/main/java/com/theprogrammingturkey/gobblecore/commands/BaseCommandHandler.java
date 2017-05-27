package com.theprogrammingturkey.gobblecore.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.theprogrammingturkey.gobblecore.IModCore;
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

	private IModCore mod;

	public BaseCommandHandler(IModCore mod, String commandName)
	{
		this.commandName = commandName;
		this.mod = mod;
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
		if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h") && args.length == 2)
		{
			ISubCommand command = this.commandList.get(args[0].toLowerCase());
			if(command != null)
				MessageUtil.sendMessageToPlayer(player, command.getDescription());
			else
				MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "No such command!");
		}
		else if(args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v"))
		{
			MessageUtil.sendMessageToPlayer(player, TextFormatting.GREEN + this.mod.getName() + " version " + this.mod.getVersion());
		}
		else if(args[0].equalsIgnoreCase("listCommands") || args[0].equalsIgnoreCase("lc"))
		{
			StringBuilder builder = new StringBuilder();
			builder.append("Valid commands are: ");
			for(String command : commandList.keySet())
			{
				builder.append(command);
				builder.append(", ");
			}
			builder.delete(builder.length() - 2, builder.length());
			MessageUtil.sendMessageToPlayer(player, builder.toString());
		}
		else if(commandList.containsKey(args[0].toLowerCase()))
		{
			this.commandList.get(args[0].toLowerCase()).execute(server, sender, args);
		}
		else
		{
			MessageUtil.sendMessageToPlayer(player, TextFormatting.DARK_RED + "No such command! Type /" + commandName + " listcommands or help for a list of commands and a desctription!");
		}
	}
}
