package com.theprogrammingturkey.gobblecore.modhooks;

public abstract class BaseModHook
{
	private String modName;
	
	public BaseModHook(String mod)
	{
		this.modName = mod;
	}
	
	public abstract void initHook();
	
	public String getModName()
	{
		return this.modName;
	}
}
