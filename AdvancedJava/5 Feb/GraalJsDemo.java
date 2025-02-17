package jsInJava;

import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class GraalJsDemo
{
	public static void main(String [] args)
	{
		try
		{		
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> list = manager.getEngineFactories();
		list.forEach((sef)->System.out.println(sef.getEngineName()));
        // Create a Nashorn ScriptEngine
		//ScriptEngine engine = manager.getEngineByName("Nashorn");
        ScriptEngine engine = manager.getEngineByName("Graal.js");
		engine.eval(new FileReader("demo.js"));
				
		Invocable inv = (Invocable) engine;
		
		inv.invokeFunction("abc");
		inv.invokeFunction("add",1,3);
		System.out.println(inv.invokeFunction("addition",2,5));
		
		String cname = (String)inv.invokeFunction("greet","wissen","Technology");
		System.out.println("Welcome to "+cname);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}