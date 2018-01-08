package com.theprogrammingturkey.gobblecore.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.HTTPUtil;
import com.theprogrammingturkey.gobblecore.util.HTTPUtil.RequestType;

public class WebHookManager
{
	private static List<ModWebHook> mods = new ArrayList<ModWebHook>();

	public static void registerHook(ModWebHook modHook)
	{
		mods.add(modHook);
	}

	public static void processHooks()
	{
		Map<String, String> params = new HashMap<String, String>();

		for(ModWebHook hook : mods)
			params.put(hook.getMod().getName(), hook.getMod().getVersion());

		JsonElement json;
		try
		{
			json = HTTPUtil.getWebFile("https://api.theprogrammingturkey.com/GobbleCoreAPI.php", RequestType.POST, new HashMap<String, String>(), params);
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