

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.fh.exact.ExactCoverTest;
import nl.fh.exact.ExactHittingTest;
import nl.fh.solver.*;
import nl.fh.sudoku.SudokuTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author frank
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    NodeTableTest.class,
    BasicSolverTest.class,
    HideUnhideKnuthTest.class,
    HideUnhideThreeTest.class,
    KnuthTest.class,
    ExactHittingTest.class,
    ExactCoverTest.class,
    SudokuTest.class
})
public class TestSuite {
    
}
