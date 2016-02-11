package customview;

import java.awt.*;  // Notice these dynamic imports
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

/**
 * Created by Arlind on 30-Jan-16.
 */
public class SeekBar extends JComponent
{
    private SeekProgressChangedListener listner;
    private int x;
    private int y;
    private int width;
    private int height;

    private Color progress_color = new Color(252, 250, 240);
    private Color right_bar_color = new Color(102, 215, 255);
    private Color thumb = new Color(252, 250, 240);
    private Color outer_thumb = new Color(252, 250, 240,100);

    private int thumb_radius = 20;
    float progress = 50f;
    float progress_value = 150f;
    float max_progress_value = 300f;

    private boolean mouse_down = false;

    public SeekBar(final int x, int y, final int width, int height)
    {
        this.setLocation(x, y);
        this.setSize(width, height);
        this.x = thumb_radius;
        this.y = y + thumb_radius / 2;
        this.width = width + thumb_radius;
        this.height = height;
        this.max_progress_value = width;
        this.progress_value = width / 50;
        // setBorder(BorderFactory.createTitledBorder(""));

        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                progressSet(e);
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                progressSet(e);
            }
        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                mouse_down = true;
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                mouse_down = false;
                repaint();
            }
        });
    }


    private void progressSet(MouseEvent e)
    {
        if (mouse_down)
        {
            progress = (((float) e.getX() - (float) x) / (float) width) * 100f;
            if (progress < 0)
            {
                progress = 0;
            } else if (progress > 100)
            {
                progress = 100;
            }
            setProgress(progress * max_progress_value / 100);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(right_bar_color);
        graphics.fillRoundRect(x, y + height / 4, this.width, this.height / 2, height, height);

        System.out.println(height);

        graphics.setColor(progress_color);
        graphics.fillRoundRect(x, y, this.width * (int) progress / 100, this.height, height, height);

        if (mouse_down)
        {
            int radius = thumb_radius + thumb_radius / 2;
            graphics.setColor(outer_thumb);
            graphics.fillOval((this.width * (int) progress / 100) + (x - (radius / 2)), y + this.height / 2 - radius / 2, radius, radius);

            graphics.setColor(thumb);
            graphics.fillOval((this.width * (int) progress / 100) + (x - (thumb_radius / 2)), y + this.height / 2 - thumb_radius / 2, thumb_radius, thumb_radius);
        } else
        {
            int radius = thumb_radius - 4;
            graphics.setColor(thumb);
            graphics.fillOval((this.width * (int) progress / 100) + (x - (radius / 2)), y + this.height / 2 - radius / 2, radius, radius);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width + thumb_radius * 2, height + thumb_radius + thumb_radius / 2 + 5);
    }

    public void setMax(int value)
    {
        max_progress_value = value;
    }

    public void setProgress(float value)
    {
        if (value < 0)
        {
            progress_value = 0;
        } else if (value > max_progress_value)
        {
            progress_value = max_progress_value;
        } else
        {
            progress_value = value;
        }

        progress = (progress_value / max_progress_value) * 100f;
        if (listner != null)
        {
            listner.onSeekProgressChangedListener((int) progress_value);
        }
        repaint();
    }

    public void setSeekProgressChangedListener(SeekProgressChangedListener listner)
    {
        this.listner = listner;
    }
}
