/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rafae
 */

/*
 This class is responsible for calculating and presenting 
 the banker's offer in the Deal or No Deal game. 
 It extends MechanicsControl as it contains all info required in one game 
 and implements the IBanker interface, ensuring it provides the bankerOffer method.
*/
public class MechanicsBanker extends MechanicsControl implements IBanker
{
    FileOutGameLog folog = new FileOutGameLog();
    
    @Override
    public double bankerOffer(Cases cases)
    {
        double totalValue = 0;
        double highestValue = 0;
        int numCases = cases.getCases().size();
        
        // Calculate total value and find the highest value among the remaining cases
        for(double d : cases.getCases().values())
        {
            totalValue += d;
            
            if(d > highestValue)
            {
                highestValue = d;
            }
        }
        
        // Calculate the average total value of the remaining cases
        double avgTot = totalValue / numCases;
        
        // Calculate the offer multiplier based on the round number, capped at 0.80
        double multiplier = 0.30 + (roundNum * 0.10);
        if(multiplier > 0.80)
            multiplier = 0.80;
        
        // Calculate the risk factor based on the highest value
        // Calculate the final offer using the average total value, multiplier, and risk factor
        double risk = highestValue / totalValue;
        double offer = avgTot * multiplier * (1 - (risk * 0.25));
        
        double roundedOffer = Math.round(offer * 100.0) / 100.0;
        System.out.println("Banker's offer is $" + roundedOffer + "!\n");
        
        // Log the banker's offer to game log file
        folog.FileOutLog(Player.firstName, Player.lastName, "Banker offered $" + roundedOffer);
        
        return roundedOffer;
    }
}
