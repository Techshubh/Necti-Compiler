/**
*This application is a type of mini compiler that converted a program into a semi-byte code
*@author Shubham
*@since 3/09/2018
*@version 1.0
*/

import java.util.*;


public class Compiler{
	public static void main(String[] args) {
		if(args.length<0)
			System.out.println("Please give the input file");
		else
		{
		FactoryObject fo=new FactoryObject();
		CompilerMethod cm=fo.getInstance();
		cm.File_Name=args[0];
		cm.Conversion();
		}
		
	}
}