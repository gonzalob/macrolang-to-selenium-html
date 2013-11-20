package dridco.macrolang

class MacroWithParametersTest extends AbstractCommandTest {

    protected macros() {
        ['''
<macro name='login'>
    <command name='open' target='${url}' />
    <command name='type' target='id=username' value='${username}' />
    <command name='type' target='id=password' value='${password}' />
    <command name='click' target='id=submit' />
</macro>''']
    }

    @Override
    def expectedCommands() {
        '''
<tr>
    <td>open</td>
    <td>login.mydomain.com</td>
    <td></td>
</tr>
<tr>
    <td>type</td>
    <td>id=username</td>
    <td>sample</td>
</tr>
<tr>
    <td>type</td>
    <td>id=password</td>
    <td>s3cr3t</td>
</tr>
<tr>
    <td>click</td>
    <td>id=submit</td>
    <td></td>
</tr>'''
    }

    @Override
    def definedCommands() {
        '<login url="login.mydomain.com" username="sample" password="s3cr3t" />'
    }
}
