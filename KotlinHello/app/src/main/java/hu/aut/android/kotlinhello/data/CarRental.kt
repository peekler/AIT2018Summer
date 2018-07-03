package hu.aut.android.kotlinhello.data

class CarRental(val name: String) {
    private val cars = mutableListOf<Car>()

    fun addCar(car: Car) {
        cars += car
    }

    fun kotlinTotalPrice() : Int{
        return cars.sumBy { it.price }
    }

    fun totalPrice() : Int {
        var sum = 0

        for (car in cars) {
            sum += car.price
        }

        return sum
    }
}

open class Car(var type: String, var price: Int) {

}

class ElectricCar(type: String, price: Int, var range: Int) : Car(type, price) {

}