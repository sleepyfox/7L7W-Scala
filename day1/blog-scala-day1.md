# Scala - day 1
Scala learnings - tips and tricks

## Out of memory errors
If you get the error from SBT 'java.lang.OutOfMemoryError: PermGen space' then it can be fixed with adding the following line to your ~/.profile

	$SBT_OPTS="-XX:MaxPermSize=256m"

If you want to use the REPL and you need to load modules into it in order to do stuff then providing you've already imported them in your code (i.e. by providing them as libraryDependencies in SBT) then you can bring them into the REPL by using the following command

	scala -cp target/scala-2.10/classes

## use SBT!
Use SBT's recommended directory structure convention:

	mkdir -p src/main/scala
	mkdir -p src/test/scala

make a Build.sbt with the following:

	libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

This is the minimum necessary in order to bring in the dependencies for scalatest. Optionally also add to the top of the file:

	name := "NoughtsAndCrosses"

	version := "0.0.1"
	
	scalaVersion := "2.10.3"

It is important to put the blank lines in otherwise SBT will barf as it's parser uses blank lines to identify a new lexical token. Weird, but true.

## Scalatest
Use the following template to create the file src/test/scala/testTextSizer.scala

	import org.scalatest._

	class TestTextSizer extends FunSpec with ShouldMatchers {

		describe("A text sizer") {
			it("should give length zero") {
	    		sizer(List()) should be (0)
			}
		}
	}

To save you typing I've published both files as a gist on github [here](http://j.mp/scala-day1)

## Running your tests
You can run 'sbt test' from the command line or enter SBT's interactive mode by entering 'sbt' at the command line and then typing 'test'. If you use the '~ test' command it will watch the test directory and recompile and run tests on changes, which is nice.

## Exercises

* Find the Scala API - [here](http://www.scala-lang.org/api/current/index.html#package) - personally I didn't find this terribly easy to work with, maybe I'm missing something.
* A comparison of Java and Scala - there is a nice explanation of the similarities and differences between Java 8 (particularly lambdas) and Scala [here](http://www.infoq.com/articles/java-8-vs-scala). There's also an interesting comparison of performance done by Google between C++, Go, Java and Scala [here](http://www.theregister.co.uk/2011/06/03/google_paper_on_cplusplus_java_scala_go/)
* A discussion of val vs. var - although there are plenty of simplistic write-ups e.g. [here](https://techvivek.wordpress.com/2009/09/13/scala-difference-between-var-and-val/) I did find a more involved discussion of the pros and cons of mutable vs. immutable structures [here](http://www.scala-lang.org/old/node/5367) - I'm not sure how much of the discussion is made irrelevant by the fact that it is almost 4 years old...
* Write a program that will analyse a noughts-and-crosses board and find if there is a winner, who it is, or if there is a tie or if the game is not over yet.
* Bonus to previous exercise: let two players play noughts-and-crosses

## Noughts and Crosses
You can find the repo with the code that I wrote for this on github [here](https://github.com/sleepyfox/7L7W-Scala/tree/master/day1/tictactoe)
