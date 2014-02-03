import org.scalatest._

trait Censor {
  def censorString(string: String) : String = {
    val wordList = Map("Shoot" -> "Pucky", "shoot" -> "pucky")
    var myString = string
    wordList.foreach { t =>
      myString = myString.replace(t._1, t._2)
    }
    myString
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

    describe("when the string contains 'Shoot'") {
      it("should replace it with 'Pucky'") {
        censorString("Don't shoot the messenger") should be ("Don't pucky the messenger")
      }

      describe("when it's at the start") {
        it("should replace the first word with 'Pucky'") {
          censorString("Shoot the breeze") should be ("Pucky the breeze")
        }
      }
    }
  }
}
