final class Manager

Sealed class Manager permits SalesManager
{
	int x = 10;
	public void abc()
	{
		System.out.println("HI");
	}
}
class Peon extends Manager //Cant extend manager as its not permitted to
{
}
non-sealed class SalesManager extends Manager //NonSealed means it can be extended by anyone just like a normal class
{
}
non-sealed MarketingManager extends Manager permits DistributionManager
{
}
final class DistributionManager extends MarketingManager //this cant be extended by anyone further as it is a final class
{
}
class GlobalManager extends SalesManager //default class is allowed as a type here as it extends  a non sealed class not a sealed class.
{
}

class MarketingManager extends Manager

public class CantInherit
{	
	public static void main(String args[])
	{
		GlobalManager gm = new GlobalManager();
		gm.abc();
	}
}