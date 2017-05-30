package com.theprogrammingturkey.gobblecore.network;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkLoader
{
	private IModCore subMod = GobbleCore.instance;
	private SimpleNetworkWrapper simpleNetwork;

	private int id = 0;

	public NetworkLoader(IModCore mod)
	{
		this.subMod = mod;
		simpleNetwork = NetworkRegistry.INSTANCE.newSimpleChannel(subMod.getModID());
	}

	public <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
	{
		simpleNetwork.registerMessage(messageHandler, requestMessageType, id++, side);
	}
}
