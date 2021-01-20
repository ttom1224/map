import java.util.*;

/***********************************
 * Hint ********************************************
 * 
 * To finish the object, we define some variables in this code.
 * 
 * 1. Absolute Location --> The GUI location in Swing definition. 2. Grid
 * Location (GUI version) --> We divided the map into 16*16 grids, use this
 * variable to record the certain location of a grid. 3. Delta X/Y --> The x,y
 * location offsets of the map in the panel. We calculate these two scalars by
 * substracting the center point of the map (width/2,height/2) and the current
 * panel center point (CenterX, CenterY) in panel. 4. Relative Location --> The
 * x,y location for painting the objects (e.g. obstacle and players) on the
 * panel. We calculate this using Delta X/Y plus the grid location mutiply the
 * length of the grid (Absolute Coordinate).
 * 
 ********************************** The End of the Hint
 ***************************************/

public class A1063330_checkpoint6_PlayerMovement implements Runnable {
	// Description : the player object.
	private A1063330_checkpoint6_Player player;
	// Description : the panel object.
	private A1063330_checkpoint6_DemoPanel panel;
	// Description : while moving the player, it needs to move by.(in Grid Location)
	private A1063330_checkpoint6_RouteLinkedList route;
	// Description : the X absolute location the destination is on.
	private int newPointX;
	// Description : the Y absolute location the destination is on.
	private int newPointY;
	// Description : the time that the player moving in per step
	private int milliseconds;
	// Description : the path that the player has walked through.
	private ArrayList<A1063330_checkpoint6_Block> walkedPath;
	// Description : BFS : 1, DFS : 0
	private int algorithm;
	// Description : the map with all blocks. (a. k. a. state space)
	// You can get the location block you want with typing map[x][y].
	private A1063330_checkpoint6_Block[][] map;
	// Description : record the block and it's ParentBlock.
	private HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block> ParentBlock;
	// Description : record the block that has be visited.
	private boolean[][] visited;
	// Description : the player current location ( the root of the tree).
	private A1063330_checkpoint6_Block root;
	// Description : if algorithm equals 0 means using DFS.
	private final int DFS = 0;
	// Description : if algorithm equals 1 means using BFS.
	private final int BFS = 1;

	public A1063330_checkpoint6_PlayerMovement(A1063330_checkpoint6_Player player, A1063330_checkpoint6_DemoPanel panel,
			int newPointX, int newPointY, int milliseconds, int algorithm, A1063330_checkpoint6_Block[][] map) {
		this.player = player;
		this.panel = panel;
		this.newPointX = newPointX;
		this.newPointY = newPointY;
		this.milliseconds = milliseconds;
		this.route = new A1063330_checkpoint6_RouteLinkedList();
		this.walkedPath = new ArrayList<A1063330_checkpoint6_Block>();
		this.algorithm = algorithm;
		this.map = map;
		this.ParentBlock = new HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block>();
		this.visited = new boolean[panel.getXGrids()][panel.getYGrids()];
		this.root = map[player.getMapX()][player.getMapY()];
		for (int x = 0; x < panel.getXGrids(); x++) {
			for (int y = 0; y < panel.getYGrids(); y++) {
				visited[x][y] = false;
			}
		}
	}

	// Description : The Thread object running function(The thing that this thread
	// doing ).
	@Override
	public void run() {

		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(1.1): This time you have need to calculate the target location (Grid
		 * verb.) first and get the target block. TODO(1.2): After you find the target
		 * block, you have to set target in panel via panel.setTarget() and repaint it
		 * in order to display which block you clicked. TODO(1.3): generate the route
		 * via detectWay(). TODO(1.4): follow the route via followRoute(); TODO(1.5):
		 * After following the route, you have to update the player's
		 * status.(player.setOnClick(), player.setMoving()) and clear the path that the
		 * player has walked through via panel.setPath() and repaint the panel.
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		player.setMoving(true);
		int x, y, nx, ny, d, mx, my, rx, ry, dx, dy;
		mx = panel.getWidth() / 2 - panel.getCenterX();
		my = panel.getHeight() / 2 - panel.getCenterY();
		nx = (newPointX - mx) / panel.getGridLen();
		ny = (newPointY - my) / panel.getGridLen();
		A1063330_checkpoint6_Block tar = new A1063330_checkpoint6_Block(nx, ny, "target", null);
		panel.setTarget(tar);
		x = player.getMapX();
		y = player.getMapY();
		detectWay(root, tar, ParentBlock, visited);
		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	private A1063330_checkpoint6_RouteLinkedList detectWay(A1063330_checkpoint6_Block root,
			A1063330_checkpoint6_Block target,
			HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block> ParentBlock, boolean[][] visited) {
		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(2): For the TODO here, you have to Search the target block via the
		 * function Search() and back trace and generate the route by the function
		 * createRoute();
		 * 
		 * Hint(1): BFS algorithm needs to use queue to work, so we make a object named
		 * BlockQueue for BFS. Hint(2): DFS algorithm needs to use stack to work, so we
		 * make a object named BlockStack for DFS. Hint(3): These two object are all
		 * implemnt the fringe, the detail description can be found in the code of
		 * Fringe.java BlockQueue.java BlockStack.java. Hint(4): Before send the fringe
		 * into the function Search(), you have to add the root (the player current
		 * location) into fringe.
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		A1063330_checkpoint6_BlockQueue q = new A1063330_checkpoint6_BlockQueue();
		A1063330_checkpoint6_BlockStack s = new A1063330_checkpoint6_BlockStack();
		if (algorithm == 0) {
			s.add(root);
			Search(s, target, ParentBlock, visited);
		} else if (algorithm == 1) {
			q.add(root);
			Search(q, target, ParentBlock, visited);
		}

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	public A1063330_checkpoint6_Block Search(A1063330_checkpoint6_Fringe fringe, A1063330_checkpoint6_Block target,
			HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block> ParentBlock, boolean[][] visited) {
		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(3.1): For the TODO here, you have to implement the searching funciton;
		 * TODO(3.2): You MUST print the message of "Searching at (x, y)" in order to
		 * let us check if you sucessfully do it. TODO(3.3): After you find the target,
		 * you just need to return the target block. //System.out.println("Searching at
		 * ("+Integer.toString(YOURBLOCK.getX())+",
		 * "+Integer.toString(YOURBLOCK.getY())+")");
		 * 
		 * Hint1: Page 44 in the teaching material "lecture05" may be your reference.
		 * Hint2: if the target can not be search you should return null(that means
		 * failure).
		 * 
		 * pseudo code is provided here: 1. get the block from fringe. 2. print the
		 * message 3. if that block equals target return it. 4. if not, expand the block
		 * and insert then into fringe. 5. return to 1. until the fringe does not have
		 * anyting in it.
		 * 
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		while (!fringe.isEmpty()) {

		}

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	private ArrayList<A1063330_checkpoint6_Block> expand(A1063330_checkpoint6_Block currentBlock,
			HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block> ParentBlock, boolean[][] visited) {
		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(4.1): For the TODO here, you have to implement the expand funciton and
		 * return the Blocks(successor); TODO(4.2): the order that you expand is
		 * North(Up) West(Left) South(Down) East(Right). TODO(4.3): before adding the
		 * block into succesoor, you have to check if it is valid by checkBlock().
		 * 
		 * 
		 * Hint1: Page 44 in the teaching material "lecture05" may be your reference.
		 * Hint2: While the block is valid, before you add the block into successor, you
		 * should set its ParentBlock (We prepare a HashMap to implement this). And you
		 * should also set it is visited. (We prepare 2D boolean array for you) (the
		 * (x,y) block <--> visited[x][y] ) The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	private Boolean checkBlock(int newLocationX, int newLocationY, boolean[][] visited) {
		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(5.1): For the TODO here, you have to implement the checkBlock funciton
		 * and return if the Block is valid or not; TODO(5.2): First you have to check
		 * the new block is in your map or not. TODO(5.3): Second the block can not be
		 * obstacle. TODO(5.4): Third the block should not be visited.
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		if (newLocationX > panel.getXGrids() || newLocationY > panel.getYGrids()) {
			return false;
		} else if (map[newLocationX][newLocationY]==) {
			return false;
		}
		else if(visited[newLocationX][newLocationY]==false) {
			
		}
		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	private A1063330_checkpoint6_RouteLinkedList createRoute(
			HashMap<A1063330_checkpoint6_Block, A1063330_checkpoint6_Block> ParentBlock,
			A1063330_checkpoint6_Block target) {
		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(6): For the TODO here, you have to trace back the route and return the
		 * route;
		 * 
		 * Hint1: You can get the parent block of target by HashMap ParentBlock, thus
		 * you can calculate the last step of the route. And then you get the parent
		 * block of the parent block of target, you can calculate the backward step and
		 * so on.
		 * 
		 * presudo code is provided here: 1. get parent block 2. calculate the delta
		 * location 3. insert into head 4. make the target equals its parent block and
		 * so on.
		 * 
		 ********************************** The End of the TODO
		 **************************************/

		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	private void followRoute(A1063330_checkpoint6_RouteLinkedList route) {

		/**********************************
		 * The TODO This Time (Checkpoint6)**************************
		 * 
		 * TODO(7): This time in order to make moving more easily to trace and debug. We
		 * make a ArrayList walkedPath to record the block that the player had walked
		 * through. You have to add the block into walkedPath and set it into panel and
		 * then the panel will draw it automatically.
		 * 
		 * pseudo code provide here loop the route untill it does not have next node. #
		 * you have done this last time. movePlayerOneGrid() (base on the value of route
		 * node). # you have done this last time. add block that the player walk through
		 * into walkedPath. # This time you have to implement it. set the walkedPath in
		 * panel by panel.setPath().# This time you have to implement it. update the
		 * player's location # you have done this last time. checkCollided() # you have
		 * done this last time.
		 * 
		 ********************************** The End of the TODO
		 **************************************/
		/********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	// Description : Check the new point(Grid location) is collided or not.
	private void checkCollided(Integer newPointX, Integer newPointY) {
		if (map[newPointX][newPointY].getType() == "obstacle") {
			A1063330_checkpoint6_BugFrame bugFrame = new A1063330_checkpoint6_BugFrame();
			bugFrame.setVisible(true);
		}
		if (newPointX < 0 || newPointY < 0 || newPointY * this.panel.getGridLen() >= this.panel.getMapHeight()
				|| newPointX * this.panel.getGridLen() >= this.panel.getMapWidth()) {
			A1063330_checkpoint6_BugFrame bugFrame = new A1063330_checkpoint6_BugFrame();
			bugFrame.setVisible(true);
		}
	}

	// Description : moving the player one gird location via this function.
	private void movePlayerOneGrid(A1063330_checkpoint6_DemoPanel panel, A1063330_checkpoint6_Player player,
			int direction, int axis, int milliseconds) {

		for (int i = 1; i <= player.getStepTime(); i++) {
			movePlayerOneStep(panel, player, direction, axis, milliseconds);
		}
	}

	private void movePlayerOneStep(A1063330_checkpoint6_DemoPanel panel, A1063330_checkpoint6_Player player,
			int direction, int axis, int milliseconds) {
		/*********************************
		 * The Past TODO (Checkpoint4)********************************
		 * 
		 * TODO(Past) In this function you have to make your player moving one step
		 * here. You need to continue setting the player or panel location, but with
		 * stopping a little time in order to make the player moving continusly. We have
		 * already prepared the continus function(movePlayerOneGrid()), but you have to
		 * realize the stopping time and setting the player or panel location here There
		 * is a requirement that the player needs to keep being in the center point of
		 * the windows. So maybe not only player you have to consider, but also panel
		 * you need to do too.
		 * 
		 * Hint pseudo code provide here doNothing() if axis = 1 -> move in y else x
		 * panel.setCenter( new Center + player.getPerStep()) # Note the direction
		 * panel.repaint()¡Bplayer.repaint()
		 * 
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

	// Description : Stop the thread in milliseconds.
	private static void doNothing(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.out.println("Unexpected interruption");
			System.exit(0);
		}
	}

}
