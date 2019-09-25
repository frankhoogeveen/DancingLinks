

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.fh.solver.SetupTest;
import nl.fh.link.HideRestoreTest;
import nl.fh.solver.SolverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author frank
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SetupTest.class,
    HideRestoreTest.class,
    SolverTest.class
})
public class TestSuite {
    
}
