package dridco.macrolang

import groovy.transform.EqualsAndHashCode

import static org.apache.commons.lang.StringUtils.EMPTY

@EqualsAndHashCode(includes = 'name')
class MacroDefinition implements Definition {

    String name
    def steps

    String render(Map<String, String> context) {
        steps*.render(context).join EMPTY
    }
}
