package dridco.macrolang

import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Slf4j

import java.util.concurrent.ConcurrentHashMap

import static org.apache.commons.lang.StringUtils.EMPTY

@Slf4j
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
        log.debug "Processing command $name"
        def squeezed = squeeze context
        template.make(squeezed).toString()
    }

    static squeeze(Map<String, String> source) {
        source.findAll { it.value instanceof String }.collectEntries { key, value ->
            if (value.toString().contains('$')) {
                def template = templates.get(value.toString())
                return [key, template.make(new HashMap(source))]
            } else {
                return [key, value]
            }
        }
    }

    static Map<String, Template> templates = new ConcurrentHashMap<String, Template>().withDefault {
        new SimpleTemplateEngine().createTemplate it
    }
}