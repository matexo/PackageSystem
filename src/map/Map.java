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
    
    public Map() 
    {
    city = new ArrayList<>();
    }
    
    public void addCity (int cityId , String cityName )
    {
    city.add(new City(cityId , cityName));
    }
    
    public int getSize() { return city.size(); }
    public City getCity(int i) { return city.get(i); }
   
    
    public String toString() 
    {
    String cityInfo = "";
    for(int i=0;i<city.size();i++)
        cityInfo += city.get(i).toString() + "\n";
    return cityInfo;
    }
}
