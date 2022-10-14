package Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends Component
{

	public JPanel Panel;

	public Panel()
	{
		this.Panel = new JPanel();
	}

	@Override
	public void setBounds(int x, int y, int w, int h)
	{
		this.Panel.setBounds(x,
			y,
			w,
			h);
	}

	@Override
	public void setSize(int w, int h)
	{
		this.Panel.setSize(w,
			h);
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.Panel.setVisible(visible);
	}

	@Override
	public void setBackground(Color color)
	{
		this.Panel.setBackground(color);
	}

	public void add(Component add, Object contains)
	{
		this.Panel.add(add,
			contains);
	}

	public void setLayoutNull()
	{
		this.Panel.setLayout(null);
	}

	public void setPreferredSize(int w, int h)
	{
		this.Panel.setPreferredSize(new Dimension(w, h));
	}

}
