package Window;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

public class Frame
{

	public JFrame frame;

	public Frame()
	{
		this.frame = new JFrame();
	}

	public void setWindowName(String name)
	{
		this.frame.setTitle(name);
	}

	public void setBounds(int x, int y, int w, int h)
	{
		this.frame.setBounds(x, y, w, h);
	}

	public void setVisible(boolean visible)
	{
		this.frame.setVisible(visible);
	}

	public void setCloseOp(int CloseOp)
	{
		this.frame.setDefaultCloseOperation(CloseOp);
	}

	public void setResizable(boolean Resizeable)
	{
		this.frame.setResizable(Resizeable);
	}

	public void setBackground(Color color)
	{
		this.frame.setBackground(color);
	}

	public void add(Component add, Object contains)
	{
		this.frame.getContentPane().add(add, contains);
	}

	public void setLayoutNull()
	{
		this.frame.setLayout(null);
	}

}
