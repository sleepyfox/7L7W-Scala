# Iterates over the arguments list and prints each in turn

def forLoop {
	println("for loop using Java-style iteration")
	for (i <- 0 until args.length) {
		println(args(i))
	}
}
forLoop