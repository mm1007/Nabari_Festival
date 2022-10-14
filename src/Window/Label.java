package Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends Component
{

	JLabel label;

	public Label()
	{
		label = new JLabel();
	}

	@Override
	public void setBounds(int x, int y, int w, int h)
	{
		this.label.setBounds(x,
			y,
			w,
			h);
	}

	@Override
	public void setSize(int w, int h)
	{
		this.label.setSize(w,
			h);
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.label.setVisible(visible);
	}

	@Override
	public void setBackground(Color color)
	{
		this.label.setBackground(color);
	}

	public void setText(String text)
	{
		this.label.setText(text);
	}

	@Override
	public void setFont(Font font)
	{
		this.label.setFont(font);
	}

	public void setPreferredSize(int w, int h)
	{
		this.label.setPreferredSize(new Dimension(w, h));
	}

	@Override
	public void setForeground(Color color)
	{
		this.label.setForeground(color);
	}

	public void setLayoutNull()
	{
		this.label.setLayout(null);
	}

}
