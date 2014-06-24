import org.scalatest._
import scala.util.matching.Regex
import scala.io.Source.fromFile 

trait Censor {
  def censorString(string: String) : String = {
    fn(wordList, string)
  }

  private def fn(wordList : Map[Regex, String], string : String) : String = wordList.size match {
    case 0 => string
    case _ => fn(wordList.tail, doReplace(wordList.head, string))
  }

  private def doReplace(replacementRegexTuple: (Regex, String), string: String) : String = {
    replacementRegexTuple._1.replaceAllIn(string, m => {
      m.group(1) + replacementRegexTuple._2 + m.group(2)
    })
  }

  private def wordList : Map[Regex, String] = {
    bareWordList.map( x => {
      (makeMatcher(x._1), x._2)
    })
  }

  private def bareWordList : Map[String, String] = {
    Map(
      "Shoot" -> "Pucky",
      "shoot" -> "pucky",
      "Darn"  -> "Beans",
      "darn"  -> "beans"
    )
  }

  private def makeMatcher(word : String) : Regex = {
    ("(\\W?)" + word + "(\\W|\\z)").r
  }
}

trait CensorFromFile {
    def censorString(string: String) : String = {
    fn(wordList, string)
  }

  private def fn(wordList : Map[Regex, String], string : String) : String = wordList.size match {
    case 0 => string
    case _ => fn(wordList.tail, doReplace(wordList.head, string))
  }

  private def doReplace(replacementRegexTuple: (Regex, String), string: String) : String = {
    replacementRegexTuple._1.replaceAllIn(string, m => {
      m.group(1) + replacementRegexTuple._2 + m.group(2)
    })
  }

  private def wordList : Map[Regex, String] = {
    bareWordList.map( x => {
      (makeMatcher(x._1), x._2)
    })
  }

  private def makeMatcher(word : String) : Regex = {
    ("(\\W?)" + word + "(\\W|\\z)").r
  }

  def bareWordList : Map[String, String] = {
    val lines = fromFile("words.csv", "utf-8").getLines;
    var m : Map[String, String] = Map()
    def splitter(x: String) = {
      val arr = x.split(",") 
      m += arr(0) -> arr(1)
    }
    lines.foreach(x => splitter(x))
    m
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

    describe("when the string contains 'Darn'") {
      it("should replace it with 'Beans'") {
        censorString("Gosh darn it to heck") should be ("Gosh beans it to heck")
      }

      describe("when it's at the start") {
        it("should replace the first word with 'Beans'") {
          censorString("Darn my socks") should be ("Beans my socks")
        }
      }
    }

    describe("when the string contains multiple curse words") {
      it("should replace each of them") {
        censorString("Shoot darn it son of a mother fudge") should
          be("Pucky beans it son of a mother fudge")
      }
    }

    describe("when the string contains curse words embedded in other words") {
      it("should return the original string") {
          censorString("And yet my socks remain undarned") should
            be ("And yet my socks remain undarned")
      }
    }

    describe("when the string ends with a curse word") {
      it("should return the original string") {
          censorString("Please don't shoot") should
            be ("Please don't pucky")
      }
    }
  }
}

class TestCensorFromFile extends FunSpec with ShouldMatchers with CensorFromFile {
  describe("A censor") {
    describe("that reads from a file") {
      it("should have a map that replaces Darn with Beanz") {
        bareWordList("Darn") should be ("Beanz") 
      }
    }

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
      it("should replace it with 'Pucki'") {
        censorString("Don't shoot the messenger") should be ("Don't pucki the messenger")
      }

      describe("when it's at the start") {
        it("should replace the first word with 'Pucki'") {
          censorString("Shoot the breeze") should be ("Pucki the breeze")
        }
      }
    }

    describe("when the string contains 'Darn'") {
      it("should replace it with 'Beanz'") {
        censorString("Gosh darn it to heck") should be ("Gosh beanz it to heck")
      }

      describe("when it's at the start") {
        it("should replace the first word with 'Beanz'") {
          censorString("Darn my socks") should be ("Beanz my socks")
        }
      }
    }

    describe("when the string contains multiple curse words") {
      it("should replace each of them") {
        censorString("Shoot darn it son of a mother fudge") should
          be("Pucki beanz it son of a mother fudge")
      }
    }

    describe("when the string contains curse words embedded in other words") {
      it("should return the original string") {
          censorString("And yet my socks remain undarned") should
            be ("And yet my socks remain undarned")
      }
    }

    describe("when the string ends with a curse word") {
      it("should return the original string") {
          censorString("Please don't shoot") should
            be ("Please don't pucki")
      }
    }
  }
}