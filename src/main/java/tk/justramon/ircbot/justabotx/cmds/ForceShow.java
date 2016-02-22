package tk.justramon.ircbot.justabotx.cmds;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justabotx.Core;
import tk.justramon.ircbot.justabotx.features.JRWUpdates;
import tk.justramon.ircbot.justabotx.features.MojangUpdates;
import tk.justramon.ircbot.justabotx.util.Ops;

public class ForceShow
{
	public static void debugForceShow(MessageEvent<PircBotX> event, String[] args) throws IOException
	{
		if(Ops.isOp(event))
		{
			if(Core.wip)
			{
				if(args[1].toLowerCase().equals("mojang"))
				{
					MojangUpdates.debugForceShow();
				}
				if(args[1].toLowerCase().equals("justramon") || args[1].toLowerCase().equals("jr"))
				{
					JRWUpdates.debugForceShow(event);
				}
			}
			else
				event.respond("This is a debug command in wip mode.");
		}
	}
}
