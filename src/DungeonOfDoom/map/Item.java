package DungeonOfDoom.map;
import javax.swing.ImageIcon;

/**
 * Class for handling all icons and items in game
 */
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
	
	/**
	 * Constructor. Sets up ImageIcons for use when drawing maps.
	 * 
	 * Parameter names should be self-explanatory - they are all game icons or items
	 * @param wall
	 * @param ground
	 * @param player
	 * @param gold
	 * @param door1
	 * @param door2
	 * @param door3
	 * @param door4
	 * @param blue_potion
	 * @param vortex
	 */
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
		this.vortex=new ImageIcon("images/"+vortex+".png");
		this.red_potion=new ImageIcon("images/"+red_potion+".png");
		this.vortex=new ImageIcon("images/"+vortex+".png");
		this.bot=new ImageIcon("images/"+bot+".png");
	}
	
	public Item(String mini_room, String current_room) {
		this.mini_room = new ImageIcon("images/"+mini_room+".png");
		this.current_room = new ImageIcon("images/"+current_room+".png");
	}
	
	public ImageIcon getWall() {
		return wall;
	}

	public void setWall(ImageIcon wall) {
		this.wall = wall;
	}

	public ImageIcon getGround() {
		return ground;
	}

	public void setGround(ImageIcon ground) {
		this.ground = ground;
	}

	public ImageIcon getPlayer() {
		return player;
	}

	public void setPlayer(ImageIcon player) {
		this.player = player;
	}

	public ImageIcon getGold() {
		return gold;
	}

	public void setGold(ImageIcon gold) {
		this.gold = gold;
	}

	public ImageIcon getDoor1() {
		return door1;
	}

	public void setDoor1(ImageIcon door1) {
		this.door1 = door1;
	}

	public ImageIcon getDoor2() {
		return door2;
	}

	public void setDoor2(ImageIcon door2) {
		this.door2 = door2;
	}

	public ImageIcon getDoor3() {
		return door3;
	}

	public void setDoor3(ImageIcon door3) {
		this.door3 = door3;
	}

	public ImageIcon getDoor4() {
		return door4;
	}

	public void setDoor4(ImageIcon door4) {
		this.door4 = door4;
	}

	public ImageIcon getBlue_potion() {
		return blue_potion;
	}

	public void setBlue_potion(ImageIcon blue_potion) {
		this.blue_potion = blue_potion;
	}
	public ImageIcon getRed_potion() {
		return red_potion;
	}
	public ImageIcon getVortex() {
		return vortex;
	}
	public void setVortex(ImageIcon vortex) {
		this.vortex = vortex;
	}
	public ImageIcon getMini_room() {
		return mini_room;
	}

	public void setMini_room(ImageIcon mini_room) {
		this.mini_room = mini_room;
	}

	public ImageIcon getCurrent_room() {
		return current_room;
	}

	public ImageIcon getBot() {
		return bot;
	}
}
