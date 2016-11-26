package com.theprogrammingturkey.gobblecore.events;

import com.theprogrammingturkey.gobblecore.util.Scheduler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.relauncher.Side;

public class TickListener
{

	@SubscribeEvent
	public void onTick(ServerTickEvent event)
	{
		if(event.side == Side.SERVER && event.type == Type.SERVER && event.phase == Phase.START)
			Scheduler.tickTasks();
	}
}
