package dridco.macrolang

class EscapedHtmlEntitiesInCommandsTest extends AbstractCommandTest {

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>verifyTextPresent</td>
    <td>&lt;p&gt;hello world&lt;/p&gt;</td>
    <td></td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        '<command name="verifyTextPresent" target="&lt;p&gt;hello world&lt;/p&gt;" />'
    }
}
