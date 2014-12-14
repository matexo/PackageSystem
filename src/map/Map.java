/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;

/**
 *
 * @author Matexo
 */
public class Map {
    private ArrayList<City> city;
    
    public Map()                                            { city = new ArrayList<>(); }
    public void addCity (int cityId , String cityName )     { city.add(new City(cityId , cityName)); }
    public int getSize()                                    { return city.size(); }
    public City getCity(int i)                              { return city.get(i); }
   
    public void generatePosition()
    {
    double angle = 360/this.getSize();
    double currentAngle = 0;
    for(int i =0; i<this.getSize();i++)
        {
        this.getCity(i).position.x = (int)(300+Math.cos(Math.toRadians(currentAngle))*200);
        this.getCity(i).position.y = (int)(300+Math.sin(Math.toRadians(currentAngle))*200);
        currentAngle += angle;
        }
    }
    
    public String toString() 
    {
    String cityInfo = "";
    for(int i=0;i<city.size();i++)
        cityInfo += city.get(i).toString() + "\n";
    return cityInfo;
    }
}
