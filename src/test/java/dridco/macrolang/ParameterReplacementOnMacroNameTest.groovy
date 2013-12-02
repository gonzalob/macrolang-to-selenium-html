package dridco.macrolang

class ParameterReplacementOnMacroNameTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ['''
<macro name="echo-something">
    <parametrized-macro name="echo-$what" />
</macro>
''', '''
<macro name="echo-yes">
    <command name="echo" target="yes" />
</macro>
''', '''
<macro name="echo-no">
    <command name="echo" target="no" />
</macro>
''']
    }

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>echo</td>
    <td>yes</td>
    <td></td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        '<echo-something what="yes" />'
    }
}
