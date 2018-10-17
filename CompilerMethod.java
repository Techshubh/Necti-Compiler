import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CompilerMethod implements Comp
{	public static String File_Name="";
	public void Conversion()
	{
		 HashMap<String, String> map = new HashMap<>(); 
		 int cint=0;
		 int cfloat=0;
		 int cObject=0;
		 String n="";
		 String n1="";
		 boolean check=false;

		List<String> list = new ArrayList<String>();
		String[] s1={}; 
		
		//String[] stringArr = list.toArray(new String[0]);
		try 
		{
      	Scanner sc = new Scanner(new File(File_Name));
      	while (sc.hasNext()) {
        String str = sc.nextLine();
        list.add(str);
      	}

      	s1= list.toArray(new String[0]);
      	sc.close();
    	
    	} 


    	catch (IOException e) 
    	{
      	// TODO Auto-generated catch block
      	e.printStackTrace();
    	}


	
		
		String s2=new String();
		int k=0;
		try{
		bracketpair.main1(s1);
		
		for(String sg:s1)
		{	check=false;
			if(sg.contains("="))
			{
				String[] s=sg.split("[=;]");
				if(map.containsKey(s[0])&& map.containsKey(s[1]))
				{	if(map.get(s[0]).contains("i"))
					{s2+="iload "+map.get(s[0])+" "+map.get(s[1])+"\n";
					check=true;}
					else if(map.get(s[0]).contains("f"))
					{
						s2+="fload "+map.get(s[0])+" "+map.get(s[1])+"\n";
					check=true;
					}

				}
				else if(map.containsKey(s[0]))
				{	if(map.get(s[0]).contains("i"))
					{s2+="iload "+map.get(s[0])+" "+s[1]+"\n";
					check=true;}
					else if(map.get(s[0]).contains("f"))
					{
						s2+="fload "+map.get(s[0])+" "+s[1]+"\n";
					check=true;
					}
					else if(map.get(s[0]).contains("A"))
					{
						s2+=""+map.get(s[0])+" "+s[1]+"\n";
					check=true;
					}

				}
			
			}
			if(sg.contains("int "))
			{
				n="i_store";
				n1="";
				//load and store
				String[] l=sg.split(" ");
				String[] lnew=l[1].split("[,;]");
				for(String lit:lnew)
				{
					if(lit.contains("="))
					{cint++;
					String[] eq=lit.split("=");
					n+=" i"+cint;
					if(map.containsKey(eq[1]))
					n1+="iload"+" "+"i"+cint+" "+map.get(eq[1])+";"+"\n";
					else
						n1+="iload"+" "+"i"+cint+" "+eq[1]+";"+"\n";
					map.put(eq[0],"i"+cint);
					


				}
				else
				{
					cint++;
					n+=" i"+""+cint;
					map.put(lit,"i"+cint);
				}
			


				}
				n+=";\n"+n1;	
				s2+=n;
				check=true;
				

				
			}
			else if(sg.contains("float"))
			{	//check float
				n="f_store";
				n1="";
				//load and store
				String[] l=sg.split(" ");
				String[] lnew=l[1].split("[,;]");
				for(String lit:lnew)
				{
					if(lit.contains("="))
					{cfloat++;
					String[] eq=lit.split("=");
					//System.out.println(eq[0]);
					//System.out.println(eq[1]);
					n+=" f"+cfloat;
					if(map.containsKey(eq[1]))
					n1+="fload"+" "+"f"+cfloat+" "+map.get(eq[1])+";"+"\n";
					else
						n1+="fload"+" "+"f"+cfloat+" "+eq[1]+";"+"\n";
					map.put(eq[0],"f"+cfloat);
					


				}
				else
				{
					cfloat++;
					n+=" f"+""+cfloat;
					map.put(lit,"f"+cfloat);
				}
			


				}
				n+=";\n"+n1;	
				s2+=n;
				check=true;
				
				//s2+=sg.replaceAll("float ","f_store ")+"\n";
				//check=true;
			}
			else if(sg.contains("new"))
			{	cObject++;
				s2+="Invoke_special:";
				s2+="A"+cObject+"_store;\n";
				check=true;
			}
			else if(sg.contains(");"))
			{
				s2+="Invoke_special:"+sg+"\n";			
				check=true;
			}
			else if(sg.contains("String")&&!sg.contains("main"))
			{	
				cObject++;
				n="A"+cObject+"_store";
				n1="";
				//load and store
				String[] l=sg.split(" ");
				String[] lnew=l[1].split("[,;]");
				for(String lit:lnew)
				{
					if(lit.contains("="))
					{
					String[] eq=lit.split("=");
					//System.out.println(eq[0]);
					//System.out.println(eq[1]);
					
					if(map.containsKey(eq[1]))
					n1+="A"+cObject+"load"+" "+map.get(eq[1])+";"+"\n";
					else
						n1+="A"+cObject+"load"+" "+eq[1]+";"+"\n";
					map.put(eq[0],"A"+cObject+"load");
					


				}
				else 
				{
					
					n+="";
					map.put(lit,"A"+cObject+"load");
				}
			


				}
				n+=";\n"+n1;	
				s2+=n;
				check=true;
				//s2+=sg.replaceAll("String","A_store")+"\n";
				//check=true;
			}
			else if(!sg.contains("class")&&!sg.contains("for")&&!sg.contains("while")&&!sg.contains("do"))
			{
				if(sg.contains("){"))
					{
						s2+="Function: "+sg+"\n";
						if(sg.contains("String "))
							s2+=sg.replaceAll("String","A_store");
							check=true;
					}

				else if(!check)
					{s2+=sg+"\n";
						check=true;}
			}
			else if(!check)
				{
						s2+=sg+"\n";
				}

			
			
			}
			System.out.println(map);
	
		//calling for Line count
		LineCount(s2);
		
		}



		
		catch(Exception e)
		{

		System.out.println(e.getMessage());
		
		}
	}
		

	public static void LineCount(String input)
	{	
		String s1[]=input.split("\n");
		String s2=new String();
		int i=0;
		boolean flag=false;
	
		for(String sg:s1)
		{	
			if(!sg.contains("for")&&!sg.contains("while")&&!sg.contains("do"))
			{	
				if(sg.contains("){")&& !flag)
					{	i=0;
						s2+=sg+"\n";
						flag=true;
					}
					else if(flag&&!sg.contains("}"))	
					{		
						s2+=i+" "+sg+"\n";
						i++;
					}
					else if(flag&&sg.contains("}"))
					{
						s2+=i+" "+sg+"\n";
						flag=false;
						i++;
					}
					else
						s2+=sg+"\n";
				
			}
			else
			{
				s2+=sg+"\n";
			}
		}
	
		//System.out.println(s2);
		write_file(s2);

	}
	public static void write_file(String Cfile)
	{	
		String File_wext=File_Name.split("\\.")[0];
		 File file = new File(File_wext+".class");
		 System.out.println("wrting");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(Cfile);

        } 
        catch (IOException e)
         {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}