package me.justramon.ircbot.justabotx.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

public class ConfigHandler
{
	public static File configfile;
	public static Config config;

	public static void loadConfig()
	{
		configfile = new File("config.yml");

		if(!configfile.exists())
		{
			setupConfig(configfile);
		}

		try
		{
			YamlReader configreader = new YamlReader(new FileReader(configfile));
			config = configreader.read(Config.class);
		} 
		catch (FileNotFoundException | YamlException e)
		{
			e.printStackTrace();
		}

	}
	
	static void setupConfig(File configfile)
	{
		try
		{
			configfile.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Config config = new Config();
		
		config.nick = "JustABotX";
		config.devnick = "JABXDev";
		config.login = "JustABotX";
		config.realname = "Just a bot!";
		config.serverhostname = "irc.esper.net";
		
		List<String> channels = new ArrayList<String>();
		channels.add("#JustRamon");
		config.channels = channels;
		
		config.devchan = "#bl4ckb0tTest";
		
		List<String> operators = new ArrayList<String>();
		operators.add(genrandstring(10));
		config.operators = operators;
		
		List<String> xtrafunc = new ArrayList<String>();
		xtrafunc.add("#whatever");
		config.xtrafunc = xtrafunc;
		
		try
		{
			YamlWriter writer = new YamlWriter(new FileWriter(configfile));
			writer.write(config);
			writer.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static String genrandstring(int length)
	{
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    StringBuilder buffer = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (new Random().nextFloat() * (rightLimit - leftLimit));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    
	    return generatedString;
	}
}