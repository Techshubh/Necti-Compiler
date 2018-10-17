
public class bracketpair 
{
	public static void main1(String[] _input1) throws Exception
	{
		int i=-1,x=1;
		char a[]=new char[100];
		for(String s:_input1)
		{
			if(s.contains("{"))
			{
				a[++i]='{';
			}
			else if(s.contains("}"))
			{
				if(i!=-1&&a[i]=='{')
				{
					a[i]='\u0000';
					i--;
				}
				else
				{
					x=-1;
					break;
				}
			}
		}
		if(x==-1)
			throw new Exception("curly bracket not found");
		//return x;
	}
}
