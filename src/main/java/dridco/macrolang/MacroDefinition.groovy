package dridco.macrolang

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Slf4j

import static org.apache.commons.lang.StringUtils.EMPTY

@Slf4j
@EqualsAndHashCode(includes = 'name')
class MacroDefinition implements Definition {

    String name
    def steps

    String render(Map<String, String> context) {
        log.debug "Entering macro $name"
        def rendered = steps*.render(context).join EMPTY
        log.debug "Exiting macro $name"
        rendered
    }
}
