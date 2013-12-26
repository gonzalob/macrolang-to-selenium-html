package dridco.macrolang

class EscapedHtmlEntitiesInMacrosTest extends AbstractCommandTest {

    @Override
    protected macros() {
        ['''
<macro name="check-html-contents">
    <command name="verifyTextPresent" target="id=$id" value="$content" />
</macro>''']
    }

    @Override
    protected expectedCommands() {
        '''
<tr>
    <td>verifyTextPresent</td>
    <td>id=my-element</td>
    <td>&lt;a href='http://localhost'&gt;link&lt;/a&gt;</td>
</tr>'''
    }

    @Override
    protected definedCommands() {
        $/<check-html-contents id="my-element" content="&lt;a href='http://localhost'&gt;link&lt;/a&gt;" />/$
    }
}
