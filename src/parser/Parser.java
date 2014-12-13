/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import map.Map;
import priorityQueue.*;

/**
 *
 * @author Matexo
 */
public class Parser {
    
    public static Map parseMap(String fileName) throws FileNotFoundException // to test
    {
    Map map = new Map();
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String buffer = null;
    try 
        {
        if( (buffer = reader.readLine()) != null && buffer.substring(0, 8).equals("# miasta"))
            {
            while ((buffer = reader.readLine()) != null)
                {
                String[] tmp = buffer.split("\\s+");
                try 
                    {
                    int cityId = Integer.parseInt(tmp[0]);
                    String cityName = tmp[1];
                    map.addCity(cityId , cityName);
                    }
                catch (ArrayIndexOutOfBoundsException e)
                    {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - zbyt mało pól");
                    }
                catch (NumberFormatException e)
                    {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - \"" + tmp[0] + "\" nie jest liczbą naturalną");
                    }
                }
            }
        }
    catch (IOException e)
        {
        System.err.println(e.getMessage());
        }
    return map;    
    }

    public static Map parseConnection(String fileName , Map map) throws FileNotFoundException 
    {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String buffer;
    try 
        {
        if( (buffer = reader.readLine()) != null && buffer.substring(0, 12).equals("# polaczenia"))
            {
            while ((buffer = reader.readLine()) != null)
                {
                String[] tmp = buffer.split("\\s+");
                try 
                    {
                    int sourceCityId = Integer.parseInt(tmp[0]);
                    int destinationCityId = Integer.parseInt(tmp[1]);
                    int distance = Integer.parseInt(tmp[2]);
                    map.getCity(sourceCityId).addCityConnection(distance, destinationCityId);
                    map.getCity(destinationCityId).addCityConnection(distance, sourceCityId);
                    }
                catch (ArrayIndexOutOfBoundsException e)
                    {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - zbyt mało pól");
                    }
                catch (NumberFormatException e)
                    {
                    System.err.println("Zignorowana linia \"" + buffer + "\" - \"" + "\" nie zawiera 3 liczb naturalnych");
                    }
                }
            }
        }
    catch (IOException e)
        {
        System.err.println(e.getMessage());
        }
    return map;
    }   

    public static PriorityQueue parsePackage(String fileName) throws FileNotFoundException 
    {
    PriorityQueue<Pack> queue = new PriorityQueue<>();
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String buffer;
    try 
        {
        while ((buffer = reader.readLine()) != null)
            {
            String[] tmp = buffer.split("\\s+");
            try 
                {
                String packageId = tmp[0];
                int destinationCityId = Integer.parseInt(tmp[1]);
                int priority = Integer.parseInt(tmp[tmp.length-1]);
                String packageName = "";
                for(int i=2;i<tmp.length-1;i++)
                    packageName += tmp[i] + " ";
                queue.insert(new Pack(packageId, destinationCityId, packageName, priority));
                }
            catch (ArrayIndexOutOfBoundsException e)
                {
                System.err.println("Zignorowana linia \"" + buffer + "\" - zbyt mało pól");
                }
            catch (NumberFormatException e)
                {
                System.err.println("Zignorowana linia \"" + buffer + "\" - \"" + "\" nie zawiera liczb naturalnych");
                }
            }   
        }
    catch (IOException e)
        {
        System.err.println(e.getMessage());
        }
    return queue;
    }  
    
    
    
    
    
}