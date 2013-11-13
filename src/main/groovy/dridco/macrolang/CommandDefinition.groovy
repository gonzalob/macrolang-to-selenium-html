package dridco.macrolang

import groovy.transform.Immutable

import static org.apache.commons.lang.StringUtils.EMPTY

@Immutable
class CommandDefinition implements Definition {

    String name
    String target
    String value

    @Override
    String toString() {
        """
<tr>
    <td>${name ?: EMPTY}</td>
    <td>${target ?: EMPTY}</td>
    <td>${value ?: EMPTY}</td>
</tr>"""
    }
}
