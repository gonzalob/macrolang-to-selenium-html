package dridco.macrolang

import org.apache.commons.lang.StringUtils

import static org.apache.commons.lang.StringUtils.EMPTY

class NoCommandsTest extends AbstractCommandTest {

    @Override
    def expectedCommands() {
        EMPTY
    }

    @Override
    def definedCommands() {
        EMPTY
    }
}
