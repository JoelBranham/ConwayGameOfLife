/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridModelTest {
    
    private GridModel oscillator1;
    
    public GridModelTest() {
    }
    
    @Before
    public void setUp() {
        oscillator1 = new GridModel("test\\gameoflife\\oscillator1.txt");
    }

    @Test
    public void testSetUpOscillator1() {
        boolean[][] expected = {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
        };
        for (int i = 0; i < oscillator1.getNumRows(); i++){
            for (int j = 0; j < oscillator1.getNumCols(); j++){
                assertEquals(expected[i][j], oscillator1.locationAlive(i,j));
            }
        }
    }

    @Test
    public void testTickOscillator1() {
        for (int count = 0; count < 100; count++){
            oscillator1.tick();
            if (count % 2 == 0){
                boolean[][] firstTick = {
                    {false, false, false, false, false},
                    {false, false, false, false, false},
                    {false, true, true, true, false},
                    {false, false, false, false, false},
                    {false, false, false, false, false}
                };
                for (int i = 0; i < oscillator1.getNumRows(); i++){
                    for (int j = 0; j < oscillator1.getNumCols(); j++){
                            assertEquals(firstTick[i][j], oscillator1.locationAlive(i,j));
                    }
                }
            }
            else{
                boolean[][] secondTick = {
                    {false, false, false, false, false},
                    {false, false, true, false, false},
                    {false, false, true, false, false},
                    {false, false, true, false, false},
                    {false, false, false, false, false}
                };
                for (int i = 0; i < oscillator1.getNumRows(); i++){
                    for (int j = 0; j < oscillator1.getNumCols(); j++){                      
                        assertEquals(secondTick[i][j], oscillator1.locationAlive(i,j));
                    }
                }
            }
        }
    }

    @Test
    public void testGetNumRows() {
        assertEquals(5, oscillator1.getNumRows());
    }

    @Test
    public void testGetNumCols() {
       assertEquals(5, oscillator1.getNumRows()); 
    }
    
}
