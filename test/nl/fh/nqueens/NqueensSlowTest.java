package nl.fh.nqueens;

import java.util.Set;
import nl.fh.categories.SlowTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frank
 */
public class NqueensSlowTest {
    
    public NqueensSlowTest(){
    }
    
    @Category(SlowTest.class)
    @Test
    public void LargeTest(){
        assertEquals(352, NqueensSolver.solve(9).size());
        assertEquals(724, NqueensSolver.solve(10).size());
        assertEquals(2680, NqueensSolver.solve(11).size());
        assertEquals(14200, NqueensSolver.solve(12).size());
        assertEquals(73712, NqueensSolver.solve(13).size());
    }
   
}
