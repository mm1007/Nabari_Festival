package Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class ScrollPane
{

	JScrollPane ScrollPane;

	public ScrollPane()
	{
		this.ScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

	public void setBounds(int x, int y, int w, int h)
	{
		this.ScrollPane.setBounds(x, y, w, h);
	}

	public void setViewport(Component Viewport)
	{
		this.ScrollPane.setViewportView(Viewport);
	}

	public void setBackground(Color color)
	{
		this.ScrollPane.getViewport().setBackground(color);
	}

	public void setPreferredSize(int w, int h)
	{
		this.ScrollPane.setPreferredSize(new Dimension(w, h));
	}

	public void setValueVertical(int scroll)
	{
		this.ScrollPane.getVerticalScrollBar().setValue(scroll);
	}

	public void setValueHorizontal(int scroll)
	{
		this.ScrollPane.getHorizontalScrollBar().setValue(scroll);
	}

}
