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
public class ValuePriority implements Comparable<ValuePriority>{
    
    private int value;
    private int priority;
    
    public ValuePriority( int cityId , int distance)
    {
    this.value = cityId;
    this.priority = distance;
    }
    
    public int getValue() { return value; }
    public int getPriority() { return priority; }

    @Override
    public int compareTo(ValuePriority o) 
    {
    if(this.priority > o.getPriority()) return -1;
    else if (this.priority < o.getPriority()) return 1;
    else return 0;
    }
    
    @Override
    public String toString()
    {
    return "(" + value + " " + priority + ")";
    }
    
}
