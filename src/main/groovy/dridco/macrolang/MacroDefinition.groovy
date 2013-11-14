package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class MacroDefinition {

    String name
    def steps

    @Override
    String toString() {
        steps.join EMPTY
    }
}
