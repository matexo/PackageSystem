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
public class LogInfo implements Comparable<LogInfo>{
    
    public Integer time;
    public Integer city;
    public Pack pack;
    
    public LogInfo(Integer cityId, Pack pack, Integer timeTable) 
    {
    this.time = timeTable;
    this.city = cityId;
    this.pack = pack;
    }

    @Override
    public int compareTo(LogInfo o) {
        if(time<o.time) return 1;
        else if(time>o.time) return -1;
        else return 0;
     }
            
}
