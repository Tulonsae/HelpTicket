package com.github.roundar.helpticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;


public class FileManager {
	
	HelpTicket helpTicket;
	
	private File openTickets, archive;
	
	public FileManager(HelpTicket helpTicket) {

		this.helpTicket = helpTicket;
		
		generateFiles();
		
	}
	
	public short getLastClosedId(){
		
		Scanner scanner;
		Short id = 0;
		
		try{			
			scanner = new Scanner(archive);			
		} catch (FileNotFoundException FNFE) {			
			helpTicket.getLogger().log(Level.SEVERE, "Unable to find archive.txt", FNFE );
			return 0;
		}
		
		scanner.useDelimiter(",");
		
		while(scanner.hasNextLine())
			if(scanner.hasNextShort()) {				
				id = scanner.nextShort();
				scanner.nextLine();
				helpTicket.getLogger().info(id.toString());
			}
		
		scanner.close();
		
		return id;
		
	}
	
	public void archiveTicket(Ticket ticket) {
		
		try{
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(archive, true));
			writer.write( ticket.toString() );
			writer.newLine();
			writer.flush();
			writer.close();
			
		} catch (IOException IOE) {
			
			helpTicket.getLogger().log(Level.WARNING, "Error adding ticket \"" + ticket + "\" to archive.txt", IOE );
			
		}
		
	}
	
	public void appendOpenTicket(Ticket ticket) {
		
		try{
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(openTickets, true));
			writer.write( ticket.toString() );
			writer.newLine();
			writer.flush();
			writer.close();
			
		} catch (IOException IOE) {			
			//Severe exception. If open tickets are not saved they will be lost on shutdown
			helpTicket.getLogger().log(Level.SEVERE, "Error saving ticket \"" + ticket.toString() + "\" to archive.txt", IOE );
			
		}
		
	}
	
	public LinkedList<Ticket> getOpenTickets(){
		
		LinkedList<Ticket> tickets = new LinkedList<Ticket>();
		
		Ticket ticket = null;
		
		short currentId = getLastClosedId();

		Scanner scanner;
			
		try {
			scanner = new Scanner(openTickets);
		} catch (FileNotFoundException FNFE) {
			helpTicket.getLogger().log(Level.SEVERE, "Unable to find openTickets file.", FNFE );
			return tickets;
		}
		
		while(scanner.hasNextLine())
			try {
				ticket = new Ticket( scanner.nextLine() );
				
				if( ticket.id() >= currentId ){
					tickets.add( ticket );
					helpTicket.getLogger().info(ticket.toString());
				}
				
			} catch (ParseException PE) {
				
				helpTicket.getLogger().log(Level.SEVERE, "Error parsing ticket \"" + ticket.toString() + "\"", PE );
				
			}
		
		scanner.close();

		openTickets.delete();
		generateFiles();
		
		Iterator<Ticket> iterator = tickets.iterator();
		
		try {
			
			BufferedWriter writer = new BufferedWriter( new FileWriter(openTickets, true) );
		
			while(iterator.hasNext()){
				writer.write( iterator.next().toString() );
				writer.newLine();
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException IOE) {
			
			helpTicket.getLogger().log(Level.SEVERE, "Error flushing open tickets.", IOE );
			
		}
		
		return tickets;
		
	}
	
	private void generateFiles(){
		
		try{
			
			helpTicket.getDataFolder().mkdirs();
		
			openTickets = new File(helpTicket.getDataFolder(), "openTickets.txt");
			archive = new File(helpTicket.getDataFolder(), "archive.txt");
			
			openTickets.createNewFile();
			archive.createNewFile();
			
		} catch (IOException IOE) {
			
			helpTicket.getLogger().log(Level.SEVERE, "Error creating necessary files.", IOE );
			
			if( !( openTickets.exists() && archive.exists() ) ) {
				
				helpTicket.getLogger().log(Level.SEVERE, "Necessary files could not be created.  Disabling HelpTicket.");
				
				helpTicket.getServer().getPluginManager().disablePlugin(helpTicket);
				
			}
			
		}
		
	}
	
}
















