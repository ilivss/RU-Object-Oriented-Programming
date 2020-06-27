package slidingGame;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * A template of a sliding game
 */
public class SlidingGame implements Configuration {

	public static final int N = 3, SIZE = N * N, HOLE = SIZE;
	/**
	 * The board is represented by a 2-dimensional array; the position of the hole
	 * is kept in 2 variables holeX and holeY
	 */
	private int[][] board;
	private int holeX, holeY;
	private int manhattanDist;
	private Configuration predecessor;

	/**
	 * A constructor that initializes the board with the specified array
	 *
	 * @param start: a one dimensional array containing the initial board. The
	 *               elements of start are stored row-wise.
	 */
	public SlidingGame(int[] start) {
		board = new int[N][N];

		assert start.length == N * N : "Length of specified board incorrect";

		for (int p = 0; p < start.length; p++) {
			board[p % N][p / N] = start[p];

			if (start[p] == HOLE) {
				holeX = p % N;
				holeY = p / N;
			}
		}

		// Set manhatten distance
		manhattanDist = calcManhattanDist();

		// Set parent of successor
		predecessor = null;
	}

	public int getManhattanDistance() {
		return manhattanDist;
	}

	/**
	 * Converts a board into a printable representation. The hole is displayed as a
	 * space
	 *
	 * @return the string representation
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int puzzel = board[col][row];
				if (N > 3) {
					buf.append(puzzel == HOLE ? " \t" : puzzel + "\t");
				} else {
					buf.append(puzzel == HOLE ? "  " : puzzel + " ");
				}

			}
			buf.append("\n");
		}
		return buf.toString();
	}

	@Override
	public boolean equals(Object o) {
		SlidingGame sg = (SlidingGame) o;

		// Check if boards are equal:
		for (int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){
				if (this.board[x][y] != sg.board[x][y]){
					return false;
				}
			}
		}

		// Check if holes match
		if (this.holeX != sg.holeX || this.holeY != sg.holeY){
			return false;
		}

		return true;
	}

	@Override
	public boolean isSolution() {
		int previous = 0;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (board[j][i] != ++previous){
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public Collection<Configuration> successors() {
		List<Configuration> out = new LinkedList<>();

		// Generate the list of all possible successors.
		for(Direction dir : Direction.values()){
			SlidingGame successor = copySlidingGame();								// Copy current SlidingGame to successor.

			// Calculate new position for the hole.
			int newHoleX = holeX + dir.getDX(),
			    newHoleY = holeY + dir.getDY();

			// Skip current direction if the hole gets out of bound.
			if (newHoleX < 0 || newHoleX >= N || newHoleY < 0 || newHoleY >= N){
				continue;
			}

			// Editing the board of copied Configuration
			int swapPiece = successor.board[newHoleX][newHoleY];					// Save piece on new hole location.
			successor.board[newHoleX][newHoleY] = successor.board[holeX][holeY]; 	// Put hole on the new location.
			successor.board[holeX][holeY] = swapPiece;								// Put saved piece on old hole location.

			// Set new hole coordinates
			successor.holeX = newHoleX;
			successor.holeY = newHoleY;

			// Set manhatten distance
			successor.setManhattanDist();

			// Set parent of successor
			successor.predecessor = this;

			out.add(successor);
		}

		return out;
	}

	private void setManhattanDist() {
		this.manhattanDist = calcManhattanDist();
	}

	private int calcManhattanDist(){
		int total = 0;

		for (int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){
				if(board[x][y] == HOLE){
					continue;
				} else {
					total += ManhattanDistPiece(board[x][y], x, y);
				}
			}
		}

		return total;
	}

	private int ManhattanDistPiece(int i, int pieceX, int pieceY){
		i--;
		int homeX = i % N;
		int homeY = i / N;

		return Math.abs(pieceX-homeX) + Math.abs(pieceY-homeY);
	}

	@Override
	public int compareTo(Configuration g) {
		SlidingGame sg = (SlidingGame) g;
		return manhattanDist-sg.getManhattanDistance();
	}

	@Override
	public Configuration getParent() {
		return predecessor;
	}

	private SlidingGame copySlidingGame(){
		int[] conf = new int[SIZE];
		int index = 0;

		for (int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){
				conf[index++] = board[x][y];
			}
		}

		return new SlidingGame(conf);
	}

	@Override
	public int hashCode() {
		int hash = 0;

		for(int x = N-1; x >= 0; x--){
			for(int y = N-1; y >= 0; y--){
				hash = 31 * hash + board[x][y];
			}
		}

		return hash;
	}
}
