package Tamaswingagotchi;

public class ThePet
{
    private int hunger, happy, discipline, weight; //All from 1 to 100
    private final int UNDERWEIGHT = 3, OVERWEIGHT = 25, SAD = 25, STARVING = 9, STUFFED = 81;
    private boolean isSick;
    
    public ThePet()
    {
        isSick = false;
        hunger = 80;//Optimal hunger
        happy = 70;//Optimal happiness is 100
        discipline = 30;//Optimal discipline is 100
        weight = 13;//Optimal weight is 19
    }
}
