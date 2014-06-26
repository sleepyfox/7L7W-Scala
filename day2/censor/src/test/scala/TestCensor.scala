import org.scalatest._
import scala.util.matching.Regex
import scala.io.Source.fromFile 

trait staticWords {
  def bareWordList : List[(String, String)] = {
    List(
      ("Shoot", "Pucky"),
      ("shoot", "pucky"),
      ("Darn",  "Beans"),
      ("darn",  "beans")
    )
  }
}

trait wordsFromFile {
  def bareWordList : List[(String, String)] = {
    val lines = fromFile("words.csv", "utf-8").mkString.split("\n").toList
    lines.map(t => (t.split(",")(0), t.split(",")(1)))
  }
}


trait Censor {
  def bareWordList : List[(String, String)]

  def censorString(string: String) : String = {
    replaceSwearWords(bareWordList, string)
  }

  private def replaceSwearWords(wordList : List[(String, String)], string : String) : String = wordList.size match {
    case 0 => string // guard for empty string case
    // else recurse over all word replacement pairs
    case _ => replaceSwearWords(wordList.tail, doReplace(wordList.head, string))
  }

  private def doReplace(wordPair: (String, String), string: String) : String = {
    // Regex to match a whole word, 
    //   or a string starting or ending with a word
    // Group 1 matches whitespace or word delimiter before word
    // Group 2 matches whitespace or word delimiter after word
    val reg = ("(\\W|^)" + wordPair._1 + "(\\W|$)").r
    reg.replaceAllIn(string, m => m.group(1) + wordPair._2 + m.group(2))
  }
}

class TestBasicCensor extends FunSpec with ShouldMatchers with Censor with staticWords {
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
          censorString("Please don't offshoot") should
            be ("Please don't offshoot")
      }
    }
  }
}

class TestCensorFromFile extends FunSpec with ShouldMatchers with Censor with wordsFromFile {
  describe("A censor") {
    describe("that reads from a file") {
      it("should have a map that replaces Darn with Beanz") {
        val result = bareWordList.filter(_._1 == "Darn")(0)._2
        result should be ("Beanz")
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
          censorString("Please don't offshoot") should
            be ("Please don't offshoot")
      }
    }
  }
}