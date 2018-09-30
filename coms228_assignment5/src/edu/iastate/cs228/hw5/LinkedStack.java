package edu.iastate.cs228.hw5;
// This class is complete.

import java.util.*;

public class LinkedStack<E>
{
  private class SNode
  {
    public E data;
    public SNode link;
  }

  private SNode top;
  private int numItems;

  public LinkedStack()
  {
    top = null;
    numItems = 0;
  }

  public int size()
  {
    return numItems;
  }

  public boolean isEmpty()
  {
     return numItems == 0;
  }

  public void push(E element)
  {
    SNode toAdd = new SNode();
    toAdd.data = element;
    toAdd.link = top;
    top = toAdd;
    numItems++;
  }

  public E pop()
  {
    if ( top == null )
      throw new NoSuchElementException();
    E returnVal = top.data;
    top = top.link;
    if ( numItems <= 0 )
      throw new RuntimeException("num 1");
    numItems--;
    return returnVal;
  }

  public E peek()
  {
    if ( top == null )
      throw new NoSuchElementException();
    return top.data;
  }
} // LinkedStack
