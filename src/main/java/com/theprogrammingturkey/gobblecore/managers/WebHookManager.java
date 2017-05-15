package com.theprogrammingturkey.gobblecore.managers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;
import com.theprogrammingturkey.gobblecore.util.HTTPUtil;

public class WebHookManager
{
	private static List<ModWebHook> mods = new ArrayList<ModWebHook>();

	public static void registerHook(ModWebHook modHook)
	{
		mods.add(modHook);
	}

	@SuppressWarnings("unchecked")
	public static void processHooks()
	{
		List<CustomEntry<String, String>> params = new ArrayList<CustomEntry<String, String>>();

		for(ModWebHook hook : mods)
			params.add(new CustomEntry<String, String>(hook.getMod().getName(), hook.getMod().getVersion()));

		JsonElement json;
		try
		{
			json = HTTPUtil.getWebFile("https://api.theprogrammingturkey.com/GobbleCoreAPI.php", (CustomEntry<String, String>[]) params.toArray());
		} catch(Exception e)
		{
			e.printStackTrace();
			return;
		}

		if(!json.isJsonObject())
			return;

		for(ModWebHook hook : mods)
			if(json.getAsJsonObject().has(hook.mod.getName()))
				hook.onResponse(json.getAsJsonObject().get(hook.mod.getName()));
	}

	public static abstract class ModWebHook
	{
		private IModCore mod;

		public ModWebHook(IModCore mod)
		{
			this.mod = mod;
		}

		public IModCore getMod()
		{
			return this.mod;
		}

		public abstract void onResponse(JsonElement json);
	}
}
