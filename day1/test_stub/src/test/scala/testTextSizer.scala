// file src/test/scala/testTextSizer.scala
import org.scalatest._

class TestTextSizer extends FunSpec with ShouldMatchers {

  def sizer(x : List[Char]) = {
    0
  }

  describe("A text sizer") {
    it("should give length zero") {
      sizer(List()) should be (0)
    }
  }
}