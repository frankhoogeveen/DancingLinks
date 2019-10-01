package nl.fh.nqueens;

import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frank
 */
public class NqueensHalfTest {
    
    public NqueensHalfTest(){
    }
    
    private void display(Set<Set<ChessLocation>> result){
        StringBuilder sb = new StringBuilder();
        sb.append("===============\n");
        for(Set<ChessLocation> set : result){
            sb.append("[");
            for(ChessLocation item : set){
                sb.append(item);
                sb.append(" ");
            }
            sb.append("]\n");
        }
        System.out.println(sb);
    }
    
    @Test
    public void FourTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solveHalf(4);
        assertEquals(7, result.size());
    }
    
    @Test
    public void FiveTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solveHalf(5);
        assertEquals(23, result.size());
    }
    
    @Test
    public void SixTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solveHalf(6);
        assertEquals(83, result.size());
    }
    @Test
    public void SevenTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solveHalf(7);
        assertEquals(405, result.size());
    }
        @Test
    public void EightTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solveHalf(8);
        assertEquals(2113, result.size());
    }

}
