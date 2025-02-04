class Person implements java.io.Serializable
{
	int age;
	int salary = 1010101;
	transient String name = "Madhav Jha"; //transient = means ignore this variable for serializing, so this wont be stored in the .ser file at all 
	static String country="Jamaica"; //static variables are not serialized because they are the property of class not of object so the JVM doesn't serialize it
	
	public Person()
	{
		this.name= "Madhav jha";
	}
	
	
}