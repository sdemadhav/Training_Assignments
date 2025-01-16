interface Vehicle 
{
	public static void getVehicle(String v);
}

class Van implements Vehicle(String v)
{
}
class Car implements Vehicle(String v)
{
}
class Truck implements Vehicle(String v)
{
}
class Bus implements Vehicle(String v)
{
}
class Train implements Vehicle(String v)
{
}

class AbstractFactoryDesignPattern
{
	psvm()
	{
		//At runtime we are creating an object of various class bu reusing the same lines of code by just pasing various different arguments
		Vehicle.getVehicle("Train");
		Vehicle.getVehicle("Car");

	}
}

