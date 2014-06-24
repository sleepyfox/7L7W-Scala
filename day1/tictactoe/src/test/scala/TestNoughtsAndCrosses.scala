import org.scalatest._

package NoughtsAndCrosses {
	class Board() {
	  val NOUGHTS = "Noughts"
	  val CROSSES = "Crosses"
	  val EMPTY = ""
		var places = List("", "", "", "", "", "", "", "", "")
		var nextPlayer = NOUGHTS

		def isValidMove(position : Int) = { 
			if (position < 0 || position > 9) 
				false
			else
				(places(position) == EMPTY)
		}

		def getNextPlayer() = {
			this.nextPlayer
		}

		def move(position : Int) : Boolean = {
			if (this.isValidMove(position)) {
				this.places = this.places.updated(position, this.nextPlayer)
				if (this.nextPlayer == NOUGHTS)
					this.nextPlayer = CROSSES
				else
					this.nextPlayer = NOUGHTS
				true
			}
			else
				false
		}

		def move(ps : List[Int]) : Boolean = {
			ps.map( x => this.move(x) ).foldLeft(false)(_ || _)
		}

		def isTied() = { false }

		def isWin() = { if (this.winner() != "") true else false }

		def winner() = { if (this.places(0) == NOUGHTS &&
			this.places(1) == NOUGHTS &&
			this.places(2) == NOUGHTS) 
				NOUGHTS
			else
				""
		}
	}

	class TestBoard extends FunSpec with ShouldMatchers {
		describe("Given an empty board") {
			var my_board = new Board()
		
			it("A move at position 0 should be valid") {
				my_board.isValidMove(0) should be (true)
			}
			it("A move at position 10 should be invalid") {
				my_board.isValidMove(10) should be (false)				
			}
			it("A move at position -1 should be invalid") {
				my_board.isValidMove(-1) should be (false)				
			}
			it("The next player to move should be Noughts") {
				my_board.getNextPlayer() should equal ("Noughts")
			}
			it("There should not be a winner") {
				my_board.winner() should equal ("")
				my_board.isWin() should equal (false)
			}
			it("the game should not be a tie") {
				my_board.isTied() should be (false)
			}
		}

		describe("Given a board with one Nought at position 0") {
			val my_board = new Board()
			my_board.move(0)

			it("A move at position 1 is valid") {
				my_board.isValidMove(1) should be (true)				
			}
			it("A move at position 0 is invalid") {
				my_board.isValidMove(0) should be (false)		
			}
			it("the next player should be Crosses") {
				my_board.getNextPlayer() should equal ("Crosses")				
			}
			it("There should not be a winner") {
				my_board.winner() should equal ("")
				my_board.isWin() should equal (false)
			}
			it("the game should not be a tie") {
				my_board.isTied() should be (false)
			}
		}

		describe("Given a board O--X-----") {
			val my_board = new Board()
			my_board.move(0 :: 3 :: List()) 

			it("the next player should be Noughts") {
				my_board.getNextPlayer should equal ("Noughts")
			}
			it("a move at position 2 should be valid") {
				my_board.isValidMove(2) should be (true)
			}
			it("a move at position 3 should be invalid") {
				my_board.isValidMove(3) should be (false)
			}
			it("the game should not be a tie") {
				my_board.isTied() should be (false)
			}
		}

		describe("Given a board OOOXX----") {
			val my_board = new Board()
			my_board.move(0 :: 3 :: 1 :: 4 :: 2 :: List())

			it("the game should not be a tie") {
				my_board.isTied() should be (false)
			}
			it("should be a win for Noughts") {
				my_board.isWin() should be (true)
				my_board.winner() should be ("Noughts")
			}
		}
	}
}
