package dridco.macrolang

import org.junit.Test

class NonExistingMacroTest {

    @Test(expected = IllegalArgumentException)
    void "fails fast when reading an inexisting macro"() {
        new SeleniumTestCompiler([]).compile("<test><non-existing-macro /></test>")
    }
}
