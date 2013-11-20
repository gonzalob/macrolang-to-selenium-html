package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class NoCommandsTest extends AbstractCommandTest {

    @Override
    protected expectedCommands() { EMPTY }

    @Override
    protected definedCommands() { EMPTY }
}
