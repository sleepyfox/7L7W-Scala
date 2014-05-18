import org.scalatest._

package NoughtsAndCrosses {

	class BoardAnalyser(board : List[Int]) {
		def hasWinner() = { 
			if ((board(0) == 1 && board(1) == 1 && board(2) == 1) || 
					(board(3) == 1 && board(4) == 1 && board(5) == 1) ||
					(board(6) == 1 && board(7) == 1 && board(8) == 1))  
				true 
			else 
				false
		}
		def nextPlayer() = {
			val number_of_pieces = board.map(x => if (x > 0) 1 else 0).reduceLeft(_+_)
			if (number_of_pieces % 2 == 0)
				"noughts"
			else
				"crosses"
		}
	}

	class TestBoardAnalyser extends FunSpec with ShouldMatchers {
		describe("A board analyser") {
			describe("when presented with an empty board") {
				it("should not have a winner") {
					val EMPTY_BOARD = List(0, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(EMPTY_BOARD)
		    	analyser.hasWinner should be (false)
				}
				it("the next player should be noughts") {
					val EMPTY_BOARD = List(0, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(EMPTY_BOARD)
		    	analyser.nextPlayer should be ("noughts")
				}
			}
			describe("when given a board with a single cross") {
				it("should not have a winner") {
					val BOARD_WITH_1X = List(1, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_1X)
		    	analyser.hasWinner should be (false)
				}
				it("the next player should be crosses") {
					val BOARD_WITH_1_O = List(2, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_1_O)
		    	analyser.nextPlayer should be ("crosses")
				}
			}
			describe("when given a board with a single nought") {
				it("the next player should be crosses") {
					val BOARD_WITH_1_O = List(2, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_1_O)
		    	analyser.nextPlayer should be ("crosses")
				}
			}
			describe("when given a board with a line of crosses on the top row") {
				it("should have a winner") {
					val BOARD_WITH_TOP_ROW_XS = List(1, 1, 1, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_TOP_ROW_XS)
					analyser.hasWinner should be (true)
				}
			}
			describe("when given a board with a line of crosses on the middle row") {
				it("should have a winner") {
					val BOARD_WITH_MIDDLE_ROW_XS = List(0, 0, 0, 1, 1, 1, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_MIDDLE_ROW_XS)
					analyser.hasWinner should be (true)
				}
			}
			describe("when given a board with a line of crosses on the bottom row") {
				it("should have a winner") {
					val BOARD_WITH_BOTTOM_ROW_XS = List(1, 1, 1, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(BOARD_WITH_BOTTOM_ROW_XS)
					analyser.hasWinner should be (true)
				}
			}
		}
	}
}
