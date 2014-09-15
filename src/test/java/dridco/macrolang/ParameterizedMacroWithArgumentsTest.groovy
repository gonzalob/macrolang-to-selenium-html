package dridco.macrolang

class ParameterizedMacroWithArgumentsTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ['''
<macro name="welcome">
    <parametrized-macro name="greet-$who" greet="hello" />
</macro>
''', '''
<macro name="greet-foo">
    <command name="echo" target="${greet} foo" />
</macro>
''', '''
<macro name="greet-bar">
    <command name="echo" target="${greet} bar" />
</macro>
''']
    }

	@Override
	def expectedCommands() {
        '''
<tr>
    <td>echo</td>
    <td>hello foo</td>
    <td></td>
</tr>'''
	}

	@Override
	def definedCommands() {
        '<welcome who="foo" />'
	}

}
