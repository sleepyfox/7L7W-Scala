class Person(firstName: String) {
  println("Outer constructor " + firstName)
  def this(firstName: String, lastName: String) {
    this(firstName)
    println("Inner constructor " + firstName + " " + lastName)
  }
  def talk() = println("Hi")
}
val bob = new Person("Bob")
val bobTate = new Person("Bob", "Tate")
