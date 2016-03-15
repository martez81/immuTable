
class PersonTest(val name: String, val lname: String, val age: Int) {
    def this(name: String, age: Int) = this(name, "", 0)

    def this(name: String, lname: String) = {
        this(name, lname, 0)
    }
}

class Person(val firstName: String, val lastName: String, val age: Int) {

    /**
      * A secondary constructor.
      */
    def this(firstName: String) {
        this(firstName, "", 0);
        println("\nNo last name or age given.")
    }

    /**
      * Another secondary constructor.
      */
    def this(firstName: String, lastName: String) {
        this(firstName, lastName, 0);
        println("\nNo age given.")
    }

    override def toString: String = {
        return "%s %s, age %d".format(firstName, lastName, age)
    }

}