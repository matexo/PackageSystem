/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.Color;

/**
 *
 * @author Matexo
 */
public class Connection implements Comparable<Connection>{
    private int distance;
    private int cityId;
    private Color color;
    
    public Connection ( int distance , int cityId ) 
    {
       this.distance = distance ;
       this.cityId = cityId ;
       color = Color.black;
    }
        
    public int getDistance() { return distance; }
    public int getCityId() { return cityId; }
    public void setDistance( int distance ) { this.distance = cityId; }
    public void setCityId( int cityId) { this.cityId = cityId; }
    public void setColor(Color color) { this.color = color;}
    public Color getColor() { return color;}
    
    
    @Override
    public String toString() 
    {
    return "    Droga do miasta:" + cityId + " Odległość:" + distance + "\n";
    }

    @Override
    public int compareTo(Connection o) 
    {
    if(this.distance > o.getDistance()) return 1;
    else if (this.distance < o.getDistance()) return -1;
    else return 0;
    }
}
