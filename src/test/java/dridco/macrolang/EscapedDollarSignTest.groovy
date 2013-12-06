package dridco.macrolang

class EscapedDollarSignTest extends AbstractCommandTest {

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>verifyTextPresent</td>
    <td>$ 0.00</td>
    <td></td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        '<command name="verifyTextPresent" target="\\$ 0.00" />'
    }
}
