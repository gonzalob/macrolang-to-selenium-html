package dridco.macrolang

import groovy.text.SimpleTemplateEngine
import groovy.transform.Immutable

import static org.apache.commons.lang.StringUtils.EMPTY

@Immutable
class CommandDefinition {

    String name
    String target
    String value

    String render(Map<String, String> context) {
        def template = new SimpleTemplateEngine().createTemplate """
<tr>
    <td>${name ?: EMPTY}</td>
    <td>${target ?: EMPTY}</td>
    <td>${value ?: EMPTY}</td>
</tr>"""
        def squeezed = squeeze context
        template.make(squeezed).toString()
    }

    static squeeze(Map<String, String> source) {
        source.collectEntries { key, value ->
            def template = new SimpleTemplateEngine().createTemplate value.toString()
            [key, template.make(new HashMap(source))]
        }
    }
}
