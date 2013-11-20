package dridco.macrolang

class ManyCommandsMacroTest extends AbstractCommandTest {

    protected macros() {
        ['''
<macro name='find'>
    <command name='open' target='http://localhost/index.html' />
    <command name='type' target='id=keywords' value='lookup keywords' />
    <command name='click' target='id=search' />
</macro>''']
    }

    @Override
    def expectedCommands() {
        '''
<tr>
    <td>open</td>
    <td>http://localhost/index.html</td>
    <td></td>
</tr>
<tr>
    <td>type</td>
    <td>id=keywords</td>
    <td>lookup keywords</td>
</tr>
<tr>
    <td>click</td>
    <td>id=search</td>
    <td></td>
</tr>'''
    }

    @Override
    def definedCommands() {
        '<find />'
    }
}
