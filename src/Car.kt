open class Car(){
    open fun drive() {
        println("I drive")
    }
}

interface Driver {
    fun drive() {println("Shhh")}
}

class Taxist() : Car(), Driver{
    override fun drive() {
        super<Car>.drive()
    }

}