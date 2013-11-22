package dridco.macrolang

class SeleniumTestCompiler {

    static String DEFAULT_ENCODING = 'UTF-8'

    Set<MacroDefinition> macros = [] as Set<MacroDefinition>

    def SeleniumTestCompiler(Iterable<String> macroSources) {
        macroSources.each { macros << parseMacro(it) }
    }

    SeleniumTest compile(String source) {
        XmlParser parser = new XmlParser()
        def test = parser.parseText(source)
        def tasks = test.children()
        def base = loadRequired test, 'base'
        def name = loadRequired test, 'name'
        def encoding = test.'@encoding' ?: DEFAULT_ENCODING
        def title = test.'@title' ?: name
        def commands = new StringBuilder()
        tasks.each { Node node ->
            commands.append parse(node)
        }
        new SeleniumTest(name: name, code: """<?xml version="1.0" encoding="$encoding"?>
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
</html>""")
    }

    Task parse(Node source) {
        new Task(definition: define(source), context: source.attributes())
    }

    def define(Node source) {
        def tag = source.name()
        switch (tag) {
            case 'command':
                parseCommand source
                break;
            case 'parametrized-macro':
                deferred source.attribute('name')
                break;
            default:
                loadMacro tag
                break;
        }
    }

    def loadMacro(name) {
        macros.find { it.name == name } ?: deferred(name)
    }

    def deferred(name) {
        new DeferredDefinition(all: macros, name: name)
    }

    MacroDefinition parseMacro(String source) {
        def macro = new XmlParser().parseText(source)
        new MacroDefinition(name: macro.'@name', steps: macro.children().collect { Node node -> parse node })
    }

    static CommandDefinition parseCommand(source) {
        new CommandDefinition(name: source.'@name', target: source.'@target', value: source.'@value')
    }

    private static loadRequired(source, name) {
        def value = source."@$name"
        assert value != null: "Attribute $name is required"
        value
    }
}
