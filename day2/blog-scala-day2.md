# Scala - day 2
Scala learnings - tips and tricks

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

This was also pretty straightforward and the code can be found [here](https://github.com/sleepyfox/7L7W-Scala/tree/master/day2/censor)

### Load the curse words and alternatives from a file

