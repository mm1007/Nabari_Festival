package Main;

import java.util.ArrayList;

public class Array<T>
{

	public ArrayList<T> List;

	public Array()
	{
		List = new ArrayList<T>();
	}

	public void add(T add)
	{
		List.add(add);
	}

	public void remove(int index)
	{
		List.remove(index);
	}

}
