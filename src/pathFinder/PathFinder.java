/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathFinder;

import map.City;
import map.Connection;
import map.Map;
import priorityQueue.*;

/**
 *
 * @author Matexo
 */
public class PathFinder {
    
    private Map map;
    private static final int infinity = Integer.MAX_VALUE;
    private int verticesNumber;
    private int distanceCost[];                 // Tablica kosztów dojścia (d)
    private int previous[];                     // Tablica poprzedników (p)
    private boolean QSSet[];                    // Zbiory Q i S
    private PriorityQueue<ValuePriority> heap;           // Kopiec (h)
    
    public PathFinder(Map map)
    {
    this.map = map;
    verticesNumber = map.getSize();
    distanceCost = new int[verticesNumber];
    previous = new int [verticesNumber];
    QSSet = new boolean [verticesNumber];
    heap = new PriorityQueue<>(verticesNumber);
    }    
    
    private void initData()
    {
    for(int i = 0; i < verticesNumber; i++)
        {
        distanceCost[i] = infinity;
        previous[i] = -1;
        QSSet[i] = false;
        heap.clear();
        }
    
    }
    
    public int[] getDistanceCost() { return distanceCost; }
    public int[] getPrevious() { return previous; }
    
    // dijkstra algoritm with heap
    public void findShortestPath(int startCity)
    {
        initData();
        distanceCost[startCity] = 0;
        previous[startCity] = 0;
        heap.insert(new ValuePriority(startCity , distanceCost[startCity]));
        while(!heap.isEmpty())
            {
            ValuePriority cityTmp = heap.pop();
            City city= map.getCity(cityTmp.getValue());
            QSSet[cityTmp.getValue()] = true;
            
            for(int i = 0 ; i < city.getSize() ; i++)
                {
                Connection connectionTmp = city.getConnection(i);
                if(!QSSet[connectionTmp.getCityId()]  
                      && (distanceCost[connectionTmp.getCityId()] > distanceCost[cityTmp.getValue()] + connectionTmp.getDistance()))
                        {
                        distanceCost[connectionTmp.getCityId()] = distanceCost[cityTmp.getValue()] + connectionTmp.getDistance(); 
                        heap.insert(new ValuePriority(connectionTmp.getCityId() , distanceCost[connectionTmp.getCityId()]) );
                        previous[connectionTmp.getCityId()] = cityTmp.getValue();
                        }
                }
            }
    }
    
    
}
