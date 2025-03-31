package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game ,sim, randomstate;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
		randomstate = TTFEFactory.createSimulator(4, 4, new Random(0));
	}
	
	@Test
	public void testInitialGamecredits() {
		assertEquals("The initial game did not have zero credits", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}

	@Test
	public void testInitialBoardWidth() {
		assertTrue("The initial game board did not have correct Width",
				4 == game.getBoardHeight());
	}
	@Test
	public void checker (){
		assertTrue(randomstate.isSpaceLeft());
	}

	@Test
	public void testInitialMoves(){
		assertTrue("The initial game board did not have correct Number of moves",
				0 == game.getNumMoves());
	}

	@Test 
	public void testInitialPieces(){
		assertTrue("The initial game board did not have correct Number of Pieces",
				2 == game.getNumPieces());
	}

	
	@Test
	public void isnull(){
		assertThrows(IllegalArgumentException.class,()->{
		 game.isMovePossible(null);
		});	
	}
	@Test
	public void addpiece(){
		game.addPiece();
		assertEquals("Add Piece doesnt Work",
		3 == game.getNumPieces());
		//Mid Way
		//int t = randomstate.getNumPieces();
        //randomstate.addPiece();
		//assertEquals("Add Piece doesnt Work",
		//t + 1 == randomstate.getNumPieces());
	}
	   //Grid - Full!!
	@Test
	public void addpiecefullboard(){
  
	  for(int i = 0 ; i < randomstate.getBoardHeight()*randomstate.getBoardWidth() ; i++){
			randomstate.addPiece();
	   } 
	   assertThrows(IllegalStateException.class,()->{
		randomstate.addPiece();
		game.run(null,null);
	   });	
	   assertThrows(IllegalArgumentException.class,()->{
		randomstate.isSpaceLeft();
	   });	
	   assertEquals("Error Somewhere",16 == randomstate.getNumPieces());
	}
	@Test
	public void direction(){
		randomstate.setPieceAt(1, 1, 4);
		randomstate.setPieceAt(2, 1, 4);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		assertTrue(game.isMovePossible(MoveDirection.WEST));
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		assertTrue(game.performMove(MoveDirection.WEST));
		assertTrue(1==game.getNumMoves());
		assertTrue(8==game.getPoints());
		assertTrue(game.isSpaceLeft());
	}
	@Test
	public void south (){
		for (int i = 0; i < game.getBoardWidth(); i++) {
			for(int j = 0;j <game.getBoardHeight();j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(0, 2, 4);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		assertTrue(game.performMove(MoveDirection.SOUTH));
		assertTrue(1==game.getNumMoves());
		assertTrue(8==game.getPoints());
		assertTrue(game.isSpaceLeft());
	}
	@Test
	public void north (){
		for (int i = 0; i < game.getBoardWidth(); i++) {
			for(int j = 0;j <game.getBoardHeight();j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(0, 1, 4);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		assertTrue(game.performMove(MoveDirection.NORTH));
		assertTrue(1==game.getNumMoves());
		assertTrue(8==game.getPoints());
		assertTrue(game.isSpaceLeft());
	}
	@Test
	public void east (){
		for (int i = 0; i < game.getBoardWidth(); i++) {
			for(int j = 0;j <game.getBoardHeight();j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(1, 0, 4);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		assertTrue(game.performMove(MoveDirection.EAST));
		assertTrue(1==game.getNumMoves());
		assertTrue(8==game.getPoints());
		assertTrue(game.isSpaceLeft());
	}
	@Test
	public void west (){
		for (int i = 0; i < game.getBoardWidth(); i++) {
			for(int j = 0;j <game.getBoardHeight();j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(2, 0, 4);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.WEST));
		assertTrue(game.performMove(MoveDirection.WEST));
		assertTrue(1==game.getNumMoves());
		assertTrue(8==game.getPoints());
		assertTrue(game.isSpaceLeft());
	}
    @Test
	public void gridfull(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (j % 2 == 0) {
					game.setPieceAt(i, j, 4);
				} else {
					game.setPieceAt(i, j, 2);
				}
			}
		}
		assertFalse(game.isMovePossible(MoveDirection.NORTH));
		assertFalse(game.isMovePossible(MoveDirection.SOUTH));
		assertFalse(game.isMovePossible(MoveDirection.EAST));
		assertFalse(game.isMovePossible(MoveDirection.WEST));
		assertFalse(game.performMove(MoveDirection.SOUTH));
		assertFalse(game.performMove(MoveDirection.NORTH));
		assertFalse(game.performMove(MoveDirection.EAST));
		assertFalse(game.performMove(MoveDirection.WEST));
		assertFalse(game.isMovePossible());
		assertFalse(game.isMovePossible());
		assertFalse(game.isSpaceLeft());
	}
	
	@Test
	public void values(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = game.getPieceAt(i,j);
				assertTrue("Not CORRECT", x == 2 || x== 0 || x == 4);
			}
		}
	}
	@Test
	public void four(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 4);
				}
		}
		assertThrows(IllegalStateException.class,()->{
			game.addPiece();
		   });

		assertFalse("Not Correct",game.isSpaceLeft());
		assertTrue("Not Correct",game.isMovePossible());
		game.performMove(MoveDirection.NORTH);
		assertTrue("Not Correct",game.isSpaceLeft());
		game.performMove(MoveDirection.SOUTH);
        assertTrue("Not Correct",game.isSpaceLeft());
	}
	@Test
	public void ll (){
      assertThrows(IllegalArgumentException.class,()->{
		game.performMove(null);
	   });
	}
	@Test 
	public void op (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 4);
		game.setPieceAt(0, 3, 4);
		game.performMove(MoveDirection.EAST);
		assertTrue("Not Correct",game.getPieceAt(3,0)==4 && game.getPieceAt(3,1)==4 &&  game.getPieceAt(3,2)==4 && game.getPieceAt(3,3)==4);
		game.performMove(MoveDirection.SOUTH);
		assertTrue("Not Correct",game.getPieceAt(3,3) == 8 && game.getPieceAt(3,2) ==8 );
		game.performMove(MoveDirection.WEST);
	    assertTrue("Not Correct",game.getPieceAt(0,3) == 8 && game.getPieceAt(0,2) ==8 );
		game.performMove(MoveDirection.WEST);
		assertTrue("Not Correct",game.getPieceAt(0,0) == 16 );
	}

	@Test
	public void kk (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 8);
		game.setPieceAt(0, 3, 16);
		assertFalse ("Not Ok",game.isMovePossible(MoveDirection.WEST));
		assertFalse ("Not Ok",game.isMovePossible(MoveDirection.NORTH));
		assertFalse ("Not Ok",game.isMovePossible(MoveDirection.SOUTH));
		assertTrue ("Not Ok",game.isMovePossible(MoveDirection.EAST));

	}





	@Test
	public void kk1 (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(2, 0, 8);
		assertTrue(game.isMovePossible());
		

	}
	@Test
	public void kk2 (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(3, 0, 2);
		game.setPieceAt(3, 1, 4);
		game.setPieceAt(3, 2, 8);
		assertTrue(game.isMovePossible());
		

	}
	@Test
	public void kk3 (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(3, 3, 2);
		game.setPieceAt(2, 3, 4);
		game.setPieceAt(1, 3, 8);
		assertTrue(game.isMovePossible());
		

	}
	@Test
	public void kk4 (){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 4);
		game.setPieceAt(0, 3, 8);
		assertTrue(game.isMovePossible());
		

	}
    @Test
	public void kk5(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				game.setPieceAt(i, j, 0);
				}
		}
        game.setPieceAt(0, 0, 8);
		game.setPieceAt(1, 0, 8);
		game.setPieceAt(2, 0, 0);
        game.setPieceAt(3, 0, 16);
		int x0 = game.getPieceAt(0,0);
		int x1 = game.getPieceAt(1,0);
		int x2 = game.getPieceAt(2,0);
		int x3 = game.getPieceAt(3,0);
		assertTrue("ll",game.isMovePossible(MoveDirection.EAST));
		game.performMove(MoveDirection.WEST);
		game.performMove(MoveDirection.EAST);
		assertTrue("ll",x0==8);
		assertTrue("ll",x1==8);
		assertTrue("ll",x2==0);
		assertTrue("ll",x3!=0);
	}
	


  
}
	

	

