package dridco.macrolang

class SeleniumTestCompiler {

    static String DEFAULT_ENCODING = 'UTF-8'

    Set<MacroDefinition> macros = [] as Set

    def SeleniumTestCompiler(Iterable<String> macroSources) {
        macroSources.each { macros << parseMacro(it) }
    }

    def compile(String source) {
        XmlParser parser = new XmlParser()
        def test = parser.parseText source
        def tasks = test.children()
        def encoding = test.'@encoding' ?: DEFAULT_ENCODING
        def base = test.'@base'
        def title = test.'@title'
        def commands = new StringBuilder()
        tasks.each { Node node ->
            commands.append parse(node)
        }
        """<?xml version="1.0" encoding="$encoding"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=$encoding" />
<link rel="selenium.base" href="$base" />
<title>$title</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">$title</td></tr>
</thead><tbody>$commands
</tbody></table>
</body>
</html>"""
    }

    Task parse(Node source) {
        new Task(definition: definition(source), context: source.attributes())
    }

    Definition definition(Node source) {
        source.name() == 'command' ? parseCommand(source) : macros.find { it.name == source.name() }
    }

    MacroDefinition parseMacro(String source) {
        def macro = new XmlParser().parseText source
        new MacroDefinition(name: macro.'@name', steps: macro.children().collect { Node node -> definition node })
    }

    static CommandDefinition parseCommand(source) {
        new CommandDefinition(name: source.'@name', target: source.'@target', value: source.'@value')
    }
}
