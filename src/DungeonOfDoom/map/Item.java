package DungeonOfDoom.map;
import javax.swing.ImageIcon;

public class Item {
	private ImageIcon wall;
	private ImageIcon ground;
	private ImageIcon player ;
	private ImageIcon gold ;
	private ImageIcon door1;
	private ImageIcon door2;
	private ImageIcon door3 ;
	

	private ImageIcon door4;
	private ImageIcon blue_potion ;
	private ImageIcon red_potion ;
	private ImageIcon mini_room ;
	private ImageIcon current_room ;
	private ImageIcon vortex;
	private ImageIcon bot;

	public Item(String wall, String ground, String player, String gold, String door1, String door2,
			String door3, String door4, String blue_potion,String red_potion, String vortex, String bot) {
		super();
		this.wall = new ImageIcon("images/"+wall+".png");
		this.ground  = new ImageIcon("images/"+ground+".png");
		this.player= new ImageIcon("images/"+player+".png");
		this.gold = new ImageIcon("images/"+gold+".png");
		this.door1 = new ImageIcon("images/"+door1+".png");
		this.door2  = new ImageIcon("images/"+door2+".png");
		this.door3= new ImageIcon("images/"+door3+".png");
		this.door4  = new ImageIcon("images/"+door4+".png");
		this.blue_potion= new ImageIcon("images/"+blue_potion+".png");
		this.red_potion=new ImageIcon("images/"+red_potion+".png");
		this.vortex=new ImageIcon("images/"+vortex+".png");
		this.bot=new ImageIcon("images/"+bot+".png");
		
	}
	
	public Item(String mini_room,String current_room) {
		this.mini_room = new ImageIcon("images/"+mini_room+".png");
		this.current_room = new ImageIcon("images/"+current_room+".png");
	}
	public ImageIcon getWall() {
		return wall;
	}



	public ImageIcon getGround() {
		return ground;
	}


	public ImageIcon getPlayer() {
		return player;
	}


	public ImageIcon getGold() {
		return gold;
	}


	public ImageIcon getDoor1() {
		return door1;
	}


	public ImageIcon getDoor2() {
		return door2;
	}



	public ImageIcon getDoor3() {
		return door3;
	}



	public ImageIcon getDoor4() {
		return door4;
	}



	public ImageIcon getBlue_potion() {
		return blue_potion;
	}
	public ImageIcon getRed_potion() {
		return red_potion;
	}
	public ImageIcon getVortex() {
		return vortex;
	}
	public ImageIcon getMini_room() {
		return mini_room;
	}



	public ImageIcon getCurrent_room() {
		return current_room;
	}


	
	public ImageIcon getBot() {
		return bot;
	}
}
