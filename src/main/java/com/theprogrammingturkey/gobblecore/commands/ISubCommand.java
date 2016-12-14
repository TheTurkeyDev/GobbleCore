package com.theprogrammingturkey.gobblecore.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract interface ISubCommand
{
	public boolean execute(MinecraftServer server, ICommandSender sender, String[] args);
	public String getDescription();
	public boolean canNonPlayerUse();
}