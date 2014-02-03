import org.scalatest._

trait Censor {
  def censorString(string: String) : String = {
    ""
  }
}

class TestCensor extends FunSpec with ShouldMatchers with Censor {
  describe("A censor") {
    describe("when given an empty string") {
      it("should return an empty string") {
        censorString("") should be ("")
      }
    }
  }
}
