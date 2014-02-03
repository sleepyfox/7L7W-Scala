import org.scalatest._

trait Censor {
  def censorString(string: String) : String = {
    string
  }
}

class TestCensor extends FunSpec with ShouldMatchers with Censor {
  describe("A censor") {
    describe("when given an empty string") {
      it("should return an empty string") {
        censorString("") should be ("")
      }
    }

    describe("when the string contains no censored words") {
      it("should return the original string") {
        censorString("Bite me!") should be ("Bite me!")
      }
    }
  }
}
