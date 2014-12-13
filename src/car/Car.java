package car;


import java.util.ArrayList;
import priorityQueue.Pack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matexo
 */
public class Car {
    private ArrayList<Integer> deliveryRoad;
    private ArrayList<Pack> pack;
    private ArrayList<Integer> timeTable;
    
    private int time; // jako sumy czesciowe w tablicy => prostsze do wypisywania w loggerze
    private int size;
    
    public int getTime() { return time;}
    public void addTime(int x) { time += x; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public void addSize() { size++; }
    
    public int getTimeTableSize () { return timeTable.size(); }
    
    public Car(int startingPosition)
    {
    deliveryRoad = new ArrayList<>();
    deliveryRoad.add( startingPosition );
    pack = new ArrayList<>();
    timeTable = new ArrayList<>();
    timeTable.add(0);
    time=0;
    this.size=0;
    }
    
    public void writeRoad()
    {
        for(int i=0;i<deliveryRoad.size();i++)
        System.out.print(deliveryRoad.get(i) + " ");
        System.out.println(deliveryRoad.size() == timeTable.size());
        for(int i=0;i<timeTable.size();i++)
        System.out.print(timeTable.get(i) + " ");
        System.out.println(" ");
        
    }
    
    
    
    
    public void setTimeTable ( int time)
    {
    timeTable.add(time);
    }
    
    public Integer getTimeTable (int index)
    {
    return timeTable.get(index);
    }
    
    
    public Integer getPosition()
    {
    return deliveryRoad.get(deliveryRoad.size()-1);
    }
    
    
    public Integer getCityId(int index)
    {
    return deliveryRoad.get(index);
    }
    
    public void setCityId(int cityId)
    {
    deliveryRoad.add(cityId);
    }
        
    public void setPack(Pack pack)
    {
    this.pack.add(pack);
    }      
    
    public Pack getPack(int index)
    {
    return pack.get(index);
    }
    
    public int getPackSize() { return pack.size(); }
            
    
    
    
}
