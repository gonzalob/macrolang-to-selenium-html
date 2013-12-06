package dridco.macrolang

class EscapedDollarSignInMacroTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ['''
<macro name='check-price'>
    <command name='verifyText' target='id=price' value='\\$ $amount' />
</macro>
''']
    }

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>verifyText</td>
    <td>id=price</td>
    <td>$ 40.50</td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        '<check-price amount="40.50" />'
    }
}
