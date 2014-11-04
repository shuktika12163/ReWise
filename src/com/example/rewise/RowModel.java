package com.example.rewise;

public class RowModel {
    
    private  String firstLine="";
    private  String secondLine="";
         
    /*********** Set Methods ******************/
     
    public void setFirstLine(String FirstLine)
    {
        this.firstLine = FirstLine;
    }
     
    public void setsecondLine(String SecondLine)
    {
        this.secondLine = SecondLine;
    }
     
    /*********** Get Methods ****************/
    public String getFirstLine()
    {
        return this.firstLine;
    }
     
    public String getsecondLine()
    {
        return this.secondLine;
    }
        
}