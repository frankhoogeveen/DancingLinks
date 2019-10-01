

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.fh.exact.ExactCoverTest;
import nl.fh.exact.ExactHittingTest;
import nl.fh.solver.*;
import nl.fh.sudoku.HardSudokuTest;
import nl.fh.sudoku.KnownSolutionTest;
import nl.fh.categories.SlowTest;
import nl.fh.exact.ExtendedExactCoverTest;
import nl.fh.exact.ExtendedExactHittingTest;
import nl.fh.nqueens.NqueensFastTest;
import nl.fh.nqueens.NqueensHalfTest;
import nl.fh.nqueens.NqueensSlowTest;
import nl.fh.sudoku.SudokuTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author frank
 */
@RunWith(Categories.class)

@ExcludeCategory(SlowTest.class)

@Suite.SuiteClasses({
    NodeTableTest.class,
    BasicSolverTest.class,
    HideUnhideKnuthTest.class,
    HideUnhideThreeTest.class,
    KnuthTest.class,
    ExactHittingTest.class,
    ExactCoverTest.class,
    KnownSolutionTest.class,
    SudokuTest.class,
    ExtendedExactCoverTest.class,
    ExtendedExactHittingTest.class,
    NqueensFastTest.class,
    NqueensSlowTest.class,
    NqueensHalfTest.class,
    
    // the slow tests
    HardSudokuTest.class  
})

public class TestSuite {
    
}
