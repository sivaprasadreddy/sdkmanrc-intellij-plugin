package dev.sivalabs.sdkmanrc.plugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SdkmanCompletionProvider extends CompletionProvider<CompletionParameters> {
    public static final String SDKMANRC_FILENAME = ".sdkmanrc";
    public static final List<String> SDKMAN_SDKS = List.of(
            "java",
            "maven",
            "mvnd",
            "gradle",
            "kotlin",
            "groovy",
            "scala"
    );

    @Override
    protected void addCompletions(
            @NotNull CompletionParameters parameters,
            @NotNull ProcessingContext context,
            @NotNull CompletionResultSet result) {

        PsiFile file = parameters.getOriginalFile();
        if (!SDKMANRC_FILENAME.equals(file.getName())) {
            return;
        }
        int offset = parameters.getOffset();
        String text = file.getText();
        int lineStart = text.lastIndexOf('\n', offset - 1) + 1;
        String line = text.substring(lineStart, offset);
        if (!line.contains("=")) {
            SDKMAN_SDKS.forEach(k ->
                    result.addElement(LookupElementBuilder.create(k)));
        } else {
            String key = line.substring(0, line.indexOf("=")).trim();
            List<String> versions = SdkmanService.getVersions(key);
            versions.forEach(v ->
                    result.addElement(LookupElementBuilder.create(v)));
        }
    }
}