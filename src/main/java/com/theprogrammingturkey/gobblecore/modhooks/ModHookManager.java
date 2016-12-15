package com.theprogrammingturkey.gobblecore.modhooks;

import net.minecraftforge.fml.common.Loader;

public class ModHookManager
{
	public static boolean loadModHook(BaseModHook modhook)
	{
		if(Loader.isModLoaded(modhook.getModName()))
		{
			modhook.initHook();
			return true;
		}
		return false;
	}
}
