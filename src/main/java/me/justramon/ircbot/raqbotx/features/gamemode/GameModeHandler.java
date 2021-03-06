package me.justramon.ircbot.raqbotx.features.gamemode;

import me.justramon.ircbot.raqbotx.config.ConfigHandler;
import me.justramon.ircbot.raqbotx.core.Core;
import me.justramon.ircbot.raqbotx.features.gamemode.games.Hangman;
import me.justramon.ircbot.raqbotx.features.gamemode.games.TicTacToe;

import java.util.HashMap;
import java.util.LinkedList;

public class GameModeHandler
{
	public static LinkedList<IGame> games = new LinkedList<IGame>();
	public static HashMap<String, String> currentlyPlaying = new HashMap<>();
    private static LinkedList<String> chansPlaying = new LinkedList<String>();

	public GameModeHandler()
	{
		games.add(new TicTacToe());
		games.add(new Hangman());
		/*if(Core.dev)
		{

		}*/
	}

	public static boolean isPlaying(String channel)
	{
		if(!(chansPlaying == null))
		{
			for(String s : chansPlaying)
			{
				if(channel.equals(s))
					return true;
			}
		}
		return false;
	}

	public static void enableGameMode(String channel, String game) throws Exception
	{
		if(hasGameModeEnabled(channel))
		{
			chansPlaying.add(channel);
			currentlyPlaying.put(channel, game);
			getGameByName(game).setup(channel);
		}
		else
			Core.bot.sendIRC().message(channel, "This channel does not have GameMode enabled.");
	}

	public static void disableGameMode(String channel)
	{
		chansPlaying.remove(channel);
		currentlyPlaying.remove(channel);
		Core.bot.sendIRC().message(channel, "Game disabled.");
	}

	private static boolean hasGameModeEnabled(String channel)
	{
		for(String s : ConfigHandler.config.gameChannels)
		{
			if(channel.equals(s))
				return true;
		}
		return false;
	}

	public static String listAllGames()
	{
		String list = "";
		for(IGame game : games)
		{
			list = list + game.getName() + ", ";
		}
		if(!list.equals(""))
		{
			list = list.substring(0, list.length() - 2) + ".";
		}
		else
			list = "None.";
		return list;
	}

	public static IGame getGameByName(String gameName)
	{
		for(IGame game : games)
		{
			if(game.getName().equalsIgnoreCase(gameName))
			{
				return game;
			}
		}
		return null;
	}
}
