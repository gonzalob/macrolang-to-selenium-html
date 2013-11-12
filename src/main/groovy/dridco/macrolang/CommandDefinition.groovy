package dridco.macrolang

import groovy.transform.Immutable

import static org.apache.commons.lang.StringUtils.EMPTY

@Immutable
class CommandDefinition implements Definition {

    String name = EMPTY
    String target = EMPTY
    String value = EMPTY

    @Override
    String toString() {
        """
<tr>
    <td>$name</td>
    <td>$target</td>
    <td>$value</td>
</tr>"""
    }
}
