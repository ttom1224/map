import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class A1063330_checkpoint6_DemoFrame extends JFrame {
	// Description : Width of Frame
	private int FWidth;
	// Description : Height of Frame
	private int FHeight;
	// Description : the displaysize of the map
	private int jfScaler;
	// Description : the obstacle images set. bar_id -> obstacle image
	private HashMap<Integer, Image> obstacleImg;
	// Description : the filenames of the obstacle image set. bar_id -> filename
	private HashMap<Integer, String> typeChar;
	// Description : the obstacle location set queryed from database
	private ArrayList<Integer[]> obstacleDataStructure;
	// Description : the obstacle location set in GUI index version.
	private ArrayList<A1063330_checkpoint6_Block> obstacle;
	// Description : the object to query data.
	private A1063330_checkpoint6_QueryDB querydb;
	// Description : the player object.
	private A1063330_checkpoint6_Player player;
	// Description : the X axis that mouse pressed.
	private static int PressedX;
	// Description : the Y axis that mouse pressed.
	private static int PressedY;
	// Description : the X axis that mouse release.
	private static int ReleasedX;
	// Description : the Y axis that mouse release.
	private static int ReleasedY;
	// Description : the time that the player moving in per step.
	private static int milliseconds;
	// Description : algorithm BFS : 1, DFS : 0.
	private int algorithm;
	// Description : the map with all blocks. (a. k. a. state space)
	// You can get the location block you want with typing map[x][y].
	private A1063330_checkpoint6_Block[][] map;

	public A1063330_checkpoint6_DemoFrame(int FWidth, int FHeight, String mapID, int jfScaler, int milliseconds,
			int algorithm) throws HeadlessException {
		this.FWidth = FWidth;
		this.FHeight = FHeight;
		this.setTitle("Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FWidth, FHeight);
		this.PressedX = 0;
		this.PressedY = 0;
		this.ReleasedX = 0;
		this.ReleasedY = 0;
		this.jfScaler = jfScaler;
		this.milliseconds = milliseconds;
		this.obstacleImg = new HashMap<>();
		this.typeChar = new HashMap<Integer, String>();
		this.obstacle = new ArrayList<A1063330_checkpoint6_Block>();
		this.obstacleDataStructure = new ArrayList<Integer[]>();
		this.algorithm = algorithm;
		this.querydb = new A1063330_checkpoint6_QueryDB(mapID);

		/*********************************
		 * The Past TODO (Checkpoint4)********************************
		 * 
		 * TODO(Past): You need to get the obstacle from database and transform it into
		 * GUI index version. and set your map(panel) on the frame, we offer a player
		 * object, and you need to draw it on the panel. Hint: In order to build Hashmap
		 * obstacleImg, key means the bar_type from database and value equals the Image
		 * class that load from the corresponding filepath. Hint2: To get the obstacle
		 * set from database, we need you to realize the queryData() in the object
		 * QueryDB in checkpoint2 and get the result. Hint3: obstacle is transformed by
		 * obstacleDataStructure via toGUIIdx() in order to let the location transformed
		 * from database to panel location.(GUI index version) Hint4. For your code
		 * here, you only need to initialize a player and set it into the DemoPanel.
		 * Hint5. We modified the constructor of DemoPanel, so you can easily set player
		 * by it. Hint6. For drawing the player, we had done in the
		 * DemoPanel.paintComponent().
		 *
		 ********************************** The End of the TODO
		 **************************************/

		/*************************************
		 * Updated Description***********************************
		 * 
		 * In order to make code clearly, we make a Object named Block to replace and
		 * implement the all of the location on map (includes obstacles). This object
		 * contatins four variable, and you can see them at the code of Block.java. So,
		 * the obstacle will be changed to ArrayList<Block>;
		 * 
		 * For the TODO here, we modify the funtion toGUIIdx()'s input into
		 * ArrayList<Integer[]>, ArrayList<Block>, HashMap<Integer, Image>. You have to
		 * additionally input obstacleImg into toGUIIdx this time.
		 * 
		 ********************************** The End of the Description
		 *******************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		if (jfScaler == 0) {
			System.exit(0);
		}
		obstacleDataStructure = querydb.getObstacle();
		typeChar = querydb.getObstacleImg();
		for (HashMap.Entry<Integer, String> entry : typeChar.entrySet()) {
			Image bar = new ImageIcon("Resource/" + entry.getValue()).getImage();
			obstacleImg.put(entry.getKey(), bar);
		}
		toGUIIdx(obstacleDataStructure, obstacle, obstacleImg);
		A1063330_checkpoint6_Player player = new A1063330_checkpoint6_Player("player", jfScaler, SwingConstants.CENTER);
		A1063330_checkpoint6_DemoPanel panel = new A1063330_checkpoint6_DemoPanel(player, obstacle, jfScaler);
		add(panel);


		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * create the map (aka state place) via the function createMap().
		 * 
		 * Hint. We implement a function to get the map's height and width in DemoPanel
		 * getXGrids() & getYGrids();
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		createMap(panel.getXGrids(),panel.getYGrids(),obstacle);
		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
		this.addComponentListener(new ComponentAdapter() {
			@Override
			// Description : While resizing the windows, the evnet will be happenned(Reset
			// the location of player).
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				int x = panel.getWidth() / 2 - panel.getCenterX();
				int y = panel.getHeight() / 2 - panel.getCenterY();
				player.setLocation(x + player.getMapX() * panel.getGridLen(),
						y + player.getMapY() * panel.getGridLen());
			}
		});

		player.addMouseListener(new MouseAdapter() {
			@Override
			// Description : the event happenned while player be clicked.
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				/*********************************
				 * The Past TODO (Checkpoint4)********************************
				 * 
				 * TODO(Past): When player is clicked, the moving action needs to be ready for
				 * realize. Therefore, the player must set into the center location of the map.
				 * Hint: While seting the location, you may have to set the center location of
				 * the map as the player's location.
				 * 
				 ********************************** The End of the TODO
				 ***************************************/
				/*******************************************************************************************
				 * START OF YOUR CODE
				 ********************************************************************************************/
				int x,y,nx,ny;
				nx=player.getMapX()*panel.getGridLen();
				ny=player.getMapY()*panel.getGridLen();
				panel.setCenterX(nx);
				panel.setCenterY(ny);
				panel.repaint();
				player.repaint();
				player.setOnClick(true);

				/********************************************************************************************
				 * END OF YOUR CODE
				 ********************************************************************************************/

			}
		});
		/*********************************
		 * The Past TODO (Checkpoint4)********************************
		 * 
		 * TODO(Past): For mouse event here, you should implement map drag here. And you
		 * have to aslo implement the moving action here while the player has been
		 * clicked. Hint: For example, if you click on the top and release in the
		 * bottom, the map should be dragged from up to down. Hint2: You should got both
		 * pressed location and release location and than calculate the moving. Hint3:
		 * While the map dragging, you are not allowed to make the player moving, and
		 * vice versa. The End of the TODO
		 ***************************************/
		panel.addMouseListener(new MouseAdapter() {
			// Description : the event happenned while mouse be pressed.
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				// TODO(Past) While dragging the map, you need to get the location of
				// mousePressed.
				// TODO(Past) If player is clicked(judgement by player.getOnClick()), you don't
				// have to do dragging.
				/********************************************************************************************
				 * START OF YOUR CODE
				 ********************************************************************************************/
				PressedX = e.getX();
				PressedY = e.getY();
				/********************************************************************************************
				 * END OF YOUR CODE
				 ********************************************************************************************/
			}

			// Description : the event happenned while mouse be released
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				/*********************************
				 * The Past TODO (Checkpoint4)********************************
				 * 
				 * TODO(past) While dragging the map, you need to get the location of
				 * mouseReleased. TODO(past) The map displacement will be calculated by Released
				 * location minus Pressed location. TODO(past) And then make the map moving by
				 * controlling it's location variable and repaint the map via repaint() in
				 * object JPanel. TODO(past) If player is clicked(judgement by
				 * player.getOnClick()), you don't have to do dragging. TODO(past) If player is
				 * clicked(judgement by player.getOnClick()), you have to moving the player by
				 * the multithread.
				 * 
				 * Hint1 You can annouce the object PlayerMovement and realize the multithread
				 * here. Hint2 While moving the player(judgement by player.getMoving()), you
				 * can't move the player again.
				 * 
				 ********************************** The End of the TODO
				 ***************************************/

				/*************************************
				 * Updated Description***********************************
				 * 
				 * In order to make code clearly, we make a Object named Block to replace and
				 * implement the all of the location on map (includes obstacles). This object
				 * contatins four variable, and you can see them at the code of Block.java.
				 * 
				 * For the TODO here, we modify the constructor of PlayerMovement input into
				 * (player, ArrayList<Block> DemoPanel, int, int, int, int, Block[][]). You have
				 * to additionally input algroithm and map into the constructor this time.
				 * 
				 ********************************** The End of the Description
				 *******************************/

				/********************************************************************************************
				 * START OF YOUR CODE
				 ********************************************************************************************/
				if (!player.getOnClick()) {
					Integer centerX, centerY, x, y, x2, y2;
					ReleasedX = e.getX();
					ReleasedY = e.getY();
					x = ReleasedX - PressedX;
					y = ReleasedY - PressedY;
					centerX = panel.getCenterX();
					centerY = panel.getCenterY();
					x2 = centerX - x;
					y2 = centerY - y;
					panel.setCenterX(x2);
					panel.setCenterY(y2);
					repaint();
				}
				if (!player.getMoving()) {
					int dx, dy;
					if (player.getOnClick()) {
						dx = e.getX();
						dy = e.getY();
						Thread t = new Thread(
								new A1063330_checkpoint6_PlayerMovement(player, panel, dx, dy, milliseconds,algorithm,map));
						t.start();
					}
				}
				/********************************************************************************************
				 * END OF YOUR CODE
				 ********************************************************************************************/
			}
		});
		panel.addMouseMotionListener(new MouseAdapter() {
			// Description : the event happenned while mouse be dragged.
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				/*********************************
				 * The Past TODO (Checkpoint3)********************************
				 * 
				 * TODO(past)(optional) we hope you can drag the map smoothly, you can override
				 * this function
				 * 
				 ********************************** The End of the TODO
				 ***************************************/

				/********************************************************************************************
				 * START OF YOUR CODE
				 ********************************************************************************************/

				/********************************************************************************************
				 * END OF YOUR CODE
				 ********************************************************************************************/

			}
		});
	}

	// Description : transform the obstacle location from database version to GUI
	// index version
	// data is the database one, and the other.
	public static void toGUIIdx(ArrayList<Integer[]> data, ArrayList<A1063330_checkpoint6_Block> dataGui,
			HashMap<Integer, Image> obstacleImg) {
		for (Integer[] x : data) {
			dataGui.add(new A1063330_checkpoint6_Block(x[1] - 1, x[0] - 1, "obstacle", obstacleImg.get(x[2])));
		}
	}

	// Description : create the map, if each of the loaction will be tag as
	// "obstacle" or "space".
	// If the location is space, it will be without it's own image.(set null)
	public A1063330_checkpoint6_Block[][] createMap(int height, int width,ArrayList<A1063330_checkpoint6_Block> obstacle) {
		A1063330_checkpoint6_Block[][] map = new A1063330_checkpoint6_Block[width][height];
		for (A1063330_checkpoint6_Block block : obstacle) {
			map[block.getX()][block.getY()] = block;
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y] == null) {
					map[x][y] = new A1063330_checkpoint6_Block(x, y, "space", null);
				}
			}
		}
		return map;
	}
}
