

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.fh.solver.KnuthTest;
import nl.fh.solver.NodeTableTest;
import nl.fh.solver.BasicSolverTest;
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
    KnuthTest.class
})
public class TestSuite {
    
}
