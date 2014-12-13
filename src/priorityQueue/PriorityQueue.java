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
public class PriorityQueue<T extends Comparable<T>>{
    
    private T[] object;
    private int size;
    
    public PriorityQueue() 
    {
    object = (T[])new Comparable[1024];
    size = 0 ;
    }
    
    public PriorityQueue(int size) 
    {
    object = (T[])new Comparable[size];
    this.size = 0 ;
    }
    
    public void clear() 
    {
    size = 0;
    }

    public T pop() 
    {
    T objectTmp = object[0];
    object[0] = object[--size];
    heapDown();
    return objectTmp;
    }

    private void heapDown() 
    {
    int i = 0;
    int index = 2*i + 1;
    while (index < size)
        {
        if( index+1 < size && object[index+1].compareTo(object[index]) == 1) 
            index++;
        if(object[index].compareTo(object[i]) != 1) 
            return;
        swap(i,index);
        i = index;
        index = 2*i+1;
        }
    }
    
    private void swap(int x1 , int x2)
    {
    T objectTmp = object[x1];
    object[x1] = object[x2];
    object[x2] = objectTmp;
    }
    
    public T get() 
    {
    return object[0];
    }

    public int getSize() 
    {
    return size;
    }

    public void insert(T pack) 
    {
    this.object[size] = pack;
    size++;
    if(size != 1) heapUp();
    }
    
    private void heapUp() 
    {
    int i = size-1;
    int p = (size-1)/2;
    while (i>0 && object[p].compareTo(object[i]) == -1)
        {
        swap(p,i);
        i=p;
        p=(i-1)/2;
        }
    }

    public boolean isEmpty() 
    {
    return size <= 0;
    }
    
    @Override
    public String toString() 
    {
    String tmp="";
    for(int i = 0; i < size ; i++)
        tmp = tmp + object[i].toString() + " ";
    return tmp;
    }
    
}
