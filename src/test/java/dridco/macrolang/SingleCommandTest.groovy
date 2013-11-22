package dridco.macrolang

class SingleCommandTest extends AbstractCommandTest {

    @Override
    def expectedCommands() {
        '''
<tr>
    <td>waitForElementPresent</td>
    <td>id=foo</td>
    <td></td>
</tr>'''
    }

    @Override
    def definedCommands() {
        '<command name="waitForElementPresent" target="id=foo" />'
    }
}
