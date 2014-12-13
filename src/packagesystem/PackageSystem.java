/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagesystem;

import java.io.FileNotFoundException;
import logic.Logic;
import map.Map;
import priorityQueue.*;
import parser.Parser;
import pathFinder.*;

/**
 *
 * @author Matexo
 */
public class PackageSystem {

    public static void main(String[] args) throws FileNotFoundException  {
        Map map = Parser.parseMap("map.txt");
        map = Parser.parseConnection("connections.txt", map);
        PriorityQueue<Pack> pack= new PriorityQueue<>();
        pack = Parser.parsePackage("packages.txt");
        
        Logic logic = new Logic(map, pack , 2 , 2 , 0);
        logic.run();
        
        
    }
    
}
