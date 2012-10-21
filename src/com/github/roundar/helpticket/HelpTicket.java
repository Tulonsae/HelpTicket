package com.github.roundar.helpticket;

import org.bukkit.plugin.java.JavaPlugin;

public class HelpTicket extends JavaPlugin {
	
	private TicketManager ticketManager;
	private FileManager fileManager;
	
	@Override
	public void onEnable(){
		
		fileManager = new FileManager(this);
		
		ticketManager = new TicketManager(this);
		
		getCommand("ticket").setExecutor(new TicketCommandExecutor(this));
		getCommand("readticket").setExecutor(new ReadTicketCommandExecutor(this));
		getCommand("passticket").setExecutor(new PassTicketCommandExecutor(this));
		getCommand("closeticket").setExecutor(new CloseTicketCommandExecutor(this));
		getCommand("ticketcount").setExecutor(new TicketCountCommandExecutor(this));
		getCommand("HelpTicketBlock").setExecutor(new HelpTicketBlockCommandExecutor(this));
		getCommand("HelpTicketUnblock").setExecutor(new HelpTicketUnblockCommandExecutor(this));
		
	}

	public TicketManager ticketManager(){
		
		return ticketManager; 
		
	}
	
	public FileManager fileManager(){ 
		
		return fileManager; 
		
	}
	
}
