/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityQueue;

/**
 *
 * @author Matexo
 */
public class Pack implements Comparable<Pack>{
    private String packageId;
    private int destinationCityId;
    private String packageName;
    private int priority;
    
    public Pack(String packageId , int destinationCityId , String packageName , int priority)
    {
    this.packageId = packageId;
    this.destinationCityId = destinationCityId;
    this.packageName = packageName;
    this.priority = priority;
    }
    
    public String getPackageId() { return packageId;}
    public int getDestinationCityId() { return destinationCityId;}
    public String getPackageName() { return packageName; }
    public int getPriority() { return priority;}
            
    
    @Override
    public String toString() 
    {
    return packageId + ": " + packageName + "(" + priority + ") -> " + destinationCityId;
    }

    @Override
    public int compareTo(Pack o) 
    {
    if(this.priority > o.getPriority())
        return 1;
    else if(this.priority < o.getPriority())
        return -1;
    else return 0;
    }
    
}
