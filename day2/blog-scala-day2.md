# Scala - day 2
Scala learnings - tips and tricks

Note to self: functions without parameters don't need parentheses e.g.

	scala> def three = 1 + 2
	three: Int

	scala> three
	res0: Int = 3

Other note to self: beware functions like split that are a hang-over from Java and return an Array (mutable) rather than a List (immutable). In order to translate just use the Array.toList method:

	scala> val myListOfWords = "cat dog mouse".split(" ").toList
	myListOfWords: List[String] = List(cat, dog, mouse)

## Find:
### A discussion on how to use Scala files
This simple blog post has a good example of using the Source library to read from and write to a file [link](http://alvinalexander.com/scala/scala-how-open-read-files-scala-examples) and this stackOverflow question has some good answers, again using Source [link](http://stackoverflow.com/questions/1284423/read-entire-file-in-scala)

### What makes a closure different from a code block?
The simple answer here is that a closure contains the calling environment stack (free variables from outside its own scope) and a code block is just a block of code (with no environment). 

This is nothing Scala related, but comes from my knowledge of other functional languages, I really hope that Scala doesn't define things differently or throw something odd into the mix like Ruby's Procs and Lambdas.

## Do:
### Use foldLeft to compute the total size of a list of strings
This was fairly simple and the code can be found [here](https://github.com/sleepyfox/7L7W-Scala/tree/master/day2/text-sizer)

### Write a Censor trait
With a method that will replace the curse words Shoot and Darn with Pucky and Beans alternatives. Use a map to store the curse words and their alternatives.

This was also pretty straightforward and the code can be found [here](https://github.com/sleepyfox/7L7W-Scala/blob/2126c16af2c1da7a93d10351f6f379a1d4345ad0/day2/censor/src/test/scala/TestCensor.scala) 

The question about 'how do you deal with capitalisation' provoked a great deal of debate in the dojo, and turns out to be a more difficult question than at first glance e.g. how do you deal with SHoot 

### Load the curse words and alternatives from a file
This took a bit of refactoring. First the code to read pairs from the file as a string using scala.io.Source.fromFile: 
  
	def bareWordList : Map[String, String] = {
	  val lines = fromFile("words.csv", "utf-8").getLines
	  var m : Map[String, String] = Map()
	  def splitter(x: String) = {
	    val arr = x.split(",") 
	    m += arr(0) -> arr(1)
	  }
	  lines.foreach(x => splitter(x))
	  m
	}

This felt very *imperative* and not very functional, so some playing around in the REPL gave this:

	def bareWordList : Map[String, String] = {
	  val lines = fromFile("words.csv", "utf-8").mkString.split("\n")
	  lines.map(t => t.split(",")(0) -> t.split(",")(1)).toMap
	}

First read the file into a string and split it into and array of lines, then convert that array into a map by splitting key|value pairs around the comma character. I'm sure there's a better way of doing the split of the key and value and assignment to a map; in Haskell I'd probably do this with a 'where' clause and perhaps some destructuring assignment, but my Scala knowledge doesn't extend that far yet.

I ended up with a Censor trait that has an abstract implementation of bareWordList that is then extended on use with either an implementation that uses a static Map or the version above that reads the data from a file. Code [here](https://github.com/sleepyfox/7L7W-Scala/blob/master/day2/censor/src/test/scala/TestCensor.scala).

