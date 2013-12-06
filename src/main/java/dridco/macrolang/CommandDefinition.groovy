package dridco.macrolang

import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import groovy.transform.EqualsAndHashCode

import static org.apache.commons.lang.StringUtils.EMPTY

@EqualsAndHashCode(includeFields = true, excludes = 'template')
class CommandDefinition implements Definition {

    private String name
    private String target
    private String value

    private Template template

    def CommandDefinition(String name, String target, String value) {
        this.name = name
        this.target = target
        this.value = value

        template = new SimpleTemplateEngine().createTemplate """
<tr>
    <td>${name ?: EMPTY}</td>
    <td>${target ?: EMPTY}</td>
    <td>${value ?: EMPTY}</td>
</tr>"""
    }

    String render(Map<String, String> context) {
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