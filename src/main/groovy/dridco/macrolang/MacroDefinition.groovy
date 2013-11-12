package dridco.macrolang

import groovy.transform.Immutable

import static org.apache.commons.lang.StringUtils.EMPTY

@Immutable
class MacroDefinition implements Definition {

    String name
    List<Definition> steps

    String toString() {
        steps.join EMPTY
    }
}
