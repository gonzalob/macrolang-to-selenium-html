package dridco.macrolang

class NestedMacrosWithParametersTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ['''
<macro name='login'>
    <command name='open' target='http://localhost' />
    <command name='type' target='id=username' value='$user' />
    <command name='click' target='id=submit' />
</macro>
''', '''
<macro name='check-balance'>
    <login user='$username' />
    <command name='click' target='id=balance' />
</macro>''']
    }

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>open</td>
    <td>http://localhost</td>
    <td></td>
</tr>
<tr>
    <td>type</td>
    <td>id=username</td>
    <td>sample</td>
</tr>
<tr>
    <td>click</td>
    <td>id=submit</td>
    <td></td>
</tr>
<tr>
    <td>click</td>
    <td>id=balance</td>
    <td></td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        '<check-balance username="sample" />'
    }
}
