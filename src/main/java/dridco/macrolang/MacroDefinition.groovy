package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

class MacroDefinition implements Definition {

    String name
    def steps

    String render(Map<String, String> context) {
        steps*.render(context).join EMPTY
    }
}
