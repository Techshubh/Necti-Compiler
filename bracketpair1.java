
public class bracketpair1 
{
	public static void main(String[] args) throws Exception
	{
		int i=-1,x=1;
		String[] _input1={"{","}","}"};
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
			throw new Exception("bad");
		//return x;
	}
}
