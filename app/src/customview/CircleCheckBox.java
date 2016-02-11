package customview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arlind on 10-Feb-16.
 */
public class CircleCheckBox extends JComponent
{
    private float innerCircleRadius = 30.f;
    private float outerCircleRadius = innerCircleRadius / 2f;
    private float textSize = 35f;
    private float borderThickness = 5f;
    private float tickThickness = 2f;
    private float textLeftPadding = 2f;

    private float increment = 20;
    private float total_time = 200;
    //    private final Paint mPaintPageFill = new Paint(ANTI_ALIAS_FLAG);
//    private final Paint mPaintPageStroke = new Paint(ANTI_ALIAS_FLAG);
//    private final Paint mPaintTick = new Paint(ANTI_ALIAS_FLAG);
//    private final Paint mPaintOuter = new Paint(ANTI_ALIAS_FLAG);
//    private final Paint mPaintText = new Paint(ANTI_ALIAS_FLAG);
    private boolean firstRun = true;
    private boolean timer_running = false;
    private float tick_third_ = innerCircleRadius / 3;
    private boolean draw_tick_part_one = false;
    private String text = " HELLOO MEE";

    private Color tick_color = new Color(255, 255, 255, 255);
    private Color text_color = new Color(0, 0, 0, 255);
    private Color outerCircleColor = new Color(0, 207, 173, 100);
    private Color innerCircleColor = new Color(0, 207, 173, 255);
    private Color circleBorderColor = new Color(0, 207, 173, 255);

    private OnCheckedChangeListener listener;

    private boolean showOuterCircle = true;

    float centerX = 0;
    float centerY = 0;
    private boolean isChecked = false;

    private boolean draw_tick = false;

    Timer timer = null;

    public CircleCheckBox(final int x, int y, final int width, int height)
    {
        this.setLocation(x, y);
        this.setSize(width, height);
        init();
    }


    public void init()
    {
//        mPaintOuter.setColor(outerCircleColor);
//        mPaintPageFill.setColor(innerCircleColor);
//        mPaintTick.setColor(tick_color);
//        mPaintTick.setStrokeWidth(tickThickness * 2);
//
//        mPaintPageStroke.setColor(circleBorderColor);
//        mPaintPageStroke.setStrokeWidth(borderThickness);
//        mPaintPageStroke.setStyle(Paint.Style.STROKE);
//
//        mPaintText.setTextSize(textSize);
//        mPaintText.setColor(text_color);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                setChecked(!isChecked);
                System.out.println("CHECKED " + isChecked);
            }
        });
    }

    private float current_radius = 0.0f;
    float time = 0;

    float tick_x = 0;
    float tick_y = 0;
    float tick_x_two = 0;
    float tick_y_two = 0;
    float inc_tick = 0;
    // Interpolator interpolator = new BounceInterpolator();

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (centerX == 0)
        {
            centerX = innerCircleRadius + outerCircleRadius; //+ getPaddingLeft();
        }
        centerY = getHeight() / 2;

        float inc = innerCircleRadius / (total_time / increment);

        float time_inc = 1 / (total_time / increment);
        time += time_inc;

        graphics.setColor(innerCircleColor);
        graphics.setStroke(new BasicStroke(borderThickness));

        drawCircle(graphics, (int) (centerX), (int) centerY, (int) innerCircleRadius, true);

        if (isChecked)
        {
            if (draw_tick)
            {
                float tick_offset = tickThickness * 2;
                if (showOuterCircle)
                {
                   graphics.setColor(outerCircleColor);

                    drawCircle(graphics, (centerX), centerY, (current_radius + outerCircleRadius), false);
                }
                graphics.setColor(innerCircleColor);
                drawCircle(graphics, centerX, centerY, innerCircleRadius, false);
                if (draw_tick_part_one)
                {
                    inc_tick = tick_third_ / (total_time / increment);

                    tick_x += inc_tick;
                    tick_y += inc_tick;
                    graphics.setColor(tick_color);
                   int x_start = (int) (centerX - tick_offset - tick_third_);
                    graphics.setStroke(new BasicStroke(tickThickness));

                    graphics.drawLine(x_start, (int) centerY, (int) (tick_x - tick_offset), (int) tick_y);
                    System.out.println("X1 " + x_start + ",Y1 " + centerY + ",X2 " + (int) ( tick_offset) + ",Y2 " + (int) ( tick_y));


                } else
                {
                    graphics.setColor(tick_color);
                    int x_start = (int) (centerX - tick_offset - tick_third_);

                    graphics.setStroke(new BasicStroke(tickThickness));
                    graphics.drawLine(x_start, (int) centerY, (int) (tick_x - tick_offset), (int) ( tick_y));

                    inc_tick = tick_third_ * 1.7f / (total_time / increment);

                    tick_x_two += inc_tick;
                    tick_y_two -= inc_tick;

                    graphics.drawLine((int) (centerX - tick_offset), (int) (tick_y), (int) (+ tick_x_two - tick_offset), (int) (tick_y_two));

                 }

            } else
            {
                current_radius = current_radius + inc;

                if (showOuterCircle && current_radius >= innerCircleRadius - outerCircleRadius)
                {
                    graphics.setColor(outerCircleColor);
                    drawCircle(graphics, centerX, centerY, (current_radius + outerCircleRadius), false);
                }
                graphics.setColor(innerCircleColor);
                drawCircle(graphics, centerX, centerY, (current_radius), false);
            }
        } else
        {
            if (!firstRun)
            {
                current_radius = current_radius - inc;

                graphics.setColor(innerCircleColor);
                drawCircle(graphics, (centerX), (centerY), (current_radius), false);
            }
        }

        if (isChecked)
        {
            if (!timer_running)
            {
                tick_x = centerX;// tick_third_;
                tick_y = centerY + tick_third_;
                float tick_offset = tickThickness * 2;

                graphics.setColor(tick_color);
                drawCircle(graphics, (centerX - tick_offset - tick_third_), centerY, tickThickness, false);
                graphics.drawLine((int) (centerX - tick_offset - tick_third_), (int) centerY, (int) (tick_x - tick_offset + centerX - tick_offset - tick_third_), (int) (centerY + tick_y));
                drawCircle(graphics, (tick_x - tick_offset), tick_y, tickThickness, true);

                tick_x_two = tick_x + (tick_third_ * 1.7f);
                tick_y_two = tick_y - (tick_third_ * 1.7f);
                //canvas.drawLine(centerX - tick_offset, tick_y, tick_x_two - tick_offset, tick_y_two, mPaintTick);
                graphics.drawLine((int) (centerX - tick_offset), (int) tick_y, (int) (centerX - tick_offset + tick_x_two - tick_offset), (int) (tick_y + tick_y_two));
                // canvas.drawCircle(tick_x_two - tick_offset, tick_y_two, tickThickness, mPaintTick);
                //graphics.fillOval((int) (tick_x_two - tick_offset), (int) tick_y_two, (int) tickThickness, (int) tickThickness);
                drawCircle(graphics, (tick_x_two - tick_offset), tick_y_two, tickThickness, false);
                tick_x = 0;
                tick_y = 0;
                tick_x_two = 0;
                tick_x_two = 0;

                current_radius = innerCircleRadius;
            }
        }

        // canvas.drawText(text, centerX + textLeftPadding + innerCircleRadius + outerCircleRadius, centerY + textSize / 2, mPaintText);

        graphics.setColor(text_color);
        graphics.drawString(text, (int) (centerX + textLeftPadding + innerCircleRadius + outerCircleRadius), (int) (centerY + textSize / 2));

        firstRun = false;
    }

    private void startAnimationTimer()
    {
        timer = new Timer((int) increment, new ActionListener()
        {
            int time = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                timer_running = true;
                time += increment;
                if (time <= total_time)
                {
                    repaint();
                    // postInvalidate();
                } else
                {
                    if (isChecked)
                    {
                        timer.stop();
                        startTickAnimation();
                    } else
                    {
                        timer_running = false;
                        timer.stop();
                    }

                }
            }
        });
        timer.start();
    }

    private void startTickAnimation()
    {
        timer = new Timer((int) increment, new ActionListener()
        {

            int time = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                draw_tick_part_one = true;
                timer_running = true;
                draw_tick = true;
                if (time == 0)
                {
                    tick_x = centerX - tick_third_;
                    tick_y = centerY;
                }

                time += increment;
                if (time <= total_time)
                {
                    repaint();
                    //postInvalidate();
                } else
                {
                    draw_tick_part_one = false;
                    timer.stop();
                    startTickPartTwoAnimation();
                }
            }
        });
        timer.start();
    }

    private void startTickPartTwoAnimation()
    {

        timer = new Timer((int) increment, new ActionListener()
        {

            int time = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                timer_running = true;
                draw_tick = true;
                if (time == 0)
                {
                    tick_x_two = tick_x;
                    tick_y_two = tick_y;
                }

                time += increment;
                if (time <= total_time)
                {
                    repaint();
                    //postInvalidate();
                } else
                {
                    timer_running = false;
                    draw_tick = false;
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public Color getTickColor()
    {
        return tick_color;
    }

    public void setTickColor(Color tick_color)
    {
        this.tick_color = tick_color;
    }


    public Color getTextColor()
    {
        return text_color;
    }

    public void setTextColor(Color text_color)
    {
        this.text_color = text_color;
    }

    public boolean isShowOuterCircle()
    {
        return showOuterCircle;
    }

    public void setShowOuterCircle(boolean showOuterCircle)
    {
        this.showOuterCircle = showOuterCircle;
    }

    public Color getInnerCircleColor()
    {
        return innerCircleColor;
    }

    public void setInnerCircleColor(Color innerCircleColor)
    {
        this.innerCircleColor = innerCircleColor;
    }

    public Color getOuterCircleColor()
    {
        return outerCircleColor;
    }

    public void setOuterCircleColor(Color outerCircleColor)
    {
        this.outerCircleColor = outerCircleColor;
    }

    public Color getCircleBorderColor()
    {
        return circleBorderColor;
    }

    public void setCircleBorderColor(Color circleBorderColor)
    {
        this.circleBorderColor = circleBorderColor;
    }

    public float getTickThickness()
    {
        return tickThickness;
    }

    public void setTickThickness(float tickThickness)
    {
        this.tickThickness = tickThickness;
    }

    public float getBorderThickness()
    {
        return borderThickness;
    }

    public void setBorderThickness(float borderThickness)
    {
        this.borderThickness = borderThickness;
    }

    public float getTextLeftPadding()
    {
        return textLeftPadding;
    }

    public void setTextLeftPadding(float textLeftPadding)
    {
        this.textLeftPadding = textLeftPadding;
    }

    public float getTextSize()
    {
        return textSize;
    }

    public void setTextSize(float textSize)
    {
        this.textSize = textSize;
    }

    public float getInnerCircleRadius()
    {
        return innerCircleRadius;
    }

    public void setInnerCircleRadius(float innerCircleRadius)
    {
        this.innerCircleRadius = innerCircleRadius;
    }

    public float getOuterCircleRadius()
    {
        return outerCircleRadius;
    }

    public void setOuterCircleRadius(float outerCircleRadius)
    {
        this.outerCircleRadius = outerCircleRadius;
    }

    public void setChecked(boolean isChecked)
    {
        if (!timer_running)
        {
            this.isChecked = isChecked;
            if (listener != null)
            {
                listener.onCheckedChanged(this, isChecked);
            }
            if (isChecked)
            {
                current_radius = 0;
                inc_tick = 0;
            }
            time = 0;
            startAnimationTimer();
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener)
    {
        this.listener = listener;
    }

    public interface OnCheckedChangeListener
    {
        void onCheckedChanged(CircleCheckBox view, boolean isChecked);
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        if (text != null)
        {
            this.text = text;
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 100);
    }

    public void drawCircle(Graphics2D graphics, float x, float y, float radius, boolean stroke)
    {
        if (stroke)
        {
            graphics.drawOval((int) (x - radius / 2), (int) (y - radius / 2), (int) radius, (int) radius);
        } else
        {
            graphics.fillOval((int) (x - radius / 2), (int) (y - radius / 2), (int) radius, (int) radius);
        }
    }

    public void drawLine(Graphics2D graphics, float x1, float y1, float width, float height)
    {

    }
}