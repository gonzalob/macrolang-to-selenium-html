package dridco.macrolang

import org.junit.Test

class NonExistingMacroTest {

    @Test(expected = IllegalArgumentException)
    void "fails fast when reading an inexisting macro"() {
        new SeleniumTestCompiler([]).compile("<test name='test-case' base='http://localhost'><non-existing-macro /></test>")
    }
}
