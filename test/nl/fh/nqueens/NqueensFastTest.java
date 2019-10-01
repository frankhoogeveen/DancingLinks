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
public class NqueensFastTest {
    
    public NqueensFastTest(){
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
        Set<Set<ChessLocation>> result = NqueensSolver.solve(4);
        assertEquals(2, result.size());
    }
    
    @Test
    public void FiveTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solve(5);
        assertEquals(10, result.size());
    }
    
    @Test
    public void SixTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solve(6);
        assertEquals(4, result.size());
    }
    @Test
    public void SevenTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solve(7);
        assertEquals(40, result.size());
    }
        @Test
    public void EightTest(){
        Set<Set<ChessLocation>> result = NqueensSolver.solve(8);
        assertEquals(92, result.size());
    }

}
