package com.theprogrammingturkey.gobblecore.commands;

public abstract class SimpleSubCommand implements ISubCommand
{
	private String description;
	private boolean nonPlayerUseable;

	public SimpleSubCommand(String desc)
	{
		this(desc, false);
	}

	public SimpleSubCommand(String desc, boolean nonPlayerUseable)
	{
		this.description = desc;
		this.nonPlayerUseable = nonPlayerUseable;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public boolean canNonPlayerUse()
	{
		return this.nonPlayerUseable;
	}

}
