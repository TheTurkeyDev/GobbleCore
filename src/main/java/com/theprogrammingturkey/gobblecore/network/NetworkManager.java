package com.theprogrammingturkey.gobblecore.network;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkManager
{
	private static Map<IModCore, CustomEntry<INetworkHandler, NetworkLoader>> networkHandlers = new HashMap<IModCore, CustomEntry<INetworkHandler, NetworkLoader>>();

	public static void registerNetworkHandler(INetworkHandler handler, IModCore mod)
	{
		networkHandlers.put(mod, new CustomEntry<INetworkHandler, NetworkLoader>(handler, new NetworkLoader(mod)));
	}

	public static void registerPackets()
	{
		for(IModCore mod : networkHandlers.keySet())
		{
			Loader.instance().setActiveModContainer(Loader.instance().getIndexedModList().get(mod.getModID()));
			CustomEntry<INetworkHandler, NetworkLoader> values = networkHandlers.get(mod);
			values.getKey().registerPacket(values.getValue());
		}
		Loader.instance().setActiveModContainer(Loader.instance().getIndexedModList().get(GobbleCore.MODID));
	}

	public static SimpleNetworkWrapper getSimpleNetwork(IModCore mod)
	{
		return networkHandlers.get(mod).getValue().getSimpleNetwork();
	}
}
