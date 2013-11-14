package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class MacroDefinition implements Definition {

    String name
    List<Definition> steps

    @Override
    String toString() {
        steps.join EMPTY
    }
}
