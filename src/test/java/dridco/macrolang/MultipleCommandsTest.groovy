package dridco.macrolang

class MultipleCommandsTest extends AbstractCommandTest {

    @Override
    def expectedCommands() {
        '''
<tr>
    <td>type</td>
    <td>input[name=username]</td>
    <td>foo@bar.com</td>
</tr>
<tr>
    <td>click</td>
    <td>input[type=submit]</td>
    <td></td>
</tr>'''
    }

    @Override
    def definedCommands() {
        '''
<command name='type' target='input[name=username]' value='foo@bar.com' />
<command name='click' target='input[type=submit]' />
'''
    }
}
