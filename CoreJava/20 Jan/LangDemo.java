public class LangDemo
{
	public static void main(String[] args) throws Exception
	{
		Runtime rt = Runtime.getRuntime();
		Process p1 = rt.exec("Calc.exe");
		Thread.sleep(5000);
		p1.destroy();
		System.out.println("CLOSEEEE");

	}
}