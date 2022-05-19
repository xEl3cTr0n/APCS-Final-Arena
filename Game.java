import java.util.ArrayList;
import java.util.Scanner;
public class Game {
	private player slayer;
	private vampire[] arrVamp;
	private ArrayList<Heal> heals;
	private int aliveVampires;
	private Arena arena;
	private ArrayList<Poison> objects;
	public Game(int Sizex, int Sizey, int numOfVampires) {
		arrVamp = new vampire[numOfVampires];
		aliveVampires = numOfVampires;
		slayer = new player(10, (int) (Math.random()*Sizex), (int) (Math.random()*Sizey));
		arena = new Arena(Sizex, Sizey);
		objects = new ArrayList<Poison>();
		heals = new ArrayList<Heal>();
		populateArena();
	}
	public void populateArena() {
		int max = (int) (arena.get_x_size()+arena.get_y_size())/6;
		int min = (int) (arena.get_x_size()+arena.get_y_size())/10;
		int numHeals = (int) (Math.random()*(max) + min);
		for (int i = 0; i < numHeals; i++) {
			Heal h = new Heal((int) (Math.random()*arena.xSize), (int) (Math.random()*arena.ySize));
			for (int j = 0; j < heals.size(); j++) {
				while (h.get_x() == heals.get(j).get_x() && h.get_y() == heals.get(j).get_y()) {
					h = new Heal((int) (Math.random()*arena.xSize), (int) (Math.random()*arena.ySize));
				}
			}
			heals.add(h);
		}
		for (int i = 0; i < arrVamp.length; i++) {
			arrVamp[i] = new vampire((int) (Math.random()*arena.xSize), (int) (Math.random()*arena.ySize));
			while (arrVamp[i].get_x_pos() == slayer.get_x_pos() && arrVamp[i].get_y_pos() == slayer.get_y_pos() || isHealAt(arrVamp[i].get_x_pos(), arrVamp[i].get_y_pos())) {
				arrVamp[i] = new vampire((int) (Math.random()*arena.xSize), (int) (Math.random()*arena.ySize));
			}
		}

		//add graphics as well
		
	}
	public void playerMove() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter w, a, s, d to move, x to drop poison: ");
		String line = input.nextLine();
		if (line.toLowerCase().contains("w")) {
			slayer.move(0);
		}
		else if (line.toLowerCase().contains("s")) {
			slayer.move(1);
		}
		else if (line.toLowerCase().contains("d")) {
			slayer.move(2);
		}
		else if (line.toLowerCase().contains("a")) {
			slayer.move(3);
		}
		else if (line.toLowerCase().contains("x")) {
			Poison p = new Poison(slayer.get_x_pos(), slayer.get_y_pos());
			objects.add(p);
		}
		else {
			System.out.println();
			System.out.println("Sorry but that is not a button. Please try another. ");
			System.out.println();
			playerMove();
		}
	}
	public int getNumVampsAt(int xPos, int yPos) {
		int numOfVampiresAtPos = 0;
		for (int i = 0; i < arrVamp.length; i++) {
			if (arrVamp[i].get_x_pos() == xPos && arrVamp[i].get_y_pos() == yPos && arrVamp[i].check_isAlive()) {
				numOfVampiresAtPos++;
			}
		}
		return numOfVampiresAtPos;
		
	}
	public boolean isPoisonAt(int xPos, int yPos) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).get_x_pos() == xPos && objects.get(i).get_y_pos() == yPos) {
				return true;
			}
		}
		return false;
	}

	public boolean isHealAt(int xPos, int yPos) {
		for (int i = 0; i < heals.size(); i++) {
			if (heals.get(i).get_x() == xPos && heals.get(i).get_y() == yPos) {
				int h = slayer.getNumLives() + 1;
				slayer.setNumLives(h);
				return true;
			}
		}
		return false;
	}
	public void removePoisonAt(int xPos, int yPos) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).get_x_pos() == xPos && objects.get(i).get_y_pos() == yPos) {
				objects.remove(i);
			}
		}
	}
	public void removeHealAt(int xPos, int yPos) {
		for (int i = 0; i < heals.size(); i++) {
			if (heals.get(i).get_x() == xPos && heals.get(i).get_y() == yPos) {
				heals.remove(i);
			}
		}
	}
	public player getPlayer() {
		return slayer;
		
	}
	public void display() {
		arena.updateArena(this);
		for (int i = 0; i < 4; i++) {
			System.out.println("-------");
		}
		for (int i = 0; i < arena.get_arr2DCharacter().length; i++) {
			for (int j = 0; j < arena.get_arr2DCharacter()[0].length; j++) {
				System.out.print(arena.get_arr2DCharacter()[i][j]);
			}
			System.out.println();
		}
		System.out.println("Number of Lives: " + slayer.getNumLives());
		System.out.println("Current Round:" + arena.get_currentRound());
		System.out.println("Number of Alive Vampires: " + aliveVampires);
	}
	public void playGame() {
		display();
		while(true) {
			if (slayer.getNumLives() == 0) {
				System.out.println("GAME OVER!!!! Better luck next time.");
				System.out.println("If you want to play again, click 'r' ");
				Scanner g = new Scanner(System.in);
				if (g.nextLine().toLowerCase().equals("r")) {
					int[] arr = startGame();
					Game play = new Game(arr[0], arr[1], 20);
					play.playGame();
				}
				System.exit(0);
				break;
			}
			if (aliveVampires == 0) {
				System.out.println("Congratulations, you have beaten the game. Give yourself a pat on the back.");
				System.out.println("If you want to play again, click 'r' ");
				Scanner g = new Scanner(System.in);
				if (g.nextLine().toLowerCase().equals("r")) {
					int[] arr = startGame();
					Game play = new Game(arr[0], arr[1], 20);
					play.playGame();
				}
				System.exit(0);
				break;
			}
			playerMove();
			//System.out.println(arrVamp.length);
			for (int i = 0; i < arrVamp.length; i++) {
				if (arrVamp[i].check_isAlive() == true) {
						arrVamp[i].moveVampire(slayer);
					if (isPoisonAt(arrVamp[i].get_x_pos(), arrVamp[i].get_y_pos())) {
						arrVamp[i].kill();
						removePoisonAt(arrVamp[i].get_x_pos(), arrVamp[i].get_y_pos());
						aliveVampires--;
						continue;
					}
					if (isPoisonAt(arrVamp[i].get_x_pos(), arrVamp[i].get_y_pos())) {
						arrVamp[i].moveVampire(slayer);
					}
					if (slayer.get_x_pos() == arrVamp[i].get_x_pos() && slayer.get_y_pos() == arrVamp[i].get_y_pos()) {
						System.out.println("You lose");
						break;
					}
				}
			}
			int x = slayer.get_x_pos();
			int y = slayer.get_y_pos();
			if (isHealAt(x, y)) {
				removeHealAt(x, y);
			}
			int vampLocation = 0;
			vampLocation += getNumVampsAt(x + 1, y);
			vampLocation += getNumVampsAt(x - 1, y);
			vampLocation += getNumVampsAt(x, y + 1);
			vampLocation += getNumVampsAt(x, y - 1);
			slayer.setNumLives(slayer.getNumLives() - vampLocation);
			arena.incrementRound();
			display();
		}
	}
	public static int[] startGame() {
		Scanner n = new Scanner(System.in);
		System.out.println("What dimensions do you want the game to be? Example: 25 30 (Minimum is 15 15)");
		String str = n.nextLine();
		String parts[] = str.split(" ");
		try {
			parts[1].length();
		}
		catch (Exception e) {
			System.exit(0);
		}
		while (Integer.parseInt(parts[0]) < 15 || Integer.parseInt(parts[1]) < 15) {
			System.out.println("Sorry, but those dimensions do not fit the minimum criteria to create an arena. Please give new dimensions that are viable.");
			str = n.nextLine();
			parts = str.split(" ");
		}
		int size_x = Integer.parseInt(parts[0]);
		int size_y = Integer.parseInt(parts[1]);
		int[] sizes = new int[2];
		sizes[0] = size_x;
		sizes[1] = size_y;
		return sizes;
	}
	public static void main(String[] args) {
		int[] arr = startGame();
		Game play = new Game(arr[0], arr[1], 20);
		play.playGame();
	}
}
