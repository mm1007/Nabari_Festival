package Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Frame extends Component
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

	@Override
	public void setBounds(int x, int y, int w, int h)
	{
		this.frame.setBounds(x,
			y,
			w,
			h);
	}

	@Override
	public void setSize(int w, int h)
	{
		this.frame.setSize(w,
			h);
	}

	@Override
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

	@Override
	public void setBackground(Color color)
	{
		this.frame.setBackground(color);
	}

	public void add(Component add, Object contains)
	{
		this.frame.getContentPane().add(add,
			contains);
	}

	public void addWOC(Component add)
	{
		this.frame.getContentPane().add(add);
	}

	public void setLayoutNull()
	{
		this.frame.setLayout(null);
	}

	@Override
	public void addKeyListener(KeyListener add)
	{
		this.frame.addKeyListener(add);
	}

	@Override
	public void removeKeyListener(KeyListener remove)
	{
		this.frame.removeKeyListener(remove);
	}

}
