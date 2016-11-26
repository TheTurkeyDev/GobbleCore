package com.theprogrammingturkey.gobblecore.util;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{
	private static List<Task> tasks = new ArrayList<Task>();

	public static boolean scheduleTask(Task task)
	{
		return tasks.add(task);
	}

	public static void tickTasks()
	{
		int numTasks = tasks.size();

		for(int i = numTasks - 1; i >= 0; i--)
		{
			Task task = tasks.get(i);
			if(task.tickTask())
			{
				task.callback();
				tasks.remove(i);
			}
		}
	}

	public void removeTask(String name, boolean removeAll)
	{
		for(int i = tasks.size() - 1; i >= 0; i--)
		{
			Task task = tasks.get(i);
			if(task.getName().equals(name))
			{
				tasks.remove(i);
				if(!removeAll)
					break;
			}
		}
	}

	public void removeTask(Task task)
	{
		tasks.remove(task);
	}
}