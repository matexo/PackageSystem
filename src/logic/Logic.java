/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import car.Car;
import java.util.ArrayList;
import map.Map;
import map.ShortestDistance;
import priorityQueue.*;
import pathFinder.*;

/**
 *
 * @author Matexo
 */
public class Logic {
    
    public final int MAX_SIZE;
    private Map map;
    private PriorityQueue<Pack> pack;
    private Car car[];
    PriorityQueue<LogInfo> heap;
    private final int startingPosition;
    
    public Car[] getCars() { return car; }
    
    public Logic(Map map , PriorityQueue queue , int carCounter , int size , int startingPosition)
    {
    this.map = map;
    pack = queue;
    car = new Car[carCounter];
    for(int i=0;i<carCounter;i++)
       car[i] = new Car(startingPosition);
    this.startingPosition = startingPosition;
    MAX_SIZE = size;
    heap = new PriorityQueue<>();
    }
    
    
    public void run()
    {
    generateShortestRoute();
    simulateRoute();
    addLogs();
    }
    
    public void addLogs()
    {
    for(int i =0;i<car.length;i++)
        {
        int time=0;
        for(int j=1 , k=0 ;j<car[i].getTimeTableSize();j++)
            {
            if(car[i].getCityId(j) == 0 ) 
                {
                time= car[i].getTimeTable(j);
                continue;
                }
            heap.insert( new LogInfo(startingPosition , car[i].getPack(k), time , i , false));
            heap.insert( new LogInfo(car[i].getCityId(j-1) , car[i].getPack(k) , car[i].getTimeTable(j), i , true));
            k++;
            }
        }
    }
    
    public String writeLog(LogInfo tmp)
    {
        if(tmp == null) return "KONIEC";
        String log = tmp.time + " Samochód nr:" + tmp.carId + " " + task(tmp.status) + "przesyłkę " + tmp.pack.getPackageId() 
                + " " + tmp.pack.getPackageName() + task2(tmp.status) + " " ;
        if(tmp.status == true) log += map.getCity(tmp.pack.getDestinationCityId()).getCityId() 
                            + " : " + map.getCity(tmp.pack.getDestinationCityId()).getCityName();
        else log += map.getCity(tmp.city).getCityId() + " : " + map.getCity(tmp.city).getCityName();
        return log;
    }
    
    public String writeLogs()
    {
    String log = " ";
    PriorityQueue<LogInfo> heapTmp = new PriorityQueue<>();
    while(!heap.isEmpty())
        {
        LogInfo tmp = heap.pop();
        log += writeLog(tmp);
        log += "\n";
        heapTmp.insert(tmp);
        }
    heap = heapTmp;
    return log;
    }
    
    public String task( boolean status)
    {
    if(!status)
        return " pobrano ";
    else return " dostarczono ";
    }
    
    public String task2 (boolean status)
    {
    if(!status)
        return "z miasta ";
    else return "do miasta ";   
    }
    
    public void simulateRoute()
    {
    while(!pack.isEmpty())
        {
        Pack packTmp = pack.pop();    
        int destinationCityId = packTmp.getDestinationCityId();
        int timeMin = Integer.MAX_VALUE;
        int indexOfCar = -1;
        for(int i=0;i<car.length;i++)
            {
            if(car[i].getSize() == MAX_SIZE)
                {
                car[i].addTime( map.getCity(car[i].getPosition()).getDist().SDistanceToCity[startingPosition] );
                car[i].setTimeTable(car[i].getTime());
                car[i].setCityId(startingPosition);
                car[i].setSize(0);
                }
            if(car[i].getTime() < timeMin)
                {
                timeMin = car[i].getTime(); 
                indexOfCar = i;        
                }
            }
        car[indexOfCar].addSize();
        car[indexOfCar].addTime(map.getCity(car[indexOfCar].getPosition()).getDist().SDistanceToCity[destinationCityId]);
        car[indexOfCar].setTimeTable(car[indexOfCar].getTime());
        car[indexOfCar].setCityId(destinationCityId);
        car[indexOfCar].setPack(packTmp);        
        }         
    }
    
    public void generateShortestRoute()
    {
    PathFinder pathFinder = new PathFinder(map);
    for(int i=0; i<map.getSize() ; i++)
        {
        pathFinder.findShortestPath(i);
        map.getCity(i).setShortestDistance(new ShortestDistance( pathFinder.getDistanceCost() , pathFinder.getPrevious()));
        }       
    }
    
    public ArrayList<LogInfo> getLogs() 
    {
    ArrayList<LogInfo> list = new ArrayList<>();
    PriorityQueue<LogInfo> heapTmp = new PriorityQueue<>();
    while(!heap.isEmpty())
        {
        LogInfo tmp = heap.pop();
        list.add(tmp);
        heapTmp.insert(tmp);
        }
    heap = heapTmp;
    return list;
    }
}
