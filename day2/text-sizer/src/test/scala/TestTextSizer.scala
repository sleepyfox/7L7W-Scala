import org.scalatest._

class TestTextSizer extends FunSpec with ShouldMatchers {

  def sizer(text : List[String]) : Integer = {
    text.foldLeft(0)( (totalLength, string) =>
      totalLength + string.length
    )
  }

  describe("A text sizer") {
    describe("when given an empty list") {
      it("should give length zero") {
        sizer(List()) should be (0)
      }
    }

    describe("when given a list with an empty string") {
      it("should give length zero") {
        sizer(List("")) should be (0)
      }
    }

    describe("when given a list with a single string 'Orange'") {
      it("should give length 6") {
        sizer(List("Orange")) should be (6)
      }
    }

    describe("when given a list with two strings 'Oranges' and 'Lemons'") {
      it("should give length 13") {
        sizer(List("Oranges", "Lemons")) should be (13)
      }
    }
  }
}
