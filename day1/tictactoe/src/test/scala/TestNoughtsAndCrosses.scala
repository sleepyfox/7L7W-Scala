import org.scalatest._

class Analyser(board : List[Integer]) {
	def hasWinner() = false
}

class TestNoughtsAndCrosses extends FunSpec with ShouldMatchers {
	describe("A board analyser") {
		describe("when presented with an empty board") {
			it("should not have a winner") {
				val analyser = new Analyser(List(0, 0, 0, 0, 0, 0, 0, 0, 0))
	    		analyser.hasWinner should be (false)
			}
		}
		// describe("when given a board with a single")
	}
}
