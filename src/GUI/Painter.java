/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import car.Car;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import map.Map;

/**
 *
 * @author Matexo
 */
public class Painter extends JPanel{
    
    public Map map;
    public Car[] cars;
    public int radius = 10;
    
    
    public Painter(Map map , Car[] cars)
    {
    this.map = map;
    this.cars = cars;
    }

    @Override
    public void paintComponent(Graphics g)
    {
    super.paintComponent(g);
    g.setColor(Color.black);
    for(int i=0;i<map.getSize();i++)
        {
        int x1 = map.getCity(i).position.x;    
        int y1 = map.getCity(i).position.y;       
        drawCircle(g , x1 , y1 , radius);
        g.setColor(Color.RED);
        g.drawString(String.valueOf(i) , x1 , y1-25);
        for(int j =0;j<map.getCity(i).getSize();j++)
            {
            g.setColor(map.getCity(i).getConnection(j).getColor());
            int x2 = map.getCity(map.getCity(i).getConnection(j).getCityId()).position.x;
            int y2 = map.getCity(map.getCity(i).getConnection(j).getCityId()).position.y;
            g.drawLine( x1 , y1 , x2 , y2);
            g.drawString(String.valueOf(map.getCity(i).getConnection(j).getDistance()) , (x1+x2)/2+10 , (y1+y2)/2-10);
            }
        }

    }
    
    private void drawCircle(Graphics g, int x, int y, int radius) 
    {
    g.fillOval(x-radius, y-radius, radius*2, radius*2);
    }


}
