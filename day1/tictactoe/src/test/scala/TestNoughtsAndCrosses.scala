import org.scalatest._

package NoughtsAndCrosses {

	class BoardAnalyser(board : List[Int]) {
		def hasWinner() = false
	}

	class TestBoardAnalyser extends FunSpec with ShouldMatchers {
		describe("A board analyser") {
			describe("when presented with an empty board") {
				it("should not have a winner") {
					val emptyBoard = List(0, 0, 0, 0, 0, 0, 0, 0, 0)
					val analyser = new BoardAnalyser(emptyBoard)
		    		analyser.hasWinner should be (false)
				}
			}
			// describe("when given a board with a single")
		}
	}
}