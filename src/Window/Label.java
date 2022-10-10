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

	public void setBounds(int x, int y, int w, int h)
	{
		this.label.setBounds(x, y, w, h);
	}

	public void setVisible(boolean visible)
	{
		this.label.setVisible(visible);
	}

	public void setBackground(Color color)
	{
		this.label.setBackground(color);
	}

	public void setText(String text)
	{
		this.label.setText(text);
	}

	public void setFont(Font font)
	{
		this.label.setFont(font);
	}

	public void setPreferredSize(int w, int h)
	{
		this.label.setPreferredSize(new Dimension(w, h));
	}

	public void setForeground(Color color)
	{
		this.label.setForeground(color);
	}

}
