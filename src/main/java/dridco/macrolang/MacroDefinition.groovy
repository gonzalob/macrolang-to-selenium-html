package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class MacroDefinition {

    String name
    def steps

    String render(context) {
        steps*.render(context).join EMPTY
    }
}
