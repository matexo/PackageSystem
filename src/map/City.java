/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author Matexo
 */
public class City {
    private int cityId ;
    private String cityName;
    private ArrayList<Connection> cityConnection;
    private ShortestDistance SDist;
    public Position position;
    private Color color;
        
    public City( int cityId , String cityName ) 
    {
        this.cityId = cityId;
        this.cityName = cityName ;
        cityConnection = new ArrayList<>();
        position = new Position();
        color = Color.black;
    }
    
    public int getCityId()                                                      { return cityId; }
    public void setCityId( int cityId )                                         { this.cityId = cityId; }
    public String getCityName()                                                 { return cityName; }
    public void setCityName( String cityName )                                  { this.cityName = cityName; }
    public void addCityConnection(int distance , int cityId )                   { cityConnection.add(new Connection(distance , cityId)); }
    public Connection getConnection(int i)                                      { return cityConnection.get(i); }
    public int getSize()                                                        { return cityConnection.size(); }
    public ShortestDistance getDist()                                           { return SDist; }
    public void setShortestDistance(ShortestDistance s)                         { SDist = s; }
    public void setColor(Color color) { this.color = color;}
    public Color getColor() { return color;}
    
    public Connection getConnectionByDestinationId (int destinationId)
    {
    for(int i =0; i< cityConnection.size();i++)
        {
        if(cityConnection.get(i).getCityId() == destinationId)
            return cityConnection.get(i);
        }
    return null;
    }
    
    @Override
    public String toString() 
    {
    String cityInfo = cityId + ": " + cityName + "\n";
    for(int i=0;i<cityConnection.size();i++)
        cityInfo += cityConnection.get(i).toString();
    return cityInfo;
    }
    
}
