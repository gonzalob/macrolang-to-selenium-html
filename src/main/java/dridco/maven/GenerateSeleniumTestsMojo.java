package dridco.maven;

import dridco.macrolang.SeleniumTest;
import dridco.macrolang.SeleniumTestCompiler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.io.filefilter.HiddenFileFilter.VISIBLE;
import static org.codehaus.plexus.util.FileUtils.copyDirectoryStructure;

/**
 * @goal run
 * @phase="generate-test-sources"
 */
public class GenerateSeleniumTestsMojo extends AbstractMojo {

    /**
     * Directory for macrolang sources,
     *
     * @parameter default-value="${basedir}/src/test/macrolang"
     */
    private File sources;
    /**
     * Directory for resources to be copied without processing to the
     * destination directory.
     * Existing Selenium sources should be put here, to seamlessly
     * integrate this plugin to the existing build lifecycle.
     *
     * @parameter
     */
    private File resources;
    /**
     * @parameter default-value="${project.build.directory}/generated-test-sources/macrolang"
     * @readonly
     */
    private File target;
    /**
     * @parameter default-value="^.*\.macro$"
     */
    private String macroFilenamePattern;
    /**
     * @parameter expression="${macrolang.skip}" default-value="true"
     */
    private Boolean skip;

    /**
     * @parameter default-value='UTF-8'
     */
    private String targetEncoding;

    /**
     * @parameter default-value='UTF-8'
     */
    private String sourceEncoding;

    private List<String> macros = new ArrayList<String>();
    private SeleniumTestCompiler compiler;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            if (canExecute()) {
                collectMacros();
                initializeCompiler();
                compileSources();
                copyResources();
            } else getLog().info("Skipping execution");
        } catch (IOException e) {
            throw new MojoExecutionException("An I/O operation forced the compilation to abort.", e);
        }
    }

    public void collectMacros() throws IOException {
        crawl(sources, new FileVisitor() {
            @Override
            public void accept(File file) throws IOException {
                if (isMacro(file)) macros.add(readFileToString(file, sourceEncoding));
            }
        });
    }

    public void initializeCompiler() {
        compiler = new SeleniumTestCompiler(macros);
    }

    private void compileSources() throws IOException {
        final String targetPath = target.getPath();
        crawl(sources, new FileVisitor() {
            @Override
            public void accept(File file) throws IOException {
                if (!isMacro(file)) {
                    String source = readFileToString(file, sourceEncoding);
                    SeleniumTest compiled = compiler.compile(source);
                    File target = new File(targetPath, compiled.getName());
                    writeStringToFile(target, compiled.getCode(), targetEncoding);
                }
            }
        });
    }

    private boolean isMacro(File candidate) {
        return candidate.getName().matches(macroFilenamePattern);
    }

    private void crawl(File origin, FileVisitor visitor) throws IOException {
        if (origin.isDirectory()) for (File child : origin.listFiles((FileFilter) VISIBLE)) crawl(child, visitor);
        else visitor.accept(origin);
    }

    private static interface FileVisitor {
        void accept(File file) throws IOException;
    }

    private void copyResources() throws IOException {
        if (resources != null) {
            if (resources.isDirectory()) copyDirectoryStructure(resources, target);
            else getLog().warn("Specified resources path is not a directory, or does not exist");
        }

    }

    private boolean canExecute() {
        return FALSE.equals(skip);
    }
}
