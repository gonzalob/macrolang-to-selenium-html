package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class DefaultEncodingTest extends AbstractCommandTest {

    @Override
    protected encoding() { 'KOI-8' }

    @Override
    protected expectedCommands() { EMPTY }

    @Override
    protected definedCommands() { EMPTY }
}
