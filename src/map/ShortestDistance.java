/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

/**
 *
 * @author Matexo
 */
public class ShortestDistance {
    
    public int SDistanceToCity[];
    public int previus[];
    
    public ShortestDistance(int[] SDistanceToCity , int[] previus)
    {
    this.SDistanceToCity = SDistanceToCity.clone();
    this.previus = previus.clone();
    }
    
}
