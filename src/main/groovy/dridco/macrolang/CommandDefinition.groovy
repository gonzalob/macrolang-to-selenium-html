package dridco.macrolang

import static org.apache.commons.lang.StringUtils.EMPTY

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
