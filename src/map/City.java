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
public class City {
    private int cityId ;
    private String cityName;
    private ArrayList<Connection> cityConnection;
    
    private ShortestDistance SDist;
    
    public void setShortestDistance(ShortestDistance s)
    {
    SDist = s;
    }
    
    public ShortestDistance getDist() { return SDist; }
    public void write() 
    {
    for(int i =0;i<SDist.SDistanceToCity.length;i++)
        System.out.print(SDist.SDistanceToCity[i] + " ");
    }
    
    
    // tablica dojsc do kazdego maista ??
    // ilosc paczek ??
    // id paczek ??
    
    public City( int cityId , String cityName ) 
    {
        this.cityId = cityId;
        this.cityName = cityName ;
        cityConnection = new ArrayList<>();
    }
    
    public int getCityId() { return cityId; }
    public String getCityName() { return cityName; }
    public void setCityId( int cityId ) { this.cityId = cityId; }
    public void setCityName( String cityName ) { this.cityName = cityName; }
    
    public void addCityConnection(int distance , int cityId ) 
    {
        cityConnection.add(new Connection(distance , cityId));
    }
    
    public Connection getConnection(int i)
    {
    return cityConnection.get(i);
    }
    
    public int getSize() 
    {
    return cityConnection.size();
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
