package Musicplayerpack;

import java.util.Iterator;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;



class Song
{
	String title;
	double duration;

	public Song(String title,double duration)
	{
		this.title=title;
		this.duration=duration;
	}
	public Song() 
	{
		
	}
	public String getTitle()
	{
		return title;
	}
	public double getDuration()
	{
		return duration;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Song("+"title='"+title+'\''+",duration="+duration+'}';
	}
	
}

class Album
{
	private String name;
	private String artist;
	private ArrayList<Song> songs;
	
	public Album(String name,String artist)
	{
		this.name=name;
		this.artist=artist;
		this.songs=new ArrayList<Song>();
	}
	public Album()
	{
		
	}
	public boolean addSong(String title,double duration)
	{
		if(findSong(title)==null)
		{
			songs.add(new Song(title,duration));
			System.out.println(title+"succesfully added to the list");
			return true;
		}
		else
		{
			System.out.println("Song with name"+title+"already exist in the list");
			return false;
			
		}
	}
	private Song findSong(String title) 
	{
		for (Song checkedSong:songs)
		{
			if(checkedSong.getTitle().equals(title)) return checkedSong;
		}
		return null;
	}
	
	public boolean addToPlaylist (int trackNumber,LinkedList<Song> Playlist)
	{
		int index = trackNumber-1;
		if(index>0 && index <=this.songs.size())
		
			{
				Playlist.add(this.songs.get(index));
				return true;
			}
		System.out.println("this album not have song with tracknumber"+trackNumber);
		return false;
	}
	
	public boolean addToPlaylist(String title,LinkedList<Song>Playlist)
	{
		for(Song checkedSong : this.songs)
		{
			if (checkedSong.getTitle().equals(title))
			{
				Playlist.add(checkedSong);
				return true;
				
			}
		}
		System.out.println(title+"there is no such song in album");
		return false;
	}
	
}

public class Mp3musicplayer 
{
	private static ArrayList<Album> l=new ArrayList<>();
	
  public static void main(String args[])
  {
	  Album e= new Album("Album","Anirudh");
	  
	  e.addSong("VathiComing", 4.5);
	  e.addSong("Thalaiva", 1.9);
	  e.addSong("Tholkattum", 4.6);
	  e.addSong("Marri", 5);
	  
	  l.add(e);
	  
	  e=new Album("Album2","AR Rehaman");

	  e.addSong("Chakde india", 4.5);
	  e.addSong("Pudhu vellai mallai", 3.2);
	  e.addSong("Padaiyappa title song", 4.4);
	  
	  l.add(e);
	  
	  LinkedList<Song> Playlist= new LinkedList<>();
	  
	  l.get(0).addToPlaylist("VathiComing", Playlist);
	  l.get(0).addToPlaylist("Thalaiva", Playlist);
	  l.get(1).addToPlaylist("AlamathiHabbibo", Playlist);
	  l.get(1).addToPlaylist("EtherNichal", Playlist);
	  
	  play(Playlist);
	  
  }
  
  private static void play(LinkedList<Song> Playlist)
  {
	Scanner sc= new Scanner(System.in);
	boolean quit=false;
	boolean forward=true;
	ListIterator<Song> listiterator=Playlist.listIterator();
	
	if(Playlist.size()==0)
	{
		System.out.println("This playlist has no song");
	}
	else
	{
		System.out.println("Now playing"+listiterator.next().toString());
		printMenu();
	}
	while(!quit){
		{
			int action=sc.nextInt();
			sc.nextLine();
			
			switch(action)
			{
			case 0:
				System.out.println("Playlist complete");
				quit=true;
				break;
				
				
			case 1:
				if(!forward)
				{
					if(listiterator.hasNext())
					{
						listiterator.next();
						
					}
					forward = true;
				}
				if(listiterator.hasNext())
				{
					System.out.println("Now playing"+listiterator.next().toString());
				}
				else
				{
					System.out.println("no song available,reached to the end of the list");
					forward=false;
				}
				break;
			
			case 2:
				if(forward)
				{
					if(listiterator.hasPrevious())
					{
						System.out.println("Now playing"+listiterator.previous().toString());
					}
					else
					{
						System.out.println("we are at the first song");
						forward=false;
					}
				}
					break;
					
			 case 3:
						if(forward)
						{
							if(listiterator.hasPrevious())
							{
								System.out.println("now playiing"+listiterator.next().toString());
							
						}
							else
							{
								System.out.println("we have raeched to the end of list");
							}
				}
						break;
						
			 case 4:
                   printList(Playlist);
                   break;
                   
			 case 5:
				 printMenu();
				 break;
			
			 case 6:
				 if(Playlist.size()>0)
				 {
					 listiterator.remove();
					 
					if(listiterator.hasNext())
					{
						System.out.println("now playing"+listiterator.next().toString());	
					}
					else
					{
						if(listiterator.hasPrevious())
							System.out.println("now playing"+listiterator.previous().toString());	
					}
					 
				 }
			}
		}
	}
	  
  }
  private static void printMenu()
  {
	  System.out.println("Avalaible option\n press");
	  System.out.print
	  ("0-to quit\n"+
	  
	  "1-to play next song\n"+
	  
	  "2-to play prevoius song\n"
	  
	  +"3-to replay next current song\n"
	  
	  +"4-list of all song\n"
	  
	  +"5-print all available optional\n"
	  
	  +"6-delete current song");
	  
  }
  private static void printList(LinkedList<Song>Playlist)
  {
	  Iterator<Song> iterator=Playlist.iterator();
	  System.out.println("-------------------------");
	  
	  while(iterator.hasNext())
	  {
		  System.out.println(iterator.next());
	  }
	  System.out.println("-------------------------");
	  
  }
}
